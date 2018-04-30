package com.example.group5.focus.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import com.example.group5.focus.R;
import com.example.group5.focus.model.Client;
import com.example.group5.focus.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivityManager extends AppCompatActivity {

    private AppCompatActivity activity = ProfileActivityManager.this;
    private AppCompatTextView textViewName;
    private AppCompatTextView textViewWealth;
    private AppCompatTextView textViewIndustry;
    private AppCompatTextView textViewCountry;
    private AppCompatTextView textViewEmail;
    private AppCompatTextView textViewMobile;
    private List<Client> listClient;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);
        //initViews();
        //initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        //textViewUsername = (AppCompatTextView) findViewById(R.id.textViewUsername);
        textViewName = (AppCompatTextView) findViewById(R.id.textViewNameClient);
        textViewWealth = (AppCompatTextView) findViewById(R.id.textViewWealth);
        textViewIndustry = (AppCompatTextView) findViewById(R.id.textViewIndustry);
        textViewCountry = (AppCompatTextView) findViewById(R.id.textViewCountry);
        textViewEmail = (AppCompatTextView) findViewById(R.id.textViewEmail);
        textViewMobile = (AppCompatTextView) findViewById(R.id.textViewMobile);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listClient = new ArrayList<>();
        databaseHelper = new DatabaseHelper(activity);

        /**
         * For if manager username wants to be used later
         */
        //String nameFromIntent = getIntent().getStringExtra("USERNAME");
        //textViewUsername.setText(nameFromIntent);

        int positionFromIntent = getIntent().getIntExtra("POSITION", 0);

        String managerID = getIntent().getStringExtra("ID");
        listClient = databaseHelper.findClients(managerID);

        textViewName.setText(listClient.get(positionFromIntent).getName());
        textViewCountry.setText(listClient.get(positionFromIntent).getCountry());
        textViewIndustry.setText(listClient.get(positionFromIntent).getWealth());
        textViewWealth.setText(listClient.get(positionFromIntent).getCompany());
        textViewEmail.setText(listClient.get(positionFromIntent).getDOB());

        //getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
//    private void getDataFromSQLite() {
//        // AsyncTask is used that SQLite operation not blocks the UI Thread.
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... params) {
//                listUsers.clear();
//                listUsers.addAll(databaseHelper.getAllUser());
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                usersRecyclerAdapter.notifyDataSetChanged();
//            }
//        }.execute();
//    }
}