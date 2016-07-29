package com.winnipegapp.examples;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
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
import java.util.Locale;

public class MapFragment extends Fragment implements android.location.LocationListener,OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Location locDetails;
    FloatingActionButton actionButton;
    private SupportMapFragment mapFragment;
    private boolean[] selectedFilters;

    private DatabaseHelper dbh;
    private List<LocationDetails> locations;

    LatLng currentPosition;
    final int MY_PERMISSION_REQUEST_ACCESS_LOCATION = 123;

    final float ZOOMLEV = 10f;

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

//        for(int i = 0; i >= locations.size(); i++){
//            if(locDetails.getCategory().isChecked() == ){
//
//            }
//        }

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

        locDetails = getLastKnownLocation();

        if (locDetails != null) {

            latitude = locDetails.getLatitude();
            longitude = locDetails.getLongitude();

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

        dbh = DatabaseHelper.getInstance(getActivity());

        createLocations();

        locations = dbh.getLocations();
        // Calls the database
        dbh = DatabaseHelper.getInstance(getActivity());

        // Creates dummy locations for testing (execute when you re-install the APK
        //createLocations();

        // Creates and initialises the List
        List<LocationDetails> locations = new ArrayList<>();

        int j = dbh.getLocations().size();

        for (int i = 0; i < j; i++) {

            locations.add(dbh.getLocations().get(i));

        }

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

        dbh.createLocation(new LocationDetails(0, "Pools", "St. Vital Pool", "49.859372, -97.100041"));
        dbh.createLocation(new LocationDetails(1, "Pools", "Provencher Pool", "49.890665, -97.117877"));
        dbh.createLocation(new LocationDetails(2, "Pools", "Happyland Pool", "49.881564, -97.101610"));
        dbh.createLocation(new LocationDetails(9, "Pools", "Pan Am Pool", "49.8552751, -97.17289"));
        dbh.createLocation(new LocationDetails(10, "Pools", "Seven Oaks Swimming Pool", "49.9541013, -97.1762658"));
        dbh.createLocation(new LocationDetails(11, "Pools", "St James Civic Centre Pool", "49.885907, -97.2366956"));
        dbh.createLocation(new LocationDetails(12, "Pools", "Kildonan Park Outdoor Pool", "49.9423885, -97.1048312"));
        dbh.createLocation(new LocationDetails(13, "Pools", "Fort Garry Lion’s Outdoor Pool", "49.840571, -97.152733"));
        dbh.createLocation(new LocationDetails(14, "Pools", "Westdale Outdoor Pool", "49.86125, -97.3177646"));
        dbh.createLocation(new LocationDetails(15, "Pools", "Windsor Park Outdoor Pool", "49.8624316, -97.0749827"));

        dbh.createLocation(new LocationDetails(16, "Golf Courses", "Tuxedo Golf Club", "49.863355, -97.2371127"));
        dbh.createLocation(new LocationDetails(17, "Golf Courses", "John Blumberg Golf Course", "49.8739363, -97.3590642"));
        dbh.createLocation(new LocationDetails(18, "Golf Courses", "Breezy Bend Country Club", "49.856, -97.3735887"));
        dbh.createLocation(new LocationDetails(19, "Golf Courses", "Wildewood Golf Course", "49.848582, -97.1460917"));
        dbh.createLocation(new LocationDetails(21, "Golf Courses", "Windsor Park Golf Course", "49.8599007, -97.1018567"));
        dbh.createLocation(new LocationDetails(22, "Golf Courses", "Kildonan Park Golf Course", "49.9449835, -97.1131347"));

        dbh.createLocation(new LocationDetails(23, "Post Offices", "Canada Post", "49.8068907,-97.2576962"));
        dbh.createLocation(new LocationDetails(25, "Post Offices", "Canada Post", "49.8626939,-97.1571026"));
        dbh.createLocation(new LocationDetails(26, "Post Offices", "Canada Post", "49.8674302,-97.2142367"));
        dbh.createLocation(new LocationDetails(27, "Post Offices", "Canada Post ", "49.9089262,-97.1677456"));
     //   dbh.createLocation(new LocationDetails(28, "Post Offices", "Canada Post", "49.8843777,-97.1718655"));
//        dbh.createLocation(new LocationDetails(29, "Post Offices", "Canada Post", "49.8785362,-97.3077693"));
//        dbh.createLocation(new LocationDetails(30, "Post Offices", "Canada Post", "49.8838378,-97.1487339"));

        dbh.createLocation(new LocationDetails(31, "Emergency Rooms", "Grace Hospital", "49.8823448, -97.278943"));
        dbh.createLocation(new LocationDetails(32, "Emergency Rooms", "Victoria General Hospital", "49.8067051, -97.1548719"));
        dbh.createLocation(new LocationDetails(33, "Emergency Rooms", "Seven Oaks General Hospital", "49.954856, -97.1518657"));
        dbh.createLocation(new LocationDetails(34, "Emergency Rooms", "Concordia Hospital", "49.9133456, -97.0666764"));
        dbh.createLocation(new LocationDetails(35, "Emergency Rooms", "St. Boniface Hospital", "49.8841313, -97.1270477"));
        dbh.createLocation(new LocationDetails(36, "Emergency Rooms", "Health Sciences Centre Winnipeg", "49.9030599, -97.1595927"));
        dbh.createLocation(new LocationDetails(37, "Emergency Rooms", "Misericordia Health Centre (Urgent Care)", "49.8796316, -97.1626247"));

    }

//    @Override
//    protected void onHandleIntent(Intent intent) {
//        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//    }
}

