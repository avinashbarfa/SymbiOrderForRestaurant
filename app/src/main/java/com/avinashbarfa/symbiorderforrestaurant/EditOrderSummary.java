package com.avinashbarfa.symbiorderforrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avinashbarfa.symbiorderforrestaurant.DataBean.OrdersDataBean;
import com.avinashbarfa.symbiorderforrestaurant.DataBean.UrlLinks;

import java.util.HashMap;
import java.util.Map;

public class EditOrderSummary extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] status = {"Preparing Order", "On the Way", "Delivered"};

    EditText edtAmtPaid;
    int amtToPay;
    int currStatus;
    Button btnUpdate;
    OrdersDataBean ordersDataBean;
    UrlLinks urlLinks = new UrlLinks();
    String orderID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order_summary);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //get back home button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtAmtPaid = findViewById(R.id.edtAmtPaid);
        btnUpdate = findViewById(R.id.btnUpdate);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, status);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amtToPay = Integer.valueOf(edtAmtPaid.getText().toString());
                orderID  = getIntent().getStringExtra("order_id");
                UpdateData();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currStatus = position+1;
    }

    private void UpdateData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlLinks.getUpdateOrderDetail() , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               Toast.makeText(getApplicationContext(), response , Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage() , Toast.LENGTH_LONG).show();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("order_id" , orderID);
                params.put("amtToPay" , String.valueOf(amtToPay));
                params.put("currStatus" , String.valueOf(currStatus));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
