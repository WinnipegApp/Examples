package com.winnipegapp.examples;

import com.winnipegapp.examples.Notifications.NotificationInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amari on 2016-07-19.
 */
public class Event implements NotificationInterface{

    private int event_id;
    private long start_time;
    private long end_time;
    private String name;
    private String description;
    private String address;

    //  Formats the date.
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

    public Event(){}

    public Event(int event_id, long start_time, long end_time, String name, String description, String address) {
        this.event_id = event_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    /*
    * Modular method for converting a long time to a string.
    * */
    public String convertTime(long date){
        return sdf.format(date);
    }

    /*
    * Inverted method for converting a string to a long.
    * */
    public long convertTime(String date){
        try {
            Date convertedDate = sdf.parse(date);
            return convertedDate.getTime();
        }
        catch (ParseException e){
            return 0;
        }
    }

    /*
    * Alternative getter and setter for the start time.
    * */
    public String getStartTimeLong(){
        return convertTime(this.start_time);
    }
    public void setStart_time(String time){
        this.start_time = convertTime(time);
    }

    /*
    * Alternative getter and setter for the start time.
    * */
    public String getEndTimeLong(){
        return convertTime(this.end_time);
    }
    public void setEnd_time(String time){
        this.end_time = convertTime(time);
    }

    /**
    * Methods for sorting Events in the notification feed.
     * */
    @Override
    public long fetchDate() {
        return this.start_time;
    }
    @Override
    public int compareTo(Object another) {

        long time = ((NotificationInterface)another).fetchDate();

        if (this.start_time == time){
            return 0;
        }
        else if(this.start_time > time){
            return 1;
        }
        else{
            return -1;
        }
    }


    //region Default getters and setters.
    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    //endregion
}
