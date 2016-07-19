package com.winnipegapp.examples;


import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

// Some of the code was sourced from here: http://stackoverflow.com/questions/13739990/map-view-following-user-mylocationoverlay-type-functionality-for-android-maps/13753518#13753518

public class MapFragment extends Fragment implements android.location.LocationListener,OnMapReadyCallback {

    private GoogleMap mMap;
    private View view;
    private LocationManager locManager;
    private android.location.LocationListener locListener;
    private Location locDetails;
    private Context context;
    private FloatingActionButton fabbutton;
    private SupportMapFragment mapFragment;
    private MenuInflater inflateMenu;

    // added by Mauricio El Matador
    LatLng currentPosition;
    final int MY_PERMISSION_REQUEST_ACCESS_LOCATION = 123;

    boolean gpsEnabled;
    boolean netWorkEnabled;

    private final float LOCLEVEL = 15F;
    private final long MINTIME = 5000;

    double latitude;
    double longitude;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        context = getActivity();

        locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        gpsEnabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        netWorkEnabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        locListener = new android.location.LocationListener() {


            @Override
            public void onLocationChanged(Location loc) {

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
        };

        if (locManager != null)
        {
            if (gpsEnabled) {
                try {
                    locManager.requestLocationUpdates(locManager.GPS_PROVIDER, MINTIME, LOCLEVEL, locListener);
                } catch (SecurityException se) {

                }
            } else if (netWorkEnabled) {
                try {
                    locManager.requestLocationUpdates(locManager.NETWORK_PROVIDER, MINTIME, LOCLEVEL, locListener);
                } catch (SecurityException se) {

                }
            } else {
                // Print error message
            }
        } else {
            // Print error message
        }

        this.view = inflater.inflate(R.layout.fragment_map, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {

            mapFragment.getMapAsync(this);

        }

        fabbutton = (FloatingActionButton) view.findViewById(R.id.fab);
        registerForContextMenu(fabbutton);

        return this.view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Before creating the map, let's ask for permission to use LocationServices.
        // This can be done actually on onCreate, not here.

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_LOCATION);

            return;

        } else {

            // else, try to get last known location.

        }

        mMap = googleMap;

        // This method fetches the current location, assuming LocationServices are enabled.
        // If LocationServices not enabled then we should treat differently.
        getCurrentLocation();

        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException se) {

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

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public void createLocationRequest() {

        //example
        LocationRequest locateQuery = new LocationRequest();
        locateQuery.setInterval(10000);
        locateQuery.setFastestInterval(MINTIME);
        locateQuery.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

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
        List<String> providers = locManager.getProviders(true);

        Location bestLocation = null;

        for (String provider : providers) {

            Location l = null;
            try {
                l = locManager.getLastKnownLocation(provider);
            } catch (SecurityException se) {

            }

            if (l == null) {

                continue;

            }

            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {

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

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, LOCLEVEL));

            } catch (Exception e) {

                e.printStackTrace();

            }

            Log.i("Map Location:", currentPosition.toString());

        } else {

            Log.i("Location is Null:", "yess location is null!!!");

        }

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        inflateMenu = getActivity().getMenuInflater();
        inflateMenu.inflate(R.menu.mymapmenu, menu);
    }

}
