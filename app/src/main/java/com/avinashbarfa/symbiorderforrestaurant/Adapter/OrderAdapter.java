package com.avinashbarfa.symbiorderforrestaurant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.avinashbarfa.symbiorderforrestaurant.DataBean.OrdersDataBean;
import com.avinashbarfa.symbiorderforrestaurant.DataBean.UrlLinks;
import com.avinashbarfa.symbiorderforrestaurant.EditOrderSummary;
import com.avinashbarfa.symbiorderforrestaurant.OrderClickListener;
import com.avinashbarfa.symbiorderforrestaurant.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> implements AdapterView.OnItemClickListener{

    private List<OrdersDataBean> ordersDataList;
    private Context context;
    UrlLinks urlLinks = new UrlLinks();

    public OrderAdapter(List<OrdersDataBean> ordersDataList, Context context) {
        this.ordersDataList = ordersDataList;
        this.context = context;
    }

    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list,parent,false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {
        final OrdersDataBean ordersData = ordersDataList.get(position);
        holder.txtItems.setText(ordersData.getItems());
        holder.txtOrderID.setText(String.valueOf(ordersData.getOrderID()));
        holder.txtorderedon.setText(ordersData.getTimestamp());
        holder.txtstatus.setText(String.valueOf(ordersData.getStatus()));
        holder.txttotalamount.setText(String.valueOf(ordersData.getAmount()));
        if("1".equals(String.valueOf(ordersData.getStatus()))){
            holder.txtstatus.setText("Preparing Order");
        } else if ("2".equals(String.valueOf(ordersData.getStatus()))){
            holder.txtstatus.setText("On the Way");
        } else if("3".equals(String.valueOf(ordersData.getStatus()))){
            holder.txtstatus.setText("Delivered");
        }
        holder.setOrderClickListener(new OrderClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, EditOrderSummary.class);
                intent.putExtra("order_id" , String.valueOf(ordersData.getOrderID()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersDataList.size();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtItems,txtOrderID, txtorderedon, txttotalamount,txtstatus;
        private OrderClickListener orderClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            txtItems = itemView.findViewById(R.id.txtItems);
            txtOrderID = itemView.findViewById(R.id.txtOrderID);
            txtorderedon = itemView.findViewById(R.id.txtorderedon);
            txttotalamount = itemView.findViewById(R.id.txttotalamount);
            txtstatus = itemView.findViewById(R.id.txtstatus);
            itemView.setOnClickListener(this);
        }

        public void setOrderClickListener(OrderClickListener orderClickListener){
            this.orderClickListener = orderClickListener;
        }

        @Override
        public void onClick(View v) {
            orderClickListener.onClick(v,getAdapterPosition());
        }
    }
}
