package com.winnipegapp.examples;

import com.winnipegapp.examples.Notifications.NotificationInterface;

/**
 * Created by Amari on 2016-07-20.
 */
public class PublicInquiry implements NotificationInterface {

    private long date;

    @Override
    public long fetchDate() {
        return this.date;
    }

    @Override
    public int compareTo(Object another) {
        long time = ((NotificationInterface)another).fetchDate();

        if (this.date == time){
            return 0;
        }
        else if(this.date > time){
            return 1;
        }
        else{
            return -1;
        }
    }
}
