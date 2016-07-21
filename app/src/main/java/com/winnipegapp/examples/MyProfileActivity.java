package com.winnipegapp.examples;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {

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


}
