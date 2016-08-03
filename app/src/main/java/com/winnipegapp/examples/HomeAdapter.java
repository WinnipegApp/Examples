package com.winnipegapp.examples;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winnipegapp.examples.Notifications.*;
import com.winnipegapp.examples.Notifications.Notification;
import com.winnipegapp.examples.Notifications.PublicInquiryCard;


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

    List<Object> notifications;

    //  enum values for determining what kind of view to instantiate.
    private final int
    WARNING = 0,
    WEATHER = 1,
    EVENT = 2,
    PRIVATE_INQUIRY = 3,
    PUBLIC_INQUIRY = 4,
    ANNOUNCEMENT = 5,
    REMINDER = 6,
    CUSTOM = 7;

    /*
    *   Constructor for the Home adapter.
    * */
    public HomeAdapter(List<Object> notifications){
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

        if (notifications.get(position) instanceof Warning){
            return WARNING;
        }
        else if (notifications.get(position) instanceof Weather){
            return WEATHER;
        }
        else if(notifications.get(position) instanceof Event){
            return EVENT;
        }
        else if(notifications.get(position) instanceof  Inquiry){
            return PRIVATE_INQUIRY;
        }
        else if(notifications.get(position) instanceof  PublicInquiry){
            return PUBLIC_INQUIRY;
        }

        return -1;
    }

    /*
    * Based on the object type, creates a ViewHolder cardview appropriate for that notification.
     * */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType){
            case WARNING:
                View v1 = inflater.inflate(R.layout.cardview_warning, viewGroup, false);
                viewHolder = new WarningCard(v1);
                break;
            case WEATHER:
                View v2 = inflater.inflate(R.layout.cardview_weather, viewGroup, false);
                viewHolder = new WeatherCard(v2);
                break;
            case EVENT:
                View v3 = inflater.inflate(R.layout.cardview_event, viewGroup, false);
                viewHolder = new EventCard(v3);
                break;
            case PRIVATE_INQUIRY:
                View v4 = inflater.inflate(R.layout.cardview_privateinquiry, viewGroup, false);
                viewHolder = new PrivateInquiryCard(v4);
                break;
            case PUBLIC_INQUIRY:
                View v5 = inflater.inflate(R.layout.cardview_publicinquiry, viewGroup, false);
                viewHolder = new PublicInquiryCard(v5);
                break;
            default:
                View v = inflater.inflate(R.layout.cardview_basic, viewGroup, false);
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
            case WARNING:
                WarningCard warningCard = (WarningCard) viewHolder;
                configureWarningCard(warningCard, position);
                break;
            case WEATHER:
                WeatherCard weatherCard = (WeatherCard) viewHolder;
                configureWeatherCard(weatherCard, position);
                break;
            case EVENT:
                EventCard eventCard = (EventCard) viewHolder;
                configureEventCard(eventCard, position);
                break;
            case PRIVATE_INQUIRY:
                PrivateInquiryCard privateInquiryCard = (PrivateInquiryCard) viewHolder;
                configurePrivateInquiryCard(privateInquiryCard, position);
                break;
            case PUBLIC_INQUIRY:
                PublicInquiryCard publicInquiryCard = (PublicInquiryCard) viewHolder;
                configurePublicInquiryCard(publicInquiryCard, position);
                break;
        }
    }

    @Override
    public int getItemCount() { return notifications.size();}

    /*
    * Configures the title, description, and date of all notification cards.
    * */
    private void configureNotificationCard(NotificationCard notificationCard, int position){
        Notification notification = (Notification) notifications.get(position);
        if (notification != null){
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
            String dateString = sdf.format(notification.getNotificationDate());

            notificationCard.getNotificationTitle().setText(notification.getTitle());
            notificationCard.getNotificationDescription().setText(notification.getDescription());
            notificationCard.getNotificationDate().setText(dateString);
        }
    }

    /**
     * Dismisses an item from the RecyclerView.
     *
     * @param position The position of the item to be dismissed.
     * */
    public void dismissItem(int position){
        notifications.remove(position);
        this.notifyItemRemoved(position);
    }

    /*
    * Below are methods to configure the cardviews to have data after they are created.*/
    private void configureWarningCard(WarningCard warningCard, int position){
        Warning warning = (Warning) notifications.get(position);
        if (warning != null){
            configureNotificationCard(warningCard, position);
        }
    }
    private void configureWeatherCard(WeatherCard weatherCard, int position){
        Weather weather = (Weather) notifications.get(position);
        if (weather != null){
            configureNotificationCard(weatherCard, position);
        }
    }
    private void configureEventCard(EventCard eventCard, int position){
        Event event = (Event) notifications.get(position);
        if (event != null){
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
            String dateString = sdf.format(event.getStart_time());

            eventCard.getNotificationTitle().setText(event.getName());
            eventCard.getNotificationDescription().setText(event.getDescription());
            eventCard.getNotificationDate().setText(dateString);
            eventCard.getNotificationTime().setText(event.cheatHours());
            eventCard.getNotificationType().setText("Event");
        }
    }
    private void configurePrivateInquiryCard(PrivateInquiryCard privateInquiryCard, int position){
        Inquiry inquiry = (Inquiry) notifications.get(position);
        if (inquiry != null){
            configureNotificationCard(privateInquiryCard, position);
        }
    }
    private void configurePublicInquiryCard(PublicInquiryCard publicInquiryCard, int position){
        PublicInquiry inquiry = (PublicInquiry) notifications.get(position);
        if (inquiry != null){
            configureNotificationCard(publicInquiryCard, position);
        }
    }

}