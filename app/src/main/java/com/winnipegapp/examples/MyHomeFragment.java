package com.winnipegapp.examples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winnipegapp.examples.Notifications.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL;


public class MyHomeFragment extends Fragment {


    private List<Object> notifications;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;

    boolean bLogin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_my_home, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        initializeData();

        adapter = new HomeAdapter(notifications);

        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new HomeSwipeHelper(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);



        return rootView;
    }


    private void initializeData()
    {

        DatabaseHelper helper = DatabaseHelper.getInstance(getActivity());

        //Creating test data to fill the recyclerview.
        notifications = new ArrayList<>();

        Warning warning = new Warning("Tornado Warning",
                "Environment Canada meteorologists are tracking what they describe as a " +
                        "severe thunderstorm that may produce a tornado in southern Manitoba.", 01);


        Event event01 = new Event(01, "First Fridays in the Exchange",
                "FIRST FRIDAYS is a great way to get to know the Exchange District every 1st " +
                        "Friday of each month, all year round. Make a night of it-enjoy " +
                        "supper at a great café, talk to artists in their studios, attend Gallery" +
                        "openings and explore the unique shops. Step into your culture zone & come" +
                        " for a walk in Winnipeg's original downtown!", "123 Someplace");

        Event event02 = new Event(02, "Oviloo Tunnillie", "This exhibition is the first" +
                " retrospective of work by Oviloo Tunnillie (1949-2014), one of the most" +
                " respected Inuit sculptors from the Canadian Arctic. Bringing together some" +
                " 60 sculptures from private and museum collections in Canada and the US, the " +
                "development of her work is surveyed from the typical genre of finely-crafted " +
                "birds and animals in the 1970s, to her exploration of social issues in the 1980s, " +
                "to autobiographical themes in the 1990s.", "456 Somewhere");

        Event event03 = new Event(03,"Chagall", "Russian-born artist Marc Chagall was a pioneer of" +
                " modernism. Picasso proclaimed that after Matisse, \"Chagall will be the only " +
                "painter left who understands what colour really is.”Daphnis & Chloé, the latest" +
                " NGC@WAG collaboration, features 42 lithograph prints that showcase Chagall’s " +
                "unique style. Widely considered the crowning achievement of his career as a " +
                "printmaker, the series depicts the semi-erotic tale written by the Greek poet," +
                " Longus. Chagall created these colourful pieces in the 1950s, inspired by the" +
                " great love of his life, Valentina.", "789 Main");

        Inquiry inquiry01 = new Inquiry(01, "Graffiti Removal", "On the way home we found a bus" +
                "stop by the bus depot down town on Portage @ Vaughn had been vandalized.");

        MainActivity mActivity = (MainActivity)getActivity();
        bLogin = mActivity.getMyData();
        notifications.add(warning);
        notifications.add(event01);
        notifications.add(event02);
        if(bLogin)
        {
            notifications.add(inquiry01);
        }
        notifications.add(event03);
    }

    /**
     * Returns a psuedo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimim value
     * @param max Maximim value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public long generateRandomTime(){
        long currentDate = System.currentTimeMillis();

        return currentDate + ( 1+ (randInt(100000, 1000000)));

    }
}
