package com.winnipegapp.examples;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class RoadTransitServicesFragment extends ListFragment {


    public RoadTransitServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_road_transit_services, container, false);

        String[] values = new String[] {"Pothole", "Traffic Light Failure", "Damaged/Stolen Sign", "Bus Shelter Vandalism"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_list_view, values);

        setListAdapter(adapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (id == 0) {

            replaceActivity("POTHOLE");

        } else if (id == 1) {

            replaceActivity("TRAFFIC LIGHT");

        } else if (id == 2) {

            replaceActivity("DAMAGED/STOLEN SIGN");

        } else if (id == 3) {

            replaceActivity("BUS SHELTER VANDALISM");

        }

    }

    public void replaceActivity (String serviceID) {

        Intent myIntent = new Intent(getActivity(), InquiryActivity.class);

        myIntent.putExtra("default", serviceID);

        getActivity().startActivity(myIntent);

    }
}
