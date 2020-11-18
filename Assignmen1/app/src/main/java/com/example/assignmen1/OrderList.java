package com.example.assignmen1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {
    ListView listView;
    public static final ArrayList<OrderModel> orderList = new ArrayList<OrderModel>();
    @SuppressLint("StaticFieldLeak")
    public static MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        listView = findViewById(R.id.listView);
        orderList.clear();
        addDummyOrder();

        adapter = new MainAdapter(getApplicationContext(), orderList);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();
        onItemClicked();

    }

    private void onItemClicked() {
        listView.setOnItemClickListener((parent, view, indexPosition, id) -> {
            final CharSequence[] item = {"Edit","Delete"};
            AlertDialog.Builder builder = new AlertDialog.Builder(OrderList.this);
            builder.setItems(item, (dialog, which) -> {
                if(item[which].equals("Edit")){
                    Intent intent = new Intent(getApplicationContext(), AddOrder.class);
                    intent.putExtra("edit", "yes");
                    intent.putExtra("id", String.valueOf(indexPosition));
                    startActivity(intent);
                }
                else if(item[which].equals("Delete")){
                    orderList.remove(indexPosition);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(OrderList.this, "Removed Successfully", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        });
    }

    private void addDummyOrder() {
        OrderModel orderModel = new OrderModel();
        orderModel.setOrderId("1");
        orderModel.setTableNumber("12");
        orderModel.setOrderDetail("1 x Cơm chiên, 2 x Canh Chua");
        orderModel.setOrderNote("Không hành");
        orderList.add(orderModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            startActivity(new Intent(getApplicationContext(), AddOrder.class));
            return true;
        }
        if(item.getItemId() == R.id.logout){
            startActivity(new Intent(OrderList.this, MainActivity.class));
            Toast.makeText(OrderList.this, "Logout!!!", Toast.LENGTH_SHORT).show();
            finish();
        }
        return true;
    }
}