package com.winnipegapp.examples;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    EditText editNumber, editPass;
    Button buttonRegister, buttonSignIn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupToolbar();

        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonSignIn = (Button)findViewById(R.id.buttonSignIn);

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(LoginActivity.this, LoginRegistrationFormS1.class));
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                initializeData();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
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

    private void initializeData()
    {

        editNumber = (EditText)findViewById(R.id.editTextNumber);
        String strNumber = editNumber.getText().toString();
        editPass = (EditText)findViewById(R.id.editTextPassword);
        String strPass = editPass.getText().toString();

        DatabaseHelper helper = DatabaseHelper.getInstance(this);
        User user1 = helper.selectSpecificUser(1).get(0);

        String strUserPassword = user1.getPassword();
        int strUserNumber = user1.getMobile_no();

        if(strPass.equals("badpassword") & strNumber.equals("1234567"))
        {
            Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            myIntent.putExtra("testUserId", "test");
            startActivity(myIntent);
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
        }
    }
}
