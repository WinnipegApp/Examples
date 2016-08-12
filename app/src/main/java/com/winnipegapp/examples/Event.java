package com.winnipegapp.examples;

import com.winnipegapp.examples.Notifications.Notification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Amari on 2016-07-19.
 */
public class Event extends Notification{

    private int event_id;
    private long start_time;
    private long end_time;
    private String name;
    private String description;
    private String address;
    private String event_image;

    //  Formats the date.
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

    public Event(){}

    /**
     * Constructor that omits the image and end time.
     * */
    public Event(int event_id,  String name, String description, String address) {
        this.event_id = event_id;
        this.start_time = generateRandomTime();
        this.name = name;
        this.description = description;
        this.address = address;
        this.notificationDate = this.start_time;
    }


    public Event(int event_id, long start_time, long end_time, String name, String description, String address, String event_image) {
        this.event_id = event_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.name = name;
        this.description = description;
        this.address = address;
        this.event_image = event_image;

        this.notificationDate = start_time;
    }

    /**
     * Returns a psuedo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimim value
     * @param max Maximim value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public long generateRandomTime(){
        long currentDate = System.currentTimeMillis();

        return currentDate + ( 1+ (randInt(100000000, 1000000000)));

    }

    /**
     * For the purposes of generating realistic hours for fake events.*/
    public String cheatHours(){


        int hours = (8 + randInt(0, 12));
        String result = hours + ":00";

        try {
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourSDF.parse(result);
            return _12HourSDF.format(_24HourDt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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

    public String getHour(){
        SimpleDateFormat hour = new SimpleDateFormat("h:mm a");

        return hour.format(this.start_time);

    }
    @Override
    public long getNotificationDate(){
        return this.start_time;
    }


    //region Default getters and setters.
    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public long getStart_time() {return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
        this.notificationDate = start_time;
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

    public String getEvent_image() {
        return event_image;
    }

    public void setEvent_image(String event_image) {
        this.event_image = event_image;
    }


    //endregion
}
