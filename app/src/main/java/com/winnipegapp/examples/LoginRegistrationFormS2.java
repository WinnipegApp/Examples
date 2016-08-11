package com.winnipegapp.examples;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoginRegistrationFormS2 extends AppCompatActivity
{
    CheckBox checkBoxPassword;
    Button buttonSignup;
    EditText inputTxtPassword, inputTxtConfirmPass, inputTxtNumber, inputTxtCode, inputTxtEmail;
    Toolbar toolbar;
    TextView backBUtton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration_form_s2);

        setupToolbar();

        buttonSignup = (Button)findViewById(R.id.buttonSignUp);
        inputTxtPassword = (EditText)findViewById(R.id.editPassword);
        inputTxtConfirmPass = (EditText)findViewById(R.id.editConfirm);
        inputTxtNumber = (EditText)findViewById(R.id.editNumber);
        inputTxtCode = (EditText)findViewById(R.id.editCode);
        inputTxtEmail = (EditText)findViewById(R.id.editEmail);
        checkBoxPassword = (CheckBox)findViewById(R.id.passwordCheck);
        backBUtton = (TextView)findViewById(R.id.backTextView);

        inputTxtNumber.getBackground().mutate().setColorFilter(getResources().getColor(R.color.color_editText_bottom), PorterDuff.Mode.SRC_ATOP);

        buttonSignup.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(inputTxtNumber.getText().toString().equals("") &&
                        inputTxtCode.getText().toString().equals("") &&
                        inputTxtEmail.getText().toString().equals("") &&
                        inputTxtPassword.getText().toString().equals("") &&
                        inputTxtConfirmPass.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter all the text field", Toast.LENGTH_SHORT).show();
                }
                else if(inputTxtNumber.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
                }
                else if(inputTxtCode.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter your code", Toast.LENGTH_SHORT).show();
                }
                else if(inputTxtEmail.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter your email address", Toast.LENGTH_SHORT).show();
                }
                else if(inputTxtPassword.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else if(inputTxtConfirmPass.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "The entered password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBoxPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                {
                    inputTxtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    inputTxtConfirmPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    inputTxtPassword.setInputType(129);
                    inputTxtConfirmPass.setInputType(129);
                }
            }
        });

        backBUtton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(LoginRegistrationFormS2.this, LoginRegistrationFormS1.class));
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginRegistrationFormS2.this, LoginActivity.class));
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
