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
import android.widget.Toast;


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

            replaceFragment(new InquiryFragment(), "GRAFFITI");

        } else if (id == 1) {

            replaceFragment(new InquiryFragment(), "SNOW");

        } else if (id == 2) {

            replaceFragment(new InquiryFragment(), "HAZARDOUS MATERIALS");

        } else if (id == 3) {

            replaceFragment(new InquiryFragment(), "FALLEN TREE");

        } else if (id == 4) {

            replaceFragment(new InquiryFragment(), "DEAD ANIMAL");

        } else if (id == 5) {

            replaceFragment(new InquiryFragment(), "OTHER");

        }

    }

    public void replaceFragment(Fragment fgmt, String serviceID) {

        FragmentManager fm = getFragmentManager();

        Bundle bundle = new Bundle();

        bundle.putString("Service", serviceID);

        fgmt.setArguments(bundle);

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment, fgmt);

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        ft.commit();

    }

}
