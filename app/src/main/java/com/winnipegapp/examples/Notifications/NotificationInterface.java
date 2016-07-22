package com.winnipegapp.examples.Notifications;

/**
 * Created by Amari on 2016-07-21.
 */
/**
 * This method is used to allow sorting of notifications by the time they are received
 * */
public interface NotificationInterface extends Comparable {

    public long fetchDate();

}
