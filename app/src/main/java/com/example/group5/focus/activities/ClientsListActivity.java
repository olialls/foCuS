package com.example.group5.focus.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.group5.focus.R;
import com.example.group5.focus.adapters.ClientRecyclerAdapter;
import com.example.group5.focus.model.Client;
import com.example.group5.focus.model.User;
import com.example.group5.focus.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ClientsListActivity extends AppCompatActivity {

    private AppCompatActivity activity = ClientsListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<Client> listUsers;
    private ClientRecyclerAdapter clientRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        initViews();
        final String id = initID();
        initObjects(id);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddClientIntent = new Intent(ClientsListActivity.this, ClientAddActivity.class);
                AddClientIntent.putExtra("ID", id);
                view.getContext().startActivity(AddClientIntent);
            }
        });

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects(String id) {
        listUsers = new ArrayList<>();
        clientRecyclerAdapter = new ClientRecyclerAdapter(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(clientRecyclerAdapter);

        getDataFromSQLite(id);

    }

    private String initID(){
        databaseHelper = new DatabaseHelper(activity);

        String nameFromIntent = getIntent().getStringExtra("USERNAME");
        textViewName.setText(nameFromIntent);

        List<User> User = databaseHelper.getCurrentUser(nameFromIntent);

        return Integer.toString(User.get(0).getId());

    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite(final String id) {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();

                listUsers.addAll(databaseHelper.findClients(id));

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                clientRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}