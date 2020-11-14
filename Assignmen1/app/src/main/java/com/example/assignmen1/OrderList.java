package com.example.assignmen1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.CookieHandler;
import java.util.ArrayList;

public class OrderList extends AppCompatActivity {
    ListView listView;
    public static final ArrayList<OrderModel> orderList = new ArrayList<OrderModel>();
    public static MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        listView = findViewById(R.id.listView);
        orderList.clear();
        OrderModel orderModel = new OrderModel();
        orderModel.setOrderId("1");
        orderModel.setTableNumber("12");
        orderModel.setOrderDetail("1 x Cơm chiên, 2 x Canh Chua");
        orderList.add(orderModel);


        adapter = new MainAdapter(getApplicationContext(), orderList);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            final CharSequence[] item = {"Edit","Delete"};
            AlertDialog.Builder builder = new AlertDialog.Builder(OrderList.this);
            builder.setItems(item, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(item[which].equals("Edit")){
                        Intent intent = new Intent(getApplicationContext(), AddOrder.class);
                        intent.putExtra("edit", "yes");
                        intent.putExtra("id", String.valueOf(position));
                        startActivity(intent);
                    }else if(item[which].equals("Delete")){
                        orderList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            builder.show();
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                startActivity(new Intent(getApplicationContext(), AddOrder.class));
                return true;
            default:
                break;
        }
        return true;
    }
}