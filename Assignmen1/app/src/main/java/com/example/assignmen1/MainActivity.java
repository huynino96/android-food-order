package com.example.assignmen1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        EditText nameText = findViewById(R.id.staffId);
        EditText pwd = findViewById(R.id.passwordText);

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
                startActivity(new Intent(MainActivity.this, OrderList.class));
                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Wrong Staff ID or Password, Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });

    }

}


