package com.example.assignmen1;

import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignmen1.OrderList;
import com.example.assignmen1.MainAdapter;
import com.example.assignmen1.R;
import com.example.assignmen1.OrderModel;

import java.util.Objects;

public class AddOrder extends AppCompatActivity {

    EditText editTextTable, editTextOrder;
    Button button;
    String id, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        editTextTable = (EditText) findViewById(R.id.edit_text_nama);
        editTextOrder = (EditText) findViewById(R.id.edit_text_stok);
        button = (Button) findViewById(R.id.button);

        editTextOrder.setInputType(InputType.TYPE_CLASS_PHONE);
        editTextOrder.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextOrder.setSelection(editTextOrder.getText().toString().length());
            }
        });

        id = getIntent().getStringExtra("id");
        edit = getIntent().getStringExtra("edit");
        if (edit != null) {
            if (edit.equals("yes")) {
                String tableNumber = OrderList.orderList.get(Integer.parseInt(id)).getTableNumber();
                String orderDetail = OrderList.orderList.get(Integer.parseInt(id)).getOrderDetail();
                editTextTable.setText(tableNumber);
                editTextOrder.setText(orderDetail);
                editTextOrder.setSelection(tableNumber.length());
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableNumber = editTextTable.getText().toString();
                String orderDetail = editTextTable.getText().toString();
                if(tableNumber.equals("")){
                    editTextTable.setError("Please Provide Table Number");
                    editTextTable.requestFocus();
                }else if(orderDetail.equals("")){
                    editTextOrder.setError("Please provide Order Detail");
                    editTextOrder.requestFocus();
                }else{
                    if(edit != null ) {
                        if (edit.equals("yes")) {
                            OrderModel orderModel = new OrderModel();
                            orderModel.setTableNumber(tableNumber);
                            orderModel.setOrderDetail(orderDetail);
                            OrderList.orderList.set(Integer.parseInt(id), orderModel);
                            OrderList.adapter.notifyDataSetChanged();
                            finish();
                        }
                    }else{
                        int idMax = OrderList.orderList.size();
                        OrderModel orderModel = new OrderModel();
                        orderModel.setOrderId(String.valueOf(idMax+1));
                        orderModel.setTableNumber(tableNumber);
                        orderModel.setOrderDetail(orderDetail);
                        OrderList.orderList.add(orderModel);
                        OrderList.adapter.notifyDataSetChanged();
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
