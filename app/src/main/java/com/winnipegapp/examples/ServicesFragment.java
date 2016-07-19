package com.winnipegapp.examples;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ServicesFragment extends Fragment {

    private View view;
    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence tabTitle[] = {"Removal", "Utilities", "Road & Transit"};
    private int qtyTabs = 3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_services, container, false);

        setupToolbar();

        return view;

    }

    public void setupToolbar() {

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        adapter =  new ViewPagerAdapter(getActivity().getSupportFragmentManager(), tabTitle, qtyTabs);

        pager = (ViewPager) view.findViewById(R.id.pager);

        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);

        tabs.setDistributeEvenly(true);

/*        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {

                return getResources().getColor(R.color.colorSecondaryText);

            }
        });*/

        tabs.setViewPager(pager);

    }

}
