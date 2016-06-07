package com.winnipegapp.examples;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    Fragment fragment;
    DrawerLayout drawerLayout;
    AHBottomNavigation bottomBar;
    private List<Events> events;
    RecyclerView recyclerView;
    RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();

        setupNavigationView();

        //recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //recyclerView.setHasFixedSize(true);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //recyclerView.setLayoutManager(linearLayoutManager);

        initializeData();

        //adapter = new RVAdapter(events);

        //recyclerView.setAdapter(adapter);

        setupBottomBar();

    }

    private void setupBottomBar() {

        // Setting up a Bottom Navigation according to an existing lib.
        // Please refer to Gradle lib to the original author.

        // Cast the object from activity_main.xml
        bottomBar = (AHBottomNavigation)findViewById(R.id.bottomBar);

        // Add the options. Each option is a Class from the given custom lib I'm using.
        // (name, icon, color)
        final AHBottomNavigationItem home = new AHBottomNavigationItem("Home", R.drawable.ic_placeholder2, Color.parseColor("#3366CC"));
        AHBottomNavigationItem services = new AHBottomNavigationItem("Services", R.drawable.ic_placeholder2, Color.parseColor("#cc3333"));
        AHBottomNavigationItem calendar = new AHBottomNavigationItem("Calendar", R.drawable.ic_placeholder2, Color.parseColor("#cc9933"));
        AHBottomNavigationItem map = new AHBottomNavigationItem("Map", R.drawable.ic_placeholder2, Color.parseColor("#333399"));

        // Add the objects to the Bottom Navigation Bar object
        bottomBar.addItem(home);
        bottomBar.addItem(services);
        bottomBar.addItem(calendar);
        bottomBar.addItem(map);

        //  Enables reveal effect existing in the custom class
        bottomBar.setColored(true);

        bottomBar.setCurrentItem(0);

        if (fragment == null) {

            fragment = new MyHomeFragment();

            setupFragment();

        }

        // Sets listeners. Here's where we call fragments and others
        bottomBar.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {

                fragment = null;

                if (position == 0) {

                    toolbar.setBackgroundColor(Color.parseColor("#3366CC"));
                    fragment = new MyHomeFragment();


                } else if (position == 1) {

                    toolbar.setBackgroundColor(Color.parseColor("#cc3333"));
                    fragment = new ServicesFragment();

                } else if (position == 2) {

                    toolbar.setBackgroundColor(Color.parseColor("#cc9933"));
                    fragment = new CalendarFragment();

                } else if (position == 3) {

                    toolbar.setBackgroundColor(Color.parseColor("#333399"));
                    fragment = new MapFragment();

                }

                setupFragment();

            }

        });

    }

    private void setupFragment() {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment, fragment);

        transaction.addToBackStack(null);

        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.myprofile) {

            Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);

            startActivity(intent);

        } else if (id == R.id.settings) {

            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);

            startActivity(intent);

        } else if (id == R.id.about) {

            Intent intent = new Intent(MainActivity.this, AboutActivity.class);

            startActivity(intent);

        } else if (id == R.id.techsupport) {

            Intent intent = new Intent(MainActivity.this, TechSupportActivity.class);

            startActivity(intent);

        } else if (id == R.id.signout) {

            Toast.makeText(getApplicationContext(), "Perform signout event", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }

    private void initializeData() {

        events = new ArrayList<>();
        events.add(new Events("Card 1", "Winnipeg, MB", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquam vehicula nisi, at vulputate nibh aliquet mollis.", R.drawable.ic_placeholder));
        events.add(new Events("Card 2", "Winnipeg, MB", "Integer mollis libero ex, eget dictum neque efficitur faucibus. Proin eget magna quam. Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue.", R.drawable.ic_placeholder));
        events.add(new Events("Card 3", "Winnipeg, MB", "Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue.", R.drawable.ic_placeholder));
        events.add(new Events("Card 4", "Winnipeg, MB", "Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue. Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue. Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue. Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue. Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue. ", R.drawable.ic_placeholder));
        events.add(new Events("Card 5", "Winnipeg, MB", "Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue. Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue. Donec nec nisl volutpat, dictum tellus vitae, malesuada ligula. Praesent sed ullamcorper augue.", R.drawable.ic_placeholder));
        events.add(new Events("Card 6", "Winnipeg, MB", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquam vehicula nisi, at vulputate nibh aliquet mollis.", R.drawable.ic_placeholder));

    }

    private void setupToolbar() {

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setupNavigationView(){

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);

    }

}
