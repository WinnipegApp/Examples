package com.winnipegapp.examples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amari on 2016-07-18.
 */
/*
* */
public class InquiryUpdate {
    //  Field declaration
    private int inquiry_update_id;
    private long date;
    private String status;
    private String description;
    private long inquiry_id;

    //  Formats the date.
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

    /*
    * No arg constructor
    * */
    public InquiryUpdate(){}

    /*
    * Constructor with parameters defined.
    * */
    public InquiryUpdate(int inquiry_update_id, String status, String description, long inquiry_id) {
        this.inquiry_update_id = inquiry_update_id;
        this.status = status;
        this.description = description;
        this.inquiry_id = inquiry_id;

        this.date = System.currentTimeMillis();
    }

    /*
    * Parameter where the date can be defined.*/
    public InquiryUpdate(int inquiry_update_id, long date, String status, String description, long inquiry_id) {
        this.inquiry_update_id = inquiry_update_id;
        this.status = status;
        this.description = description;
        this.inquiry_id = inquiry_id;
        this.date = date;
    }

    /*
    * Returns the date in a string format.
    * */
    public String getDateString(){
        //  Creates a date string by formatting the date.
        String date = sdf.format(this.date);

        return date;
    }

    /*
    * Sets the date based on a string value.
    * */
    public void setDateWithString(String date){
        try {
            Date convertedDate = sdf.parse(date);
            this.date = convertedDate.getTime();
        }
        catch (ParseException e){
            this.date = 0;
        }
    }

    //region Default getters and setters
    public int getInquiry_update_id() {
        return inquiry_update_id;
    }

    public void setInquiry_update_id(int inquiry_update_id) {
        this.inquiry_update_id = inquiry_update_id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getInquiry_id() {
        return inquiry_id;
    }

    public void setInquiry_id(long inquiry_id) {
        this.inquiry_id = inquiry_id;
    }
    //endregion

}
