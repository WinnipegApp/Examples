package com.winnipegapp.examples.Notifications;

import android.view.View;
import android.widget.TextView;

import com.winnipegapp.examples.R;

/**
 * Created by Amari on 2016-07-20.
 */
public class EventCard extends NotificationCard {

    private TextView notificationTime;


    public EventCard(View itemView) {
        super(itemView);
        notificationTime = (TextView) itemView.findViewById(R.id.notificationTime);

    }

    public TextView getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(TextView notificationTime) {
        this.notificationTime = notificationTime;
    }
}
