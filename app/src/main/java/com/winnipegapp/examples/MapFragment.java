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

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapFragment extends Fragment implements android.location.LocationListener,OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locManager;
    private Location locDetails;
    FloatingActionButton actionButton;
    private SupportMapFragment mapFragment;
    private boolean[] selectedFilters;

    LatLng currentPosition;
    final int MY_PERMISSION_REQUEST_ACCESS_LOCATION = 123;

    boolean gpsEnabled;
    boolean netWorkEnabled;

    double latitude;
    double longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        gpsEnabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        netWorkEnabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

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

        initialiseData();

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
        List<String> providers = locManager.getProviders(true);

        Location bestLocation = null;

        for (String provider : providers) {

            Location l = null;

            try {

                l = locManager.getLastKnownLocation(provider);

            } catch (SecurityException se) {

                se.printStackTrace();

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

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 15));

            } catch (Exception e) {

                e.printStackTrace();

            }

            Log.i("Map Location:", currentPosition.toString());

        } else {

            Log.i("Location is Null:", "yes location is null!!!");

        }

    }

    private void initialiseData() {

        if (mMap != null) {

            loadUserFilters("selectedFilters", getActivity());

        }

    }

    public boolean[] loadUserFilters(String arrayName, Context mContext) {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("selectedFilters", 0);

        int size = sharedPreferences.getInt(arrayName + "_size", 0);

        selectedFilters = new boolean[size];

        for (int i = 0; i < size; i++)

            selectedFilters[i] = sharedPreferences.getBoolean(arrayName + "_" + i, false);

        return selectedFilters;

    }

}
