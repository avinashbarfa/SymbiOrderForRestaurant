package com.avinashbarfa.symbiorderforrestaurant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avinashbarfa.symbiorderforrestaurant.Adapter.OrderAdapter;
import com.avinashbarfa.symbiorderforrestaurant.DataBean.OrdersDataBean;
import com.avinashbarfa.symbiorderforrestaurant.DataBean.RestaurantDataBean;
import com.avinashbarfa.symbiorderforrestaurant.DataBean.UrlLinks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<OrdersDataBean> ordersDataList;
    private int rstID;
    UrlLinks urlLinks = new UrlLinks();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.myorder_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ordersDataList = new ArrayList<>();
        rstID = Integer.valueOf(getIntent().getStringExtra("rstID"));
        loadOrderList();
    }

    private void loadOrderList() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlLinks.getGetOrdersLink() , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("result");
                    for(int i =0; i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        OrdersDataBean list = new OrdersDataBean (
                                object.getInt("order_id"),
                                object.getInt("status"),
                                object.getInt("amount"),
                                object.getLong("contact_no"),
                                object.getString("items"),
                                object.getString("address"),
                                object.getString("timestamp"));
                        ordersDataList.add(list);

                    }
                    adapter = new OrderAdapter(ordersDataList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage() , Toast.LENGTH_LONG).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("rstID" , String.valueOf(rstID));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
