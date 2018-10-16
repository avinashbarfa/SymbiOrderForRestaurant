package com.avinashbarfa.symbiorderforrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avinashbarfa.symbiorderforrestaurant.DataBean.RestaurantDataBean;
import com.avinashbarfa.symbiorderforrestaurant.DataBean.UrlLinks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUserID,edtPassword;
    Button btnLogin;
    String rstID;
    RestaurantDataBean restaurantDataBean = new RestaurantDataBean();
    UrlLinks urlLinks = new UrlLinks();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserID = findViewById(R.id.edtUserID);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin) {
            restaurantDataBean.setRestaurantUserID(Integer.valueOf(edtUserID.getText().toString()));
            restaurantDataBean.setRestaurantPassword(edtPassword.getText().toString());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlLinks.getAuthenticateLink() , new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    rstID = response;
                    Log.v("response---------", response);
                    if(!"INVALID".equals(response)) {
                        String[] arrOfStr = rstID.split(" ", 2);
                        restaurantDataBean.setRestaurantID(Integer.valueOf(arrOfStr[0]));
                        restaurantDataBean.setRestaurantName(arrOfStr[1]);
                        Toast.makeText(getApplicationContext(), "Welcome " + " || " + restaurantDataBean.getRestaurantName(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("rstID", String.valueOf(arrOfStr[0]));
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Credentials" , Toast.LENGTH_LONG).show();
                    }
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
                    params.put("user_id" , String.valueOf(restaurantDataBean.getRestaurantUserID()));
                    params.put("password" , restaurantDataBean.getRestaurantPassword());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}
