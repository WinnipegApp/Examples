package com.winnipegapp.examples.Notifications.WeatherService;

import android.os.AsyncTask;

import com.winnipegapp.examples.Notifications.Weather;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Amari on 2016-06-15.
 * API URL: http://api.wunderground.com/api/520ba1cd3e1cbe7f/geolookup/conditions/forecast/q/Canada/Winnipeg.json
 * API Login: WinnipegApp
 * Password: 204raptors204suck204
 * API Homepage: https://www.wunderground.com/
 */
public class RetrieveWeather extends AsyncTask <Void, Void, Weather> {
    //  We should not allow more then one query of the weather per hour. Maybe a time object in the
    //  My Home fragment?

    private JSONObject reader;
    private String result;
    private String temperature;

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected Weather doInBackground(Void... params) {

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


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
    }
}
