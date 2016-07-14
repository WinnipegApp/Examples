package com.winnipegapp.examples;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
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

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private View view;
    private LocationManager locManager;
    private android.location.LocationListener locListener;
    private Location location;
    private Context context;

    boolean gpsEnabled;
    boolean netWorkEnabled;

    private final float MINDISTANCE = 15F;
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

                location = loc;
                //update

                latitude = location.getLatitude();
                longitude = location.getLongitude();

                LatLng latLng = new LatLng(latitude, longitude);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
                mMap.moveCamera(cameraUpdate);

                if ( Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return  ;
                }
                locManager.removeUpdates(this);
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
                    locManager.requestLocationUpdates(locManager.GPS_PROVIDER, MINTIME, MINDISTANCE, locListener);
                } catch (SecurityException se) {

                }
            } else if (netWorkEnabled) {
                try {
                    locManager.requestLocationUpdates(locManager.NETWORK_PROVIDER, MINTIME, MINDISTANCE, locListener);
                } catch (SecurityException se) {

                }
            } else {
                // Print error message
            }
        } else {
            // Print error message
        }

        this.view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {

            mapFragment.getMapAsync(this);

        }

        FloatingActionButton fabbottom = (FloatingActionButton) view.findViewById(R.id.fab);
        assert fabbottom != null;
        fabbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        return this.view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        createLocationRequest();

        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        } else {
            latitude = 49.899721;
            longitude = -97.1375084;
        }

        CameraUpdate updateLocation = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), MINDISTANCE);
        mMap.moveCamera(updateLocation);

        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException se) {

        }

//        LatLng currentPosition = new LatLng(latitude, longitude);
//        mMap.addMarker(new MarkerOptions().position(currentPosition).title("Current Location"));
//        LatLng initPos = new LatLng(49.895497, -97.138471);
//
//        CameraUpdate center = CameraUpdateFactory.newLatLng(initPos);
//
//        CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);
//
//        mMap.moveCamera(center);
//
//        mMap.animateCamera(zoom);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

    }

    @Override
    public void onPause() {
        super.onPause();

//        if(locManager != null){
//            locManager.removeUpdates(this);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onStart() {
        super.onStart();

//        if(apiMapCLient == null){
//            apiMapCLient = new GoogleApiClient.Builder(this);
//
//        }
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
}
