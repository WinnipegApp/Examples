package com.winnipegapp.examples;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winnipegapp.examples.Notifications.*;
import com.winnipegapp.examples.Notifications.Notification;


import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Mauricio on 2016-05-30.
 */
/*
* Reconfigured by Amari Len
* June 22, 2016
* */


public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Notification> notifications;

    //  enum values for determining what kind of view to instantiate.
    private final int
    WARNING = 0,
    WEATHER = 1,
    EVENT = 2,
    PRIVATE_INQUIRY = 3,
    PUBLIC_INQUIRY = 4,
    Announcement = 5,
    Reminder = 6,
    CUSTOM = 7;

    /*
    *   Constructor for the Home adapter.
    * */
    public HomeAdapter(List<Notification> notifications){
        this.notifications = notifications;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    /*
    * Used to derive what kind of view to create. Corresponds to the enums above.
    * */
    public int getItemViewType(int position){
        if (notifications.get(position) instanceof Weather){
            return WEATHER;
        }

        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType){
            case WEATHER:
                View v2 = inflater.inflate(R.layout.cardview_weather, viewGroup, false);
                viewHolder = new WeatherCard(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.notification_header, viewGroup, false);
                viewHolder = new NotificationCard(v);
                break;
        }
        return viewHolder;
    }

    /*
    * Operations occur when new viewholders are bound to the RecyclerView,
    * configuring their details.
    * */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position){
        switch (viewHolder.getItemViewType() ){
            case WEATHER:
                WeatherCard weatherCard = (WeatherCard) viewHolder;
                configureWeatherCard(weatherCard, position);
                break;
        }
    }

    @Override
    public int getItemCount() { return notifications.size();}




    /*
    * Configures the title, description, and date of all notification cards.
    * */
    private void configureNotificationCard(NotificationCard notificationCard, int position){
        Notification notification = notifications.get(position);
        if (notification != null){
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
            String dateString = sdf.format(notification.getNotificationDate());


            notificationCard.getNotificationTitle().setText(notification.getTitle());
            notificationCard.getNotificationDescription().setText(notification.getDescription());
            notificationCard.getNotificationDate().setText(dateString);
            notificationCard.getNotificationImage().setImageResource(notification.getNotificationImage());
        }
    }

    private void configureWeatherCard(WeatherCard weatherCard, int position){
        Weather weather = (Weather) notifications.get(position);
        if (weather != null){
            configureNotificationCard((NotificationCard)weatherCard, position);

        }
    }

}