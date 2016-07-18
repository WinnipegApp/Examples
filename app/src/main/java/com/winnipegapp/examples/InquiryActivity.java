package com.winnipegapp.examples;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class InquiryActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText textDescription;
    private TextView textLocation, textPostalCode;

    private LocationManager locManager;
    private LatLng currentPosition;
    private Location location;
    final int MY_PERMISSION_REQUEST_ACCESS_LOCATION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        textDescription = (EditText) findViewById(R.id.textDescription);
        textLocation = (TextView) findViewById(R.id.textLocation);
        textPostalCode = (TextView) findViewById(R.id.textPostalCode);

        locManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        setupToolbar();

        getLocationPermission();

    }

    private void setupToolbar() {

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void getLocationPermission() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_LOCATION);

            return;

        } else {

            getCurrentLocation();

            return;

        }

    }

    public void getCurrentLocation() {

        location = getLastKnownLocation();

        if (location != null) {

            Double lat = location.getLatitude();
            Double lng = location.getLongitude();

            try {

                Geocoder geocoder = new Geocoder(this);

                geocoder.getFromLocation(lat, lng, 1);

                List<Address> listAddresses = geocoder.getFromLocation(lat, lng, 1);

                if (listAddresses != null && listAddresses.size() > 0) {

                    textLocation.setText(listAddresses.get(0).getAddressLine(0));

                    textPostalCode.setText(listAddresses.get(0).getPostalCode());

                }


            } catch (Exception e) {

                e.printStackTrace();

            }

        } else {

            // ** TO DO ** Show dialog asking user to input Location and Postal Code manually
            Toast.makeText(getApplicationContext(), "Location Services are not enabled!", Toast.LENGTH_SHORT).show();

        }

    }

    public Location getLastKnownLocation() {

        List<String> providers = locManager.getProviders(true);

        Location bestLocation = null;

        for (String provider : providers) {

            Location l = locManager.getLastKnownLocation(provider);

            if (l == null) {

                continue;

            }

            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {

                bestLocation = l;

            }

        }

        return bestLocation;

    }

}
