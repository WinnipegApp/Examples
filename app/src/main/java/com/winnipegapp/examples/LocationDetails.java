package com.winnipegapp.examples;


import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Array;
import java.util.Arrays;

/**
 * Created by Amari on 2016-07-15.
 */
public class LocationDetails {

    private int location_id;
    private String name;
    private String category;
    private String coordinates;

    /*
    * No arg constructor.
    * */
    public LocationDetails(){};

    /*
    * Constructor for LocationDetails with specific data.
    * */
    public LocationDetails(int location_id, String category, String name, String coordinates){
        this.location_id = location_id;
        this.category = category;
        this.name = name;
        this.coordinates = coordinates;
    }

    //region Auto-generated getters and setters.
    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    //endregion

    /*
    * Creates a Location from the coordinates in the database.
    * */
    public Location getLocation(){
        Location location = new Location("");

        String[] coordinateSet = this.coordinates.toString().split(",");

        location.setLatitude(Double.parseDouble(coordinateSet[0]));
        location.setLongitude(Double.parseDouble(coordinateSet[1]));

        return  location;
    }

    /*
    * Creates a Location from the coordinates in the database.
    * */
    public LatLng getLatLng(){

        String[] coordinateSet = this.coordinates.toString().split(",");

        LatLng location = new LatLng(Double.parseDouble(coordinateSet[0]),Double.parseDouble(coordinateSet[1]));

        return  location;
    }

    /*
    * Alternative setter that receives Location to set the coordinates.
    * */
    public void setCoordinates(Location location){
        this.coordinates = location.getLatitude() + "," + location.getLongitude();
    }
}
