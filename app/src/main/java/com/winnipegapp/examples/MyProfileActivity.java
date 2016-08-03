package com.winnipegapp.examples;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {

    TextView txtName, txtAddress, txtPhone, txtEmail, txtPostal, txtSnowZone;
    Toolbar toolbar;
    TextView editProfileText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        setupToolbar();
        editProfileText = (TextView)findViewById(R.id.editProfile);
        editProfileText.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(MyProfileActivity.this, EditProfileActivity.class));
            }
        });

        InitializeProfile();

    }

    private void setupToolbar()
    {

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        // Show menu icon
        final ActionBar actionBar = getSupportActionBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

    }
    private void InitializeProfile()
    {

        Intent mIntent = getIntent();
        String sValue = mIntent.getStringExtra("testUserId");

        DatabaseHelper helper = DatabaseHelper.getInstance(this);
        User user1 = helper.selectSpecificUser(1).get(0);

        txtName = (TextView)findViewById(R.id.textName );
        txtAddress = (TextView)findViewById(R.id.textAddress);
        txtPhone = (TextView)findViewById(R.id.textNumber);
        txtEmail = (TextView)findViewById(R.id.textEmail);
        txtPostal = (TextView)findViewById(R.id.textPostal);
        txtSnowZone = (TextView)findViewById(R.id.textSnowZone);

        txtName.setText(user1.getFull_name());
        txtAddress.setText(user1.getAddress());
        txtPhone.setText(Integer.toString(user1.getMobile_no()));
        txtEmail.setText("jjones.gmail");
        txtPostal.setText(user1.getPostal_code());
        txtSnowZone.setText(user1.getSnow_zone());

    }
}
