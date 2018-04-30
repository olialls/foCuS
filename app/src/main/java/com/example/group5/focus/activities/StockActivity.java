package com.example.group5.focus.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.group5.focus.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StockActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputLayout textInputLayoutSymbol;
    private TextInputEditText textInputEditTextSymbol;
    private TextView textViewPrice;
    private AppCompatButton appCompatButtonCollect;
    ProgressDialog pd;
    String Symbol;
    Date todayDate = Calendar.getInstance().getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    String date = formatter.format(todayDate);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        initViews();
        initListeners();
    }

    private void initViews() {
        textInputLayoutSymbol = (TextInputLayout) findViewById(R.id.textInputLayoutSymbol);
        textInputEditTextSymbol = (TextInputEditText) findViewById(R.id.textInputEditTextSymbol);
        textViewPrice = (AppCompatTextView) findViewById(R.id.textViewPrice);
        appCompatButtonCollect = (AppCompatButton) findViewById(R.id.appCompatButtonCollect);

    }

    private void initListeners() {
        appCompatButtonCollect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonCollect:
                getStockPrice();
    }}


    private void getStockPrice(){
        Symbol =  textInputEditTextSymbol.getText().toString().trim();
        new JsonTask().execute("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + Symbol + "&apikey=AUEWQ800Q3XMU1P0");
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(StockActivity.this);
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
                    buffer.append(line+"\n");
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
            if (pd.isShowing()){
                pd.dismiss();
            }

            try {
                JSONObject jsonObject = new JSONObject(result);


                JSONObject infoArray = jsonObject.getJSONObject("Time Series (Daily)");
                //String lastDate = infoArray.getString("3. Last Refreshed");
                JSONObject info = infoArray.getJSONObject("2018-04-30");
//                JSONObject resultArray = info.getJSONObject("2018-04-30");
                double price = info.getDouble("4. close");

                String resultPrice = Double.toString(price);
                textViewPrice.setText(resultPrice);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }




}}
