package com.example.group5.focus.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.group5.focus.R;
import com.example.group5.focus.helpers.InputValidation;
import com.example.group5.focus.model.Client;
import com.example.group5.focus.sql.DatabaseHelper;

public class ClientAddActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = ClientAddActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutDOB;
    private TextInputLayout textInputLayoutCurrency;
    private TextInputLayout textInputLayoutCountry;
    private TextInputLayout textInputLayoutWealth;
    private TextInputLayout textInputLayoutCompany;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextDOB;
    private TextInputEditText textInputEditTextCurrency;
    private TextInputEditText textInputEditTextCountry;
    private TextInputEditText textInputEditTextWealth;
    private TextInputEditText textInputEditTextCompany;


    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Client client;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_add);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutDOB = (TextInputLayout) findViewById(R.id.textInputLayoutDOB);
        textInputLayoutCurrency = (TextInputLayout) findViewById(R.id.textInputLayoutCurrency);
        textInputLayoutCountry = (TextInputLayout) findViewById(R.id.textInputLayoutCountry);
        textInputLayoutWealth = (TextInputLayout) findViewById(R.id.textInputLayoutWealth);
        textInputLayoutCompany = (TextInputLayout) findViewById(R.id.textInputLayoutCompany);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextDOB = (TextInputEditText) findViewById(R.id.textInputEditTextDOB);
        textInputEditTextCurrency = (TextInputEditText) findViewById(R.id.textInputEditTextCurrency);
        textInputEditTextCountry = (TextInputEditText) findViewById(R.id.textInputEditTextCountry);
        textInputEditTextWealth = (TextInputEditText) findViewById(R.id.textInputEditTextWealth);
        textInputEditTextCompany = (TextInputEditText) findViewById(R.id.textInputEditTextCompany);


        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        client = new Client();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextDOB, textInputLayoutDOB, getString(R.string.error_message_username))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextCurrency, textInputLayoutCurrency, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextCountry, textInputLayoutCountry, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextWealth, textInputLayoutWealth, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextCompany, textInputLayoutCompany, getString(R.string.error_message_password))) {
            return;
        }


        if (!databaseHelper.checkUser(textInputEditTextName.getText().toString().trim(), "")) {

            client.setName(textInputEditTextName.getText().toString().trim());
            client.setDOB(textInputEditTextDOB.getText().toString().trim());
            client.setCurrency(textInputEditTextCurrency.getText().toString().trim());
            client.setCountry(textInputEditTextCountry.getText().toString().trim());
            client.setWealth(textInputEditTextWealth.getText().toString().trim());
            client.setCompany(textInputEditTextCompany.getText().toString().trim());
            client.setManager(Integer.parseInt(getIntent().getStringExtra("ID")));

            databaseHelper.addClient(client);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_username_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextDOB.setText(null);
        textInputEditTextCurrency.setText(null);
        textInputEditTextCountry.setText(null);
        textInputEditTextWealth.setText(null);
        textInputEditTextCompany.setText(null);
    }
}
