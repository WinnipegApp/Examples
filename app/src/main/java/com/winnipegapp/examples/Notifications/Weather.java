package com.winnipegapp.examples.Notifications;


import java.util.Date;

/**
 * Created by Amari on 2016-06-21.
 */
public class Weather extends Notification {


    public Weather(String title, String description, int notificationId, int notificationImage) {
        super(title, description, notificationId);

    }


    @Override
    public int compareTo(Object another) {
        if(another instanceof Warning ) return -1;
        else return 1;
    }
}
