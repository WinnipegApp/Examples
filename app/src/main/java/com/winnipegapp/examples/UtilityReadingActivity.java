package com.winnipegapp.examples;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UtilityReadingActivity extends AppCompatActivity {

    /**
     *   Class variables
     * */
    private Toolbar toolbar;
    private TextView utilityOne, utilityTwo;
    private EditText utilityOneRead, utilityTwoRead;
    private Button buttonSubmit;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_reading);

        utilityOne = (TextView) findViewById(R.id.utilityOne);
        utilityTwo = (TextView) findViewById(R.id.utilityTwo);
        utilityOneRead = (EditText) findViewById(R.id.utilityOneRead);
        utilityTwoRead = (EditText) findViewById(R.id.utilityTwoRead);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        setupToolbar();

        determineType();

    }

    private void setupToolbar() {

        /**
         *   Creates the Toolbar and return button to MainActivity
         * */
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void determineType() {

        /**
         *   Retrieves utility type from the previous activity and passes to the corresponding variable
         * */
        Intent intent = getIntent();

        String s = intent.getStringExtra("default");

        if (s.equals("GAS ELECTRICITY")) {

            type = 0;

            utilityOne.setText("Electricity Reading:");
            utilityTwo.setText("Gas Reading");

        } else {

            type = 1;

            utilityOne.setText("Water Reading:");
            utilityTwo.setVisibility(View.GONE);
            utilityTwoRead.setVisibility(View.GONE);

        }

    }

    public void onClickSubmit(View view) {

/*        initialiseData();

        DatabaseHelper helper = DatabaseHelper.getInstance(this);

        *//**
         *   Retrieves size of the inquiries database, adds +1 and passes to the corresponding variable
         * *//*
        inquiry_id = helper.getInquiries().size() + 1;

        //Inquiry inquiry = new Inquiry(inquiry_id, inquiry_type, description, imageURL, coordinates);

        //helper.createInquiry(inquiry);

        Toast.makeText(getApplicationContext(), "Inquiry number " + inquiry_id + " was created.", Toast.LENGTH_LONG).show();

        this.finish();*/

    }

}
