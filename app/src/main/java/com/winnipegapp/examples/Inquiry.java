package com.winnipegapp.examples;

import android.location.Location;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amari on 2016-07-18.
 */
public class Inquiry {
    /*
    * Class for an Inquiry.
    * */

    //  Field declaration
    private int inquiry_id;
    private long created_at;
    private String type;
    private String description;
    private String image_url;
    private String coordinates;

    //  Formats the date.
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

    /*
    * No arg constructor.
    * */
    public Inquiry(){}

    /*
    * Constructor passed parameters
    * */
    public Inquiry(int inquiry_id, String type, String description, String image_url, String coordinates) {
        this.inquiry_id = inquiry_id;
        this.type = type;
        this.description = description;
        this.image_url = image_url;
        this.coordinates = coordinates;

        this.created_at = System.currentTimeMillis();
    }

    /*
    * Returns the date represented as a SimpleDateFormat
    * */
    public String getDateString() {
        //  Creates a date string by formatting the date.
        String date = sdf.format(this.created_at);

        return date;
    }

    /*
    * Alternative setter for passing the date as a string.*/
    public void setCreated_at(String date) {
        try {
            Date convertedDate = sdf.parse(date);
            this.created_at = convertedDate.getTime();
        }
        catch (ParseException e){
            this.created_at = 0;
        }
    }

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
    * Alternative setter that receives Location to set the coordinates.
    * */
    public void setCoordinates(Location location){
        this.coordinates = location.getLatitude() + "," + location.getLongitude();
    }

    //region Default getters and setters
    public int getInquiry_id() {
        return inquiry_id;
    }

    public void setInquiry_id(int inquiry_id) {
        this.inquiry_id = inquiry_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    //endregion

}
