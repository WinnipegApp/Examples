package com.winnipegapp.examples;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class RemovalServices extends ListFragment {



    public RemovalServices() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] values = new String[] {"Grafitti", "Snow", "Hazardous Materials", "Fallen Tree", "Dead Animal", "Other"};

        // possibly working with custom layout (custom_list_view.xml)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_list_view, values);

        setListAdapter(adapter);

        return inflater.inflate(R.layout.fragment_removal_services, container, false);

    }

}
