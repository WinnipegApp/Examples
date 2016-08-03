package com.winnipegapp.examples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winnipegapp.examples.Notifications.*;


import java.util.ArrayList;
import java.util.List;


public class MyHomeFragment extends Fragment {


    private List<Object> notifications;
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

        ItemTouchHelper.Callback callback = new HomeSwipeHelper(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);



        return rootView;
    }


    private void initializeData() {

        DatabaseHelper helper = DatabaseHelper.getInstance(getActivity());

        //Creating test data to fill the recyclerview.
        notifications = new ArrayList<>();

        Warning warning = new Warning("Tornado Warning",
                "Environment Canada meteorologists are tracking what they describe as a " +
                        "severe thunderstorm that may produce a tornado in southern Manitoba.", 01);

        notifications.add(warning);

    }
}
