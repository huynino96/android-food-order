package com.example.assignmen1;

import android.app.NotificationManager;
import android.content.Context;

import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import java.util.Objects;

public class AddOrder extends AppCompatActivity {

    // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
    EditText editTextTable, editTextOrder, editTextNote;
    Button button;
    String id, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        editTextTable = findViewById(R.id.edit_text_table_number);
        editTextOrder = findViewById(R.id.edit_text_order_detail);
        editTextNote = findViewById(R.id.edit_text_order_note);

        button = findViewById(R.id.button);
        editTextTable.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextOrder.setOnFocusChangeListener((v, hasFocus) -> editTextOrder.setSelection(editTextOrder.getText().toString().length()));
        editTextNote.setOnFocusChangeListener((v, hashCode) -> editTextNote.setSelection(editTextNote.getText().toString().length()));


        id = getIntent().getStringExtra("id");
        edit = getIntent().getStringExtra("edit");
        checkEditNotNull();
        onClickedOrder();
        sendNotification();

    }

    private void checkEditNotNull() {
        if (edit != null) {
            if (edit.equals("yes")) {
                String tableNumber = OrderList.orderList.get(Integer.parseInt(id)).getTableNumber();
                String orderDetail = OrderList.orderList.get(Integer.parseInt(id)).getOrderDetail();
                String orderNote = OrderList.orderList.get(Integer.parseInt(id)).getOrderNote();
                editTextTable.setText(tableNumber);
                editTextOrder.setText(orderDetail);
                editTextNote.setText(orderNote);
                editTextTable.setSelection(tableNumber.length());
            }
        }
    }

    private void onClickedOrder() {
        button.setOnClickListener(v -> {
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            String tableNumber = editTextTable.getText().toString();
            String orderDetail = editTextOrder.getText().toString();
            String orderNote = editTextNote.getText().toString();
            if(tableNumber.equals("")){
                editTextTable.setError("Please Provide Table Number");
                editTextTable.requestFocus();
            }
            else if(orderDetail.equals("")){
                editTextOrder.setError("Please provide Order Detail");
                editTextOrder.requestFocus();
            }
            else {
                if(edit != null ) {
                    if (edit.equals("yes")) {
                        OrderModel orderModel = new OrderModel();
                        orderModel.setTableNumber(tableNumber);
                        orderModel.setOrderDetail(orderDetail);
                        OrderList.orderList.set(Integer.parseInt(id), orderModel);
                        OrderList.adapter.notifyDataSetChanged();
                        finish();
                    }
                }
                else {
                    int idMax = OrderList.orderList.size();
                    OrderModel orderModel = new OrderModel();
                    orderModel.setOrderId(String.valueOf(idMax+1));
                    orderModel.setTableNumber(tableNumber);
                    orderModel.setOrderDetail(orderDetail);
                    orderModel.setOrderNote(orderNote);
                    OrderList.orderList.add(orderModel);
                    Toast.makeText(AddOrder.this, "Order Has Been Added", Toast.LENGTH_SHORT).show();
                    showNotification();
                    OrderList.adapter.notifyDataSetChanged();
                    finish();
                }
            }
        });
    }

    private void showNotification(){
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(AddOrder.this, "My Notification")
                    .setContentTitle("My Notification")
                    .setContentText("Order Added")
                    .setSmallIcon(R.mipmap.ic_launcher);

            manager.notify(1, builder.build());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendNotification(){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "My Notify")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("My Notification")
                .setContentText("Order Added")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        int notificationId = 1;
        notificationManager.notify(notificationId, builder.build());

    }
}
