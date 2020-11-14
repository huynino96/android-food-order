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
    private LayoutInflater mLayoutInflater = null;

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
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.order_listview, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        OrderModel orderModel = list.get(position);
        String tableNumber = orderModel.getTableNumber();
        String orderDetail = orderModel.getOrderDetail();
        viewHolder.tableNumber.setText(tableNumber);
        viewHolder.orderDetail.setText(orderDetail);
        return convertView;
    }

    private  class ViewHolder {
        public TextView tableNumber;
        public TextView orderDetail;
        public ViewHolder(View base) {
            tableNumber = base.findViewById(R.id.textView);
            orderDetail = base.findViewById(R.id.textView2);

        }
    }
}
