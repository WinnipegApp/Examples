package com.winnipegapp.examples.Notifications;

/**
 * Created by Amari on 2016-07-08.
 */
public class Warning extends Notification {
    public Warning(String title, String description, int notificationId) {
        super(title, description, notificationId);
    }

    @Override
    public int compareTo(Object another) {
        return 1;
    }
}
