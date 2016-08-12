package com.winnipegapp.examples.Notifications;

/**
 * Created by Amari on 2016-06-14.
 */
public class Notification implements Comparable{
    //  Field declaration.
    protected String title = "";
    protected String description = "";
    protected int notificationId = 0;
    protected long notificationDate = 0;

    public Notification(){};

    /**
    * Object class for all notifications. All notification cards will inherit from this class.
    *
    * */
    public Notification (String title, String description, int notificationId) {
        this.title = title;
        this.description = description;
        this.notificationId = notificationId;

        //  Date Usage:
        //  SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a")
        this.notificationDate = System.currentTimeMillis();
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

    @Override
    public int compareTo(Object another) {
        Notification notification = (Notification) another;
        long time = notification.getNotificationDate();

        if(this.notificationDate > time) {
            return 1;
        }
        else if(this.notificationDate < time){
            return -1;
        }
        else {
            return 0;
        }
    }

    //endregion



}
