package com.winnipegapp.examples.Notifications;

/**
 * Created by Amari on 2016-07-08.
 */
public class Warning extends Notification {
    public Warning(String title, String description, int notificationId, int notificationImage) {
        super(title, description, notificationId, notificationImage);
        this.setNotificationType("WARNING");
    }

    @Override
    public int compareTo(Object another) {
        return 1;
    }
}
