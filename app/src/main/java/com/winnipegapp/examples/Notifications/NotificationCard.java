package com.winnipegapp.examples.Notifications;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.winnipegapp.examples.R;

/**
 * Created by Amari on 2016-06-17.
 */
public class NotificationCard extends RecyclerView.ViewHolder {

    //  Field declaration

    private TextView notificationTitle;
    private TextView notificationDescription;
    private TextView notificationDate;
    protected TextView notificationType;


    public NotificationCard(View itemView) {
        super(itemView);
    }{

        notificationTitle = (TextView) itemView.findViewById(R.id.notificationTitle);
        notificationDescription = (TextView) itemView.findViewById(R.id.notificationDescription);
        notificationDate = (TextView) itemView.findViewById(R.id.notificationDate);
        notificationType = (TextView) itemView.findViewById(R.id.notificationType);
    }

    //<editor-fold desc="Getters & Setters">
    public TextView getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(TextView notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public TextView getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(TextView notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public TextView getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(TextView notificationDate) {
        this.notificationDate = notificationDate;
    }

    public TextView getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(TextView notificationType) {
        this.notificationType = notificationType;
    }

    //</editor-fold>
}
