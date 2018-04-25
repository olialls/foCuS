package com.example.group5.focus.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.group5.focus.R;
import com.example.group5.focus.sample.MainActivity;

public class MenuActivity extends AppCompatActivity {

    GridLayout mainGrid;
    private AppCompatTextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        InitObjects();

        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }



    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MenuActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MenuActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI == 0) {

                        Intent accountsIntent = new Intent(MenuActivity.this, UsersListActivity.class);
                        accountsIntent.putExtra("USERNAME", textViewName.getText().toString().trim());
                        startActivity(accountsIntent);
                    }
                    else if (finalI == 1) {

                        Intent clientsIntent = new Intent(MenuActivity.this, ClientsListActivity.class);
                        clientsIntent.putExtra("USERNAME", textViewName.getText().toString().trim());
                        startActivity(clientsIntent);
                    }
                    else if (finalI == 2) {

                        Intent stockIntent = new Intent(MenuActivity.this, StockActivity.class);
                        stockIntent.putExtra("USERNAME", textViewName.getText().toString().trim());
                        startActivity(stockIntent);
                    }
                    else if (finalI == 3) {

                        Intent CalendarIntent = new Intent(MenuActivity.this, MainActivity.class);
                        CalendarIntent.putExtra("USERNAME", textViewName.getText().toString().trim());
                        startActivity(CalendarIntent);
                    }
                    else if (finalI == 4) {

                        Intent RSSIntent = new Intent(MenuActivity.this, RSSActivity.class);
                        startActivity(RSSIntent);
                    }
                    else if (finalI == 5) {

                        Intent MapsIntent = new Intent(MenuActivity.this, MapsActivity.class);
                        startActivity(MapsIntent);
                    }

                }
            });
        }
    }
    private void InitObjects() {
        String nameFromIntent = getIntent().getStringExtra("USERNAME");
        textViewName.setText(nameFromIntent);
    }
}
