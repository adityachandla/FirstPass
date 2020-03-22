package com.example.firstpass;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


class PasswordService {

    String url;
    RequestQueue requestQueue;
    TextView passwordTextView;

    PasswordService(TextView passwordTextView) {
        this.requestQueue = Volley.newRequestQueue(MainActivity.getContext());
        this.url = "add_your_url_here";
        this.passwordTextView = passwordTextView;
    }

    public void putPassword(String userSecret, String appName) {
        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("userSecret", userSecret);
            requestJson.put("appName", appName);
        }catch (Exception e) {
            System.out.println("can't make request");
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,requestJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            passwordTextView.setText(response.get("password").toString());
                        }catch (Exception e) {
                            passwordTextView.setText("An error occured in response");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                passwordTextView.setText("An error occured");
            }
        });
        requestQueue.add(request);
    }
}
