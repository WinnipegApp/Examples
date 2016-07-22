package com.winnipegapp.examples.Notifications;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amari on 2016-06-14.
 */
public abstract class Notification implements NotificationInterface{
    //  Field declaration.
    private String title;
    private String description;
    private int notificationId;
    private long notificationDate;
    private int notificationImage;
    private String notificationType;

    /*
    * Object class for all notifications. All notification cards will inherit from this class.
    *
    * */
    public Notification (String title, String description, int notificationId,
                         int notificationImage) {
        this.title = title;
        this.description = description;
        this.notificationId = notificationId;
        this.notificationImage = notificationImage;

        this.notificationType = "NOTIFICATION";
        //  Date Usage:
        //  SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a")
        this.notificationDate = System.currentTimeMillis();
    }

    @Override
    public long fetchDate() {
        return this.notificationDate;
    }

    //region Default getters and setters.
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

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
    //endregion

}
