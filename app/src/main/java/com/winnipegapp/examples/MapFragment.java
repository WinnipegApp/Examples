package com.winnipegapp.examples;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

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
import java.util.Arrays;
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
    private List<LocationDetails> locations = new ArrayList<>();

    LatLng currentPosition;
    final int MY_PERMISSION_REQUEST_ACCESS_LOCATION = 123;

    final float ZOOMLEV = 10;

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
                mapMenu.setTargetFragment(MapFragment.this,1);
                mapMenu.show(getFragmentManager(), "dialog");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == Activity.RESULT_OK) {
            mMap.clear();
            addMarkers();
            //Log.i("MapFragment", MapMenu.getSelectedFilters(getActivity())[0]?"1":"0");
        }
        super.onActivityResult(requestCode, resultCode, data);
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

        addMarkers();

        // This method fetches the current location, assuming LocationServices are enabled.
        // If LocationServices not enabled then we should treat differently.
        getCurrentLocation();

        try {

            mMap.setMyLocationEnabled(true);

        } catch (SecurityException se) {
            se.printStackTrace();
        }
    }
    private void addMarkers(){
        boolean[] filters = MapMenu.getSelectedFilters(getActivity());
        for(int i = 0; i < filters.length; i++){
            if(filters[i]){
                for (int j = 0; j < locations.size(); j++) {
                    LocationDetails item = locations.get(j);
                    if(item.getCategory()==i) {
                        mMap.addMarker(new MarkerOptions()
                                .position(item.getLatLng())
                                .title(item.getName()));
                    }
                }
            }
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

        // Calls the database
        dbh = DatabaseHelper.getInstance(getActivity());

        // Creates dummy locations for testing (execute when you re-install the APK
        createLocations();

        locations = dbh.getLocations();
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

        dbh.createLocation(new LocationDetails(0, 0, "St. Vital Pool", "49.859372, -97.100041"));
        dbh.createLocation(new LocationDetails(1, 0, "Provencher Pool", "49.890665, -97.117877"));
        dbh.createLocation(new LocationDetails(2, 0, "Happyland Pool", "49.881564, -97.101610"));
        dbh.createLocation(new LocationDetails(3, 0, "Pan Am Pool", "49.8552751, -97.17289"));
        dbh.createLocation(new LocationDetails(4, 0, "Seven Oaks Swimming Pool", "49.9541013, -97.1762658"));
        dbh.createLocation(new LocationDetails(5, 0, "St James Civic Centre Pool", "49.885907, -97.2366956"));
        dbh.createLocation(new LocationDetails(6, 0, "Kildonan Park Outdoor Pool", "49.9423885, -97.1048312"));
        dbh.createLocation(new LocationDetails(7, 0, "Fort Garry Lion’s Outdoor Pool", "49.840571, -97.152733"));
        dbh.createLocation(new LocationDetails(8, 0, "Westdale Outdoor Pool", "49.86125, -97.3177646"));
        dbh.createLocation(new LocationDetails(9, 0, "Windsor Park Outdoor Pool", "49.8624316, -97.0749827"));

        dbh.createLocation(new LocationDetails(10, 1, "Tuxedo Golf Club", "49.863355, -97.2371127"));
        dbh.createLocation(new LocationDetails(11, 1, "John Blumberg Golf Course", "49.8739363, -97.3590642"));
        dbh.createLocation(new LocationDetails(12, 1, "Breezy Bend Country Club", "49.856, -97.3735887"));
        dbh.createLocation(new LocationDetails(13, 1, "Wildewood Golf Course", "49.848582, -97.1460917"));
        dbh.createLocation(new LocationDetails(14, 1, "Windsor Park Golf Course", "49.8599007, -97.1018567"));
        dbh.createLocation(new LocationDetails(15, 1, "Kildonan Park Golf Course", "49.9449835, -97.1131347"));

        dbh.createLocation(new LocationDetails(16, 2, "Westwood Library", "49.878315, -97.3054427"));
        dbh.createLocation(new LocationDetails(17, 2, "Millennium Library", "49.891972, -97.1443457"));
        dbh.createLocation(new LocationDetails(18, 2, "Charleswood Library", "49.857063, -97.2859897"));
        dbh.createLocation(new LocationDetails(19, 2, "Windsor Park Library", "49.8610378, -97.0795467"));
        dbh.createLocation(new LocationDetails(20, 2, "St. Boniface Library", "49.891681, -97.1271166"));
        dbh.createLocation(new LocationDetails(21, 2, "Transcona Library", "49.896123, -97.0057897"));
        dbh.createLocation(new LocationDetails(22, 2, "St. Vital Library", "49.851979,-97.1155597"));
        dbh.createLocation(new LocationDetails(23, 2, "Henderson Library", "49.9362452,-97.0968456"));

        dbh.createLocation(new LocationDetails(24, 3, "Grace Hospital", "49.8823448, -97.278943"));
        dbh.createLocation(new LocationDetails(25, 3, "Victoria General Hospital", "49.8067051, -97.1548719"));
        dbh.createLocation(new LocationDetails(26, 3, "Seven Oaks General Hospital", "49.954856, -97.1518657"));
        dbh.createLocation(new LocationDetails(27, 3, "Concordia Hospital", "49.9133456, -97.0666764"));
        dbh.createLocation(new LocationDetails(28, 3, "St. Boniface Hospital", "49.8841313, -97.1270477"));
        dbh.createLocation(new LocationDetails(29, 3, "Health Sciences Centre Winnipeg", "49.9030599, -97.1595927"));
        dbh.createLocation(new LocationDetails(30, 3, "Misericordia Health Centre (Urgent Care)", "49.8796316, -97.1626247"));
    }
}
