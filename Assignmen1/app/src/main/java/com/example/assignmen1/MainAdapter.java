package com.example.assignmen1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {

    Context context;
    ArrayList<OrderModel> list;
    private final LayoutInflater mLayoutInflater;

    public MainAdapter(Context context, ArrayList<OrderModel> list){
        this.context = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int indexPosition) {
        return indexPosition;
    }

    @Override
    public long getItemId(int indexPosition) {
        return indexPosition;
    }

    @Override
    public View getView(int indexPosition, View v, ViewGroup parent) {
        ViewOrderPlace viewPlace;
        if(v == null){
            v = mLayoutInflater.inflate(R.layout.order_listview, parent, false);
            viewPlace = new ViewOrderPlace(v);
            v.setTag(viewPlace);
        }
        else{
            viewPlace = (ViewOrderPlace) v.getTag();
        }

        OrderModel orderModel = list.get(indexPosition);
        String tableNumber = orderModel.getTableNumber();
        String orderDetail = orderModel.getOrderDetail();
        viewPlace.tableNumber.setText(tableNumber);
        viewPlace.orderDetail.setText(orderDetail);
        return v;
    }

    private static class ViewOrderPlace {
        public TextView tableNumber;
        public TextView orderDetail;
        public ViewOrderPlace(View view) {
            tableNumber = view.findViewById(R.id.textView);
            orderDetail = view.findViewById(R.id.textView2);

        }
    }
}
