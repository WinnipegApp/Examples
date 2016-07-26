package com.winnipegapp.examples;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.winnipegapp.examples.Notifications.Warning;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements android.location.LocationListener,OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Location location;
    FloatingActionButton actionButton;
    private SupportMapFragment mapFragment;
    private boolean[] selectedFilters;

    private DatabaseHelper dbh;
    private List<LocationDetails> locations;

    LatLng currentPosition;
    final int MY_PERMISSION_REQUEST_ACCESS_LOCATION = 123;

    final float ZOOMLEV = 14f;

    boolean gpsEnabled;
    boolean netWorkEnabled;

    double latitude;
    double longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        netWorkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        selectedFilters = new boolean[4];

        actionButton = (FloatingActionButton) rootView.findViewById(R.id.fab);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MapMenu mapMenu = new MapMenu();

                mapMenu.show(getFragmentManager(), "default");

            }
        });

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {

            mapFragment.getMapAsync(this);

        }

        dbh = DatabaseHelper.getInstance(getContext());

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_LOCATION);

            return;

        } else {

            // else, try to get last known location.

        }

        mMap = googleMap;


        for(int i = 0; i < locations.size(); i++) {
            mMap.addMarker(new MarkerOptions()
                    .position(locations.get(i).getLatLng())
                    .title(locations.get(i).getName()));
        }

        // This method fetches the current location, assuming LocationServices are enabled.
        // If LocationServices not enabled then we should treat differently.
        getCurrentLocation();

        try {

            mMap.setMyLocationEnabled(true);

        } catch (SecurityException se) {

            se.printStackTrace();

        }

    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();

        initialiseData();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public Location getLastKnownLocation() {

        // Listing all provides, like GPS, Network, etc.
        List<String> providers = locationManager.getProviders(true);

        Location bestLocation = null;

        for (String provider : providers) {

            Location l = null;
            try {
                l = locationManager.getLastKnownLocation(provider);
            } catch (SecurityException se) {
                se.printStackTrace();
            }

            if (l == null) {

                continue;

            }

            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {

                // Found best last known location: %s", l);
                bestLocation = l;

            }

        }

        return bestLocation;

    }

    public void getCurrentLocation() {

        location = getLastKnownLocation();

        if (location != null) {

            latitude = location.getLatitude();
            longitude = location.getLongitude();

            currentPosition = new LatLng(latitude, longitude);

            try {

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, ZOOMLEV));

            } catch (Exception e) {

                e.printStackTrace();

            }

        } else {

            Log.i("locationIsNull:", "yes location is null!!!");

        }

    }

    private void initialiseData() {

        /**
        *  This method is incomplete (Mauricio)
        *
        *  It should create a List of Locations, retrieve from the helper (db) and
        *  filter according to the selectedFilters.
        *
        *  It should be executed when the map starts, resumes and when closing the filter dialog (MapMenu)
        *
        * */

        if (mMap != null) {

            loadUserFilters("selectedFilters", getActivity());

        }

<<<<<<< HEAD
        dbh = DatabaseHelper.getInstance(getActivity());

        createLocations();

        locations = dbh.getLocations();
=======
        // Calls the database
        helper = DatabaseHelper.getInstance(getActivity());

        // Creates dummy locations for testing (execute when you re-install the APK
        //createLocations();

        // Creates and initialises the List
        List<LocationDetails> locations = new ArrayList<>();

        int j = helper.getLocations().size();

        for (int i = 0; i < j; i++) {

            locations.add(helper.getLocations().get(i));

        }
>>>>>>> origin/master



    }

    public boolean[] loadUserFilters(String arrayName, Context mContext) {

        /**
        *  Load filters from the phone Sharedpreferences
        *
        * */

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("selectedFilters", 0);

        int size = sharedPreferences.getInt(arrayName + "_size", 0);

        selectedFilters = new boolean[size];

        for (int i = 0; i < size; i++)

            selectedFilters[i] = sharedPreferences.getBoolean(arrayName + "_" + i, false);

        return selectedFilters;

    }

    public void createLocations() {

<<<<<<< HEAD
        dbh.createLocation(new LocationDetails(0, "Pools", "St. Vital Pool", "49.859372, -97.100041"));
        dbh.createLocation(new LocationDetails(1, "Pools", "Provencher Pool", "49.890665, -97.117877"));
        dbh.createLocation(new LocationDetails(2, "Pools", "Happyland Pool", "49.881564, -97.101610"));
        dbh.createLocation(new LocationDetails(9, "Pools", "Pan Am Pool", "49.8552751, -97.17289"));
//        dbh.createLocation(new LocationDetails(4, "Pools", "Happyland Pool", "49.881564, -97.101610"));
//        dbh.createLocation(new LocationDetails(5, "Pools", "Happyland Pool", "49.881564, -97.101610"));
//        dbh.createLocation(new LocationDetails(6, "Pools", "Happyland Pool", "49.881564, -97.101610"));
//        dbh.createLocation(new LocationDetails(7, "Pools", "Happyland Pool", "49.881564, -97.101610"));
//        dbh.createLocation(new LocationDetails(8, "Pools", "Happyland Pool", "49.881564, -97.101610"));
=======
        /**
        *   Creates dummy locations to test the database
        *
        * */

        LocationDetails example0 = new LocationDetails(0, "Pools", "St. Vital Pool", "49.859372, -97.100041");
        LocationDetails example1 = new LocationDetails(1, "Pools", "Provencher Pool", "49.890665, -97.117877");
        LocationDetails example2 = new LocationDetails(2, "Pools", "Happyland Pool", "49.881564, -97.101610");

        helper.createLocation(example0);
        helper.createLocation(example1);
        helper.createLocation(example2);
>>>>>>> origin/master

    }

}
