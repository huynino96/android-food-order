package com.example.assignmen1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText nameText = findViewById(R.id.staffId);
        EditText pwd = findViewById(R.id.passwordText);
        button = findViewById(R.id.login);
        button.setOnClickListener(v -> {
            if(nameText.getText().toString().equals("") && pwd.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "ID and Password must not be empty", Toast.LENGTH_SHORT).show();
            }
            else if(nameText.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "ID must not be empty", Toast.LENGTH_SHORT).show();
            }
            else if(pwd.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Password must not be empty", Toast.LENGTH_SHORT).show();
            }
            else if(nameText.getText().toString().equals("3756868") && pwd.getText().toString().equals("123456")) {
                nameText.getText().clear();
                pwd.getText().clear();
                showLoginNotification();
                startActivity(new Intent(MainActivity.this, OrderList.class));
                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(MainActivity.this, "Wrong Staff ID or Password, Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showLoginNotification(){
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Login")
                    .setContentTitle("My Notification")
                    .setContentText("Welcome!!! Have a nice working day!")
                    .setSmallIcon(R.mipmap.ic_launcher);
            manager.notify(1, builder.build());

    }

}


