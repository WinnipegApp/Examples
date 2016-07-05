package com.winnipegapp.examples;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();

        setupNavigationView();

        setupBottomBar();

    }

    private void setupBottomBar() {

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_placeholder));
        tabLayout.addTab(tabLayout.newTab().setText("Services").setIcon(R.drawable.ic_placeholder));
        tabLayout.addTab(tabLayout.newTab().setText("Calendar").setIcon(R.drawable.ic_placeholder));
        tabLayout.addTab(tabLayout.newTab().setText("Map").setIcon(R.drawable.ic_placeholder));

        setCurrentTabFragment(0);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int id = tabLayout.getSelectedTabPosition();

                tabLayout.getTabAt(id).setIcon(R.drawable.ic_placeholder);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {



            }

        });

    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(new MyHomeFragment());

                tabLayout.getTabAt(0).setIcon(R.drawable.ic_placeholder2);

                changeStatusBarColor(Color.parseColor("#285184"),Color.parseColor("#336699"),Color.parseColor("#336699"));

                break;

            case 1:
                replaceFragment(new ServicesFragment());

                tabLayout.getTabAt(1).setIcon(R.drawable.ic_placeholder2);

                changeStatusBarColor(Color.parseColor("#d86628"),Color.parseColor("#d05120"),Color.parseColor("#d05120"));

                break;

            case 2:
                replaceFragment(new CalendarFragment());

                tabLayout.getTabAt(2).setIcon(R.drawable.ic_placeholder2);

                changeStatusBarColor(Color.parseColor("#a3303b"),Color.parseColor("#b33c4a"),Color.parseColor("#b33c4a"));

                break;

            case 3:
                replaceFragment(new MapFragment());

                tabLayout.getTabAt(3).setIcon(R.drawable.ic_placeholder2);

                changeStatusBarColor(Color.parseColor("#4a6b26"),Color.parseColor("#5d8430"),Color.parseColor("#5d8430"));

                break;

        }

    }

    public void replaceFragment(Fragment fgmt) {

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment, fgmt);

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        ft.commit();

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

        changeStatusBarColor(Color.parseColor("#285184"),Color.parseColor("#336699"),Color.parseColor("#336699"));

        if (id == R.id.myprofile) {

            replaceFragment(new MyProfileFragment());

        } else if (id == R.id.settings) {

            replaceFragment(new SettingsFragment());

        } else if (id == R.id.about) {

            replaceFragment(new AboutFragment());

        } else if (id == R.id.techsupport) {

            replaceFragment(new TechSupportFragment());

        } else if (id == R.id.signout) {

            Toast.makeText(getApplicationContext(), "Perform signout event", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;

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

    public void changeStatusBarColor(int statusBarColor, int toolBarColor, int tabLayoutColor) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            getWindow().setStatusBarColor(statusBarColor);

        }

        toolbar.setBackgroundColor(toolBarColor);

        tabLayout.setBackgroundColor(tabLayoutColor);

    }

}
