package com.winnipegapp.examples;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LoginForm extends AppCompatActivity
{
    Toolbar toolbar;
    Button btnScanId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration_form_s1);

        setupToolbar();

        btnScanId = (Button)findViewById(R.id.buttonScanId);

        btnScanId.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(LoginForm.this, LoginScan.class));
            }
        });
    }

    private void setupToolbar()
    {

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}
