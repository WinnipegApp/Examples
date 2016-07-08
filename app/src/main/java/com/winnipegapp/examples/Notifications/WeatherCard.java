package com.winnipegapp.examples.Notifications;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Amari on 2016-06-15.
 *
 * API taken from http://openweathermap.org/
 * Username: amarilen@live.ca
 * Password: 204raptors204suck204
 */
public class WeatherCard extends NotificationCard {

    private TextView temperature;

    /*
    *   Representation of the ViewHolder for weather cards
    * */
    public WeatherCard(View itemView)
    {
        super(itemView);

    }

    public TextView getTemperature() {
        return temperature;
    }

    public void setTemperature(TextView temperature) {
        this.temperature = temperature;
    }

}
