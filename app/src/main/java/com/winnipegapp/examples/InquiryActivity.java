package com.winnipegapp.examples;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class InquiryActivity extends AppCompatActivity {

    /**
     *   Class variables
     * */
    private Toolbar toolbar;
    private EditText textDescription, textLocation, textPostalCode;
    private LocationManager locManager;
    private Location location;
    final int MY_PERMISSION_REQUEST_ACCESS_LOCATION = 123;
    private ImageButton cameraButton;
    private Button submitButton;

    /**
    *   Instance variables used to write a new Inquiry in the database
    * */
    private int inquiry_id;
    private String inquiry_type;
    private String description;
    private String coordinates;
    private String creationDate;
    private String imageURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        textDescription = (EditText) findViewById(R.id.textDescription);
        textLocation = (EditText) findViewById(R.id.textLocation);
        textPostalCode = (EditText) findViewById(R.id.textPostalCode);

        cameraButton = (ImageButton) findViewById(R.id.imageCamera);
        submitButton = (Button) findViewById(R.id.buttonSubmit);

        locManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        setupToolbar();

        getLocationPermission();

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

    public void getLocationPermission() {

        /**
         *   Checks and, if necessary, asks for user location permission
         * */
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_LOCATION);

            return;

        } else {

            getCurrentLocation();

            return;

        }

    }

    public void getCurrentLocation() {

        /**
         *   Retrieves user location (getLastKnownLocation method) and Postal Code via Geocoder
         * */
        location = getLastKnownLocation();

        if (location != null) {

            Double lat = location.getLatitude();

            Double lng = location.getLongitude();

            /**
             *   Retrieves Lat and Lng from location passes to the corresponding variable
             * */
            coordinates = String.valueOf(lat) + "," + String.valueOf(lng);

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

            /**
             * ** TO DO ** Show dialog asking user to input Location and Postal Code manually
             * */
            Toast.makeText(getApplicationContext(), "Location Services are not enabled!", Toast.LENGTH_SHORT).show();

        }

    }

    public Location getLastKnownLocation() {

        /**
         *   Retrieves user location from available providers (accuracy)
         * */
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

    public void onClickCamera (View view) {

        /**
         *   ** TO DO ** Camera
         * */
        Toast.makeText(getApplicationContext(), "Perform Camera Action", Toast.LENGTH_SHORT).show();

    }

    public void initialiseData() {

        /**
         *   Retrieves date from the system and passes to the corresponding variable
         * */
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        creationDate = dateFormat.format(calendar.getTime());

        /**
         *   Retrieves inquiry type from the previous activity and passes to the corresponding variable
         * */
        Intent intent = getIntent();
        inquiry_type = intent.getStringExtra("default");

        /**
         *   Retrieves Description from the EditText and passes to the corresponding variable
         * */
        description = textDescription.getText().toString();

        imageURL = "http://123test.com";

    }

    public void onClickSubmit(View view) {

        initialiseData();

        DatabaseHelper helper = DatabaseHelper.getInstance(this);

        /**
         *   Retrieves size of the inquiries database, adds +1 and passes to the corresponding variable
         * */
        inquiry_id = helper.getInquiries().size() + 1;

        Inquiry inquiry = new Inquiry(inquiry_id, inquiry_type, description, imageURL, coordinates);

        helper.createInquiry(inquiry);

        Toast.makeText(getApplicationContext(), "Inquiry number " + inquiry_id + " was created.", Toast.LENGTH_LONG).show();

        for (int i = 0; i > helper.getInquiries().size(); i++) {

            Log.i("Inquiry number:", String.valueOf(i));

        }

        this.finish();

    }

}
