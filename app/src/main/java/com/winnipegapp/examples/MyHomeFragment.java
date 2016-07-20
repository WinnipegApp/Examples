package com.winnipegapp.examples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winnipegapp.examples.Notifications.*;
import com.winnipegapp.examples.Notifications.Notification;


import java.util.ArrayList;
import java.util.List;


public class MyHomeFragment extends Fragment {


    private List<Notification> notifications;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_my_home, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        initializeData();

        HomeAdapter adapter = new HomeAdapter(notifications);

        recyclerView.setAdapter(adapter);

        return rootView;
    }


    private void initializeData() {

        DatabaseHelper helper = DatabaseHelper.getInstance(getActivity());

        //Creating test data to fill the recyclerview.
        notifications = new ArrayList<>();

        int j = helper.getInquiries().size();

        for (int i = 0; i < j; i++) {

             notifications.add(new Warning(helper.getInquiries().get(i).getType(), helper.getInquiries().get(i).getDescription(),01, R.drawable.ic_placeholder));

        }

        notifications.add(new Weather("23.3°C", "Partly Cloudy", 01, R.drawable.ic_partlycloudy));

    }
}
