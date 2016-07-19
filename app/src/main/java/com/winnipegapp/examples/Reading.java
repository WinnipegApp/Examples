package com.winnipegapp.examples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amari on 2016-07-18.
 */
public class Reading {

    //  Field declarations
    private int reading_id;
    private long date;
    private String type;
    private double amount;

    //  Formats the date.
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

    /*
    * No arg constructor.
    * */
    public Reading(){}

    /*
    * Constructor with variables defined.
    * */
    public Reading(int reading_id, long date, String type, double amount) {
        this.reading_id = reading_id;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    /*
* Returns the date represented as a SimpleDateFormat
* */
    public String getDateString() {
        //  Creates a date string by formatting the date.
        String date = sdf.format(this.date);

        return date;
    }

    /*
    * Alternative setter for passing the date as a string.*/
    public void setCreated_at(String date) {
        try {
            Date convertedDate = sdf.parse(date);
            this.date = convertedDate.getTime();
        }
        catch (ParseException e){
            this.date = 0;
        }
    }

    //region Default getters and setters
    public int getReading_id() {
        return reading_id;
    }

    public void setReading_id(int reading_id) {
        this.reading_id = reading_id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    //endregion
}
