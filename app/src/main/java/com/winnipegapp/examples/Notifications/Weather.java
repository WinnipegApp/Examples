package com.winnipegapp.examples.Notifications;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Amari on 2016-06-21.
 */
public class Weather extends Notification{

    private String temperature;
    //  Temporary variables
    private String result;
    private JSONObject reader;

    public Weather(String title, String description, int notificationId, int notificationImage) {
        super(title, description, notificationId, notificationImage);

        try{
            String url = "http://api.wunderground.com/api/520ba1cd3e1cbe7f/geolookup/conditions/forecast/q/Canada/Winnipeg.json";
            reader = new JSONObject(url);

            try {
                JSONObject forecast = reader.getJSONObject("forecast");
                temperature = forecast.getString("temp_c");
            }
            catch (JSONException e){
                throw e;
            }
        } catch (JSONException e) {
            result = "Could not retrieve weather broadcast.";
        } finally {
            result = temperature;
        }

    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
