package com.winnipegapp.examples.Notifications;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amari on 2016-06-14.
 */
public abstract class Notification {
    //  Field declaration.
    private String title;
    private String description;
    private int notificationId;
    private long notificationDate;
    private int notificationImage;

    /*
    * Object class for all notifications. All notification cards will inherit from this class.
    *
    * */
    public Notification (String title, String description, int notificationId, int notificationImage) {
        this.title = title;
        this.description = description;
        //  We need to increment this.
        this.notificationId = notificationId;
        this.notificationImage = notificationImage;

        this.notificationDate = System.currentTimeMillis();

        //  Date Usage:
        //  SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public long getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(long notificationDate) {
        this.notificationDate = notificationDate;
    }

    public int getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(int notificationImage) {
        this.notificationImage = notificationImage;
    }

}
