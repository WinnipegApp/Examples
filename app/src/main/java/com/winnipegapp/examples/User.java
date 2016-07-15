package com.winnipegapp.examples;

/**
 * Created by Amari on 2016-07-11.
 */
public class User {

    private int user_id;
    private String full_name;
    private String address;
    private String postal_code;
    private int mobile_no;
    private String snow_zone;
    private String garbage_day;
    private String password;


    /*
    * Constructor for a user object.
    * The password variable is temporary; will not hold passwords on this database in the future.
    * */
    public User(){
    }

    /*
    * Secondary constructor for building a user with parameters filled
    * */
    public User(int user_id, String full_name, String address, String postal_code, int mobile_no,
                String snow_zone, String garbage_day, String password){
        this.user_id = user_id;
        this.full_name = full_name;
        this.address = address;
        this.postal_code = postal_code;
        this.mobile_no = mobile_no;
        this.snow_zone = snow_zone;
        this.garbage_day = garbage_day;
        this.password = password;

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public int getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(int mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getSnow_zone() {
        return snow_zone;
    }

    public void setSnow_zone(String snow_zone) {
        this.snow_zone = snow_zone;
    }

    public String getGarbage_day() {
        return garbage_day;
    }

    public void setGarbage_day(String garbage_day) {
        this.garbage_day = garbage_day;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
