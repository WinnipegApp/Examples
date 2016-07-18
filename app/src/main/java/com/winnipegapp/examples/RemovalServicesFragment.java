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


public class RemovalServicesFragment extends ListFragment {



    public RemovalServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] values = new String[] {"Graffiti", "Snow", "Hazardous Materials", "Fallen Tree", "Dead Animal", "Other"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_list_view, values);

        setListAdapter(adapter);

        return inflater.inflate(R.layout.fragment_removal_services, container, false);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (id == 0) {

            replaceActivity("GRAFFITI");

        } else if (id == 1) {

            replaceActivity("SNOW");

        } else if (id == 2) {

            replaceActivity("HAZARDOUS MATERIALS");

        } else if (id == 3) {

            replaceActivity("FALLEN TREE");

        } else if (id == 4) {

            replaceActivity("DEAD ANIMAL");

        } else if (id == 5) {

            replaceActivity("OTHER");

        }

    }

    public void replaceActivity (String serviceID) {

        Intent myIntent = new Intent(getActivity(), InquiryActivity.class);

        myIntent.putExtra("default", serviceID);

        getActivity().startActivity(myIntent);

    }

}
