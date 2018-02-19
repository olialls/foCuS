package com.example.group5.focus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Stock_Page extends AppCompatActivity {
    Button button;
    TextView stockname, stockprice;
    String json_url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=15min&outputsize=compact&apikey=AUEWQ800Q3XMU1P0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock__page);
        button = (Button)findViewById(R.id.RequestButton);
        stockname = (TextView)findViewById(R.id.StockName);
        stockprice = (TextView)findViewById(R.id.StockPrice);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url, null)
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            stockname.setText(response.getString("Symbol"));
                            stockprice.setText(response.getString("close"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                };
                )

            }
        });


    }
}


