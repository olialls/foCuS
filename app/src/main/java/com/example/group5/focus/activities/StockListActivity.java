package com.example.group5.focus.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.group5.focus.R;
import com.example.group5.focus.adapters.StockRecyclerAdapter;
import com.example.group5.focus.model.Stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StockListActivity extends AppCompatActivity {

    private AppCompatActivity activity = StockListActivity.this;
    private RecyclerView recyclerViewUsers;
    private List<Stock> listStocks;
    private StockRecyclerAdapter stockRecyclerAdapter;
    private String[] Stocks = {"TWTR","GE","SNAP","BAC","MDR","FCX","WFT","NOK","VALE"};
    ProgressDialog pd;
    private FloatingActionButton fab;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_list);
        initViews();
        initObjects();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SearchStockIntent = new Intent(StockListActivity.this, StockActivity.class);
                view.getContext().startActivity(SearchStockIntent);
            }
        });

    }

    /**
     * This method is to initialize views
     */

    private void initViews() {
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewStocks);
        fab = (FloatingActionButton) findViewById(R.id.fab1);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listStocks = new ArrayList<>();
        stockRecyclerAdapter = new StockRecyclerAdapter(listStocks);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(stockRecyclerAdapter);


        getStockPrice();
    }

    private void getStockPrice(){
        String joinedSymbols = String.join(",", Stocks);
        new StockListActivity.JsonTask().execute("https://www.alphavantage.co/query?function=BATCH_STOCK_QUOTES&symbols=" + joinedSymbols + "&apikey=AUEWQ800Q3XMU1P0");
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(StockListActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()) {
                pd.dismiss();
            }
            listStocks.clear();

            try {
                JSONObject jsonObject = new JSONObject(result);


                //for x in range length of list (STOCKS), add price and name to stock object

                //JSONObject infoArray = jsonObject.getJSONObject("Stock Quotes");
                JSONArray finalArray = jsonObject.getJSONArray("Stock Quotes");

                for(int i = 0; i < Stocks.length; i++){
                    JSONObject info = finalArray.getJSONObject(i);
                    String symbol = info.getString("1. symbol");
                    String price = info.getString("2. price");
                    Stock stock = new Stock();
                    stock.setSymbol(symbol);
                    stock.setPrice(price);
                    listStocks.add(stock);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            stockRecyclerAdapter.notifyDataSetChanged();


        }
}}