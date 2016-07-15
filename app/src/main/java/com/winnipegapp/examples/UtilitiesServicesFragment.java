package com.winnipegapp.examples;


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


public class UtilitiesServicesFragment extends ListFragment {


    public UtilitiesServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_utilities_services, container, false);

        String[] values = new String[] {"Gas & Electricity", "Water"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_list_view, values);

        setListAdapter(adapter);

        return rootView;
    }

    public void replaceFragment(Fragment fgmt, String serviceID) {

        FragmentManager fm = getFragmentManager();

        Bundle bundle = new Bundle();

        bundle.putString("Utility", serviceID);

        fgmt.setArguments(bundle);

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment, fgmt);

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        ft.commit();

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (id == 0) {

            replaceFragment(new UtilityReadFragment(), "GAS & ELECTRICITY");

        } else {

            replaceFragment(new UtilityReadFragment(), "WATER");

        }

    }
}
