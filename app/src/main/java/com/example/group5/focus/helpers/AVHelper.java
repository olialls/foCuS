//package com.example.group5.focus.helpers;
//
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.webkit.WebView;
//
//import com.example.group5.focus.activities.StockActivity;
//import com.example.group5.focus.activities.StockListActivity;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class AVHelper {
//    private String APIKey;
//    private String[] Stocks = {"TWTR","GE","SNAP","BAC","MDR","FCX","WFT","NOK","VALE"};
//    ProgressDialog pd;
//
//
//
//
//
//
//    private void getStockPrice(){
//        String joinedSymbols = String.join(",", Stocks);
//        new AVHelper.JsonTask().execute("https://www.alphavantage.co/query?function=BATCH_STOCK_QUOTES&symbols=" + joinedSymbols + "&apikey=AUEWQ800Q3XMU1P0");
//    }
//
//    private class JsonTask extends AsyncTask<String, String, String> {
//
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            pd = new ProgressDialog(AVHelper.this);
//            pd.setMessage("Please wait");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        protected String doInBackground(String... params) {
//
//
//            HttpURLConnection connection = null;
//            BufferedReader reader = null;
//
//            try {
//                URL url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//
//
//                InputStream stream = connection.getInputStream();
//
//                reader = new BufferedReader(new InputStreamReader(stream));
//
//                StringBuffer buffer = new StringBuffer();
//                String line = "";
//
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line + "\n");
//                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
//
//                }
//
//                return buffer.toString();
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (connection != null) {
//                    connection.disconnect();
//                }
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (pd.isShowing()) {
//                pd.dismiss();
//            }
//
//            try {
//                JSONObject jsonObject = new JSONObject(result);
//
//                JSONObject infoArray = jsonObject.getJSONObject("Meta Data");
//                String lastDate = infoArray.getString("3. Last Refreshed");
//
//                JSONObject timeSeries = jsonObject.getJSONObject("Time Series (Daily)");
//                JSONObject resultArray = timeSeries.getJSONObject(lastDate);
//                double price = resultArray.getDouble("4. close");
//
//                String resultPrice = Double.toString(price);
//                //textViewPrice.setText(resultPrice);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//
//    }}
