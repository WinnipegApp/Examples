package com.winnipegapp.examples;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amari on 2016-06-27.
 */

    //  Table creation in plain text.
/*
    Table creation assembly

    CREATE TABLE IF NOT EXISTS DEVICE_NOTIFICATIONS
    (
        notification_id INTEGER PRIMARY KEY AUTOINCREMENT,
        date INTEGER,
        title TEXT,
        description TEXT,
        type TEXT
    );

    CREATE TABLE IF NOT EXISTS USERS
    (
        user_id INTEGER PRIMARY KEY AUTOINCREMENT,
        password TEXT,
        full_name TEXT,
        address TEXT,
        postal_code TEXT,
        mobile_no INTEGER,
        snow_zone TEXT,
        garbage_day TEXT
    );

    CREATE TABLE IF NOT EXISTS LOCATIONS
    (
        location_id PRIMARY KEY AUTOINCREMENT,
        name TEXT,
        category TEXT,
        coordinates NUMERIC
    );

    CREATE TABLE IF NOT EXISTS EVENTS
    (
        event_id INTEGER PRIMARY KEY,
        start_time INTEGER,
        end_time INTEGER,
        name TEXT,
        description TEXT,
        address TEXT,
        coordinates NUMERIC
    );

    CREATE TABLE IF NOT EXISTS INQUIRIES
    (
        inquiry_id INTEGER PRIMARY KEY AUTOINCREMENT,
        type TEXT,
        description TEXT,
        created_at INTEGER,
        postal_code TEXT,
        city_zone TEXT
    );

    CREATE TABLE IF NOT EXISTS INQUIRY_UPDATES
    (
        inquiry_update_id INTEGER PRIMARY KEY AUTOINCREMENT,
        description TEXT,
        date INTEGER,
        status TEXT,
        inquiry_id INTEGER
    );

    CREATE TABLE IF NOT EXISTS READINGS
    (
        reading_id INTEGER PRIMARY KEY AUTOINCREMENT,
        date INTEGER,
        type TEXT,
        amount NUMERIC,
    );
*/


public class DatabaseHelper extends SQLiteOpenHelper {

    //  Contains all the constants for the database. Open at your own risk!
    public static final String DATABASE_NAME = "USER_DATABASE";
    public static final int DATABASE_VERSION = 4;

    //  Table names.
    public static final String TABLE_NOTIFICATIONS = "DEVICE_NOTIFICATIONS";
    public static final String TABLE_USERS = "USERS";
    public static final String TABLE_LOCATIONS = "LOCATIONS";
    public static final String TABLE_EVENTS = "EVENTS";
    public static final String TABLE_INQUIRIES = "INQUIRIES";
    public static final String TABLE_INQUIRY_UPDATES = "INQUIRY_UPDATES";
    public static final String TABLE_READINGS = "READINGS";

    //  An array of all the table names;
    public String[] tableNames = {TABLE_NOTIFICATIONS, TABLE_USERS, TABLE_LOCATIONS,
    TABLE_EVENTS, TABLE_INQUIRIES, TABLE_INQUIRY_UPDATES, TABLE_READINGS};

    //  Notification column names.
    public static final String COLUMN_NOTIFICATIONS_NOTIFICATION_ID = "notification_id";
    public static final String COLUMN_NOTIFICATIONS_DATE = "date";
    public static final String COLUMN_NOTIFICATIONS_TITLE = "title";
    public static final String COLUMN_NOTIFICATIONS_DESCRIPTION = "description";
    public static final String COLUMN_NOTIFICATIONS_TYPE = "type";

    //  User column names.
    public static final String COLUMN_USERS_USER_ID = "user_id";
    public static final String COLUMN_USERS_PASSWORD = "password";
    public static final String COLUMN_USERS_FULL_NAME = "full_name";
    public static final String COLUMN_USERS_ADDRESS = "address";
    public static final String COLUMN_USERS_POSTAL_CODE = "postal_code";
    public static final String COLUMN_USERS_MOBILE_NO = "mobile_no";
    public static final String COLUMN_USERS_SNOW_ZONE = "snow_zone";
    public static final String COLUMN_USERS_GARBAGE_DAY = "garbage_day";

    //  Location column names.
    public static final String COLUMN_LOCATIONS_LOCATION_ID = "location_id";
    public static final String COLUMN_LOCATIONS_NAME = "name";
    public static final String COLUMN_LOCATIONS_CATEGORY = "category";
    public static final String COLUMN_LOCATIONS_COORDINATES = "coordinates";

    //  Event column names.
    public static final String COLUMN_EVENTS_EVENT_ID = "event_id";
    public static final String COLUMN_EVENTS_START_TIME = "start_time";
    public static final String COLUMN_EVENTS_END_TIME = "end_time";
    public static final String COLUMN_EVENTS_NAME = "name";
    public static final String COLUMN_EVENTS_DESCRIPTION = "description";
    public static final String COLUMN_EVENTS_ADDRESS = "address";
    public static final String COLUMN_EVENTS_COORDINATES = "coordinates";

    //  Inquiry column names.
    public static final String COLUMN_INQUIRIES_INQUIRY_ID = "inquiry_id";
    public static final String COLUMN_INQUIRIES_CREATED_AT = "postal_code";
    public static final String COLUMN_INQUIRIES_TYPE = "type";
    public static final String COLUMN_INQUIRIES_DESCRIPTION = "description";
    public static final String COLUMN_INQUIRIES_IMAGE_URL = "image_url";
    public static final String COLUMN_INQUIRIES_COORDINATES = "coordinates";

    //  Inquiry updates column names.
    public static final String COLUMN_INQUIRY_UPDATES_INQUIRY_UPDATE_ID = "inquiry_update_id";
    public static final String COLUMN_INQUIRY_UPDATES_DESCRIPTION = "description";
    public static final String COLUMN_INQUIRY_UPDATES_DATE = "date";
    public static final String COLUMN_INQUIRY_UPDATES_STATUS = "status";
    public static final String COLUMN_INQUIRY_UPDATES_INQUIRY_ID = "inquiry_id";

    //  Readings column names.
    public static final String COLUMN_READINGS_READING_ID = "reading_id";
    public static final String COLUMN_READINGS_DATE = "date";
    public static final String COLUMN_READINGS_TYPE = "type";
    public static final String COLUMN_READINGS_AMOUNT = "amount";

    //  Column types
    public static final String CREATION_MODULE = "CREATE TABLE IF NOT EXISTS ";
    public static final String PRIMARY_KEY_MODULE = " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    public static final String TEXT_MODULE = " TEXT, ";
    public static final String INTEGER_MODULE = " INTEGER, ";
    public static final String NUMERIC_MODULE = " NUMERIC, ";

    //  Table creation statements.
    public static final String CREATE_TABLE_NOTIFICATIONS =   CREATION_MODULE +
            TABLE_NOTIFICATIONS + "(" +
            COLUMN_NOTIFICATIONS_NOTIFICATION_ID + PRIMARY_KEY_MODULE +
            COLUMN_NOTIFICATIONS_DATE + INTEGER_MODULE +
            COLUMN_NOTIFICATIONS_TITLE + TEXT_MODULE +
            COLUMN_NOTIFICATIONS_DESCRIPTION + TEXT_MODULE +
            COLUMN_NOTIFICATIONS_TYPE + " TEXT)";

    public static final String CREATE_TABLE_USERS =  CREATION_MODULE +
            TABLE_USERS + "(" +
            COLUMN_USERS_USER_ID + PRIMARY_KEY_MODULE +
            COLUMN_USERS_PASSWORD + TEXT_MODULE +
            COLUMN_USERS_FULL_NAME + TEXT_MODULE +
            COLUMN_USERS_ADDRESS + TEXT_MODULE +
            COLUMN_USERS_POSTAL_CODE + TEXT_MODULE +
            COLUMN_USERS_MOBILE_NO + INTEGER_MODULE +
            COLUMN_USERS_SNOW_ZONE + TEXT_MODULE +
            COLUMN_USERS_GARBAGE_DAY + " TEXT)";


    public static final String CREATE_TABLE_LOCATIONS =  CREATION_MODULE +
            TABLE_LOCATIONS + "(" +
            COLUMN_LOCATIONS_LOCATION_ID + PRIMARY_KEY_MODULE +
            COLUMN_LOCATIONS_CATEGORY + TEXT_MODULE +
            COLUMN_LOCATIONS_NAME + TEXT_MODULE +
            COLUMN_LOCATIONS_COORDINATES + " TEXT)";

    public static final String CREATE_TABLE_EVENTS =  CREATION_MODULE +
            TABLE_EVENTS + "(" +
            COLUMN_EVENTS_EVENT_ID + PRIMARY_KEY_MODULE +
            COLUMN_EVENTS_START_TIME + INTEGER_MODULE +
            COLUMN_EVENTS_END_TIME + INTEGER_MODULE +
            COLUMN_EVENTS_NAME + TEXT_MODULE +
            COLUMN_EVENTS_DESCRIPTION + TEXT_MODULE +
            COLUMN_EVENTS_ADDRESS + TEXT_MODULE +
            COLUMN_EVENTS_COORDINATES + " TEXT)";

    public static final String CREATE_TABLE_INQUIRIES =  CREATION_MODULE +
            TABLE_INQUIRIES + "(" +
            COLUMN_INQUIRIES_INQUIRY_ID + PRIMARY_KEY_MODULE +
            COLUMN_INQUIRIES_CREATED_AT + INTEGER_MODULE +
            COLUMN_INQUIRIES_TYPE + TEXT_MODULE +
            COLUMN_INQUIRIES_DESCRIPTION + TEXT_MODULE +
            COLUMN_INQUIRIES_IMAGE_URL + TEXT_MODULE +
            COLUMN_INQUIRIES_COORDINATES + " TEXT)";


    public static final String CREATE_TABLE_INQUIRY_UPDATES =  CREATION_MODULE +
            TABLE_INQUIRY_UPDATES + "(" +
            COLUMN_INQUIRY_UPDATES_INQUIRY_UPDATE_ID + PRIMARY_KEY_MODULE +
            COLUMN_INQUIRY_UPDATES_DATE + NUMERIC_MODULE +
            COLUMN_INQUIRY_UPDATES_STATUS + TEXT_MODULE +
            COLUMN_INQUIRY_UPDATES_DESCRIPTION + TEXT_MODULE +
            COLUMN_INQUIRY_UPDATES_INQUIRY_ID + " INTEGER)";

    public static final String CREATE_TABLE_READINGS = CREATION_MODULE +
            TABLE_READINGS + "(" +
            COLUMN_READINGS_READING_ID + PRIMARY_KEY_MODULE +
            COLUMN_READINGS_DATE + NUMERIC_MODULE +
            COLUMN_READINGS_TYPE + TEXT_MODULE +
            COLUMN_READINGS_AMOUNT + " NUMERIC)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    * On first run, will create all the tables.*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        buildTables(db);
    }


    /*
    * On upgrade, it drops all the tables and rebuilds the database
    * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        dropTables(db);
        buildTables(db);
    }

    /*
    * Builds all the current tables.
    * */
    public void buildTables(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTIFICATIONS);
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_LOCATIONS);
        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_INQUIRIES);
        db.execSQL(CREATE_TABLE_INQUIRY_UPDATES);
        db.execSQL(CREATE_TABLE_READINGS);
    }

    /*
    * Drops all the current tables.
    * */
    public void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE " + TABLE_NOTIFICATIONS);
        db.execSQL("DROP TABLE " + TABLE_USERS);
        db.execSQL("DROP TABLE " + TABLE_LOCATIONS);
        db.execSQL("DROP TABLE " + TABLE_EVENTS);
        db.execSQL("DROP TABLE " + TABLE_INQUIRIES);
        db.execSQL("DROP TABLE " + TABLE_INQUIRY_UPDATES);
        db.execSQL("DROP TABLE " + TABLE_READINGS);
    }

    /*
    * Deletes all rows from the table..
     */
    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < tableNames.length; i++){
            db.delete(tableNames[i], null, null);
        }
    }

    /*
    * Method for creating users. Pass it a user object and it will add it to the database.
    * */
    public void createUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERS_USER_ID, user.getUser_id());
        values.put(COLUMN_USERS_FULL_NAME, user.getFull_name());
        values.put(COLUMN_USERS_PASSWORD, user.getPassword());
        values.put(COLUMN_USERS_MOBILE_NO, user.getMobile_no());
        values.put(COLUMN_USERS_ADDRESS, user.getAddress());
        values.put(COLUMN_USERS_POSTAL_CODE, user.getPostal_code());
        values.put(COLUMN_USERS_GARBAGE_DAY, user.getGarbage_day());
        values.put(COLUMN_USERS_SNOW_ZONE, user.getSnow_zone());

        //  Inserts the user into the database.
        db.insert(TABLE_USERS, null, values);
    }

    /*
    * Method for retrieving users from the database. This is exclusively for the prototype.
    * */
    public List<User> selectAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> users = new ArrayList<>();
        String query = "SEELCT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                User user = new User();
                user.setUser_id(Integer.parseInt(cursor.getString(0)));
                user.setPassword(cursor.getString(1));
                user.setFull_name(cursor.getString(2));
                user.setAddress(cursor.getString(3));
                user.setPostal_code(cursor.getString(4));
                user.setMobile_no(Integer.parseInt(cursor.getString(5)));
                user.setSnow_zone(cursor.getString(6));
                user.setGarbage_day(cursor.getString(7));
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }

    /*
    * Adds a location to the database
    * */
    public void createLocation(LocationDetails locationDetail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //  Appends data to column values.
        values.put(COLUMN_LOCATIONS_LOCATION_ID, locationDetail.getLocation_id());
        values.put(COLUMN_LOCATIONS_CATEGORY, locationDetail.getCategory());
        values.put(COLUMN_LOCATIONS_NAME, locationDetail.getName());
        values.put(COLUMN_LOCATIONS_COORDINATES, locationDetail.getCoordinates());

        //  Inserts into the database.
        db.insert(TABLE_LOCATIONS, null, values);
    }

    /*
    * Retrieves a list of all location details.
    * */
    public List<LocationDetails> getLocations(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<LocationDetails> locations = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_LOCATIONS;
        Cursor cursor = db.rawQuery(query, null);

        //  Iterates through all the rows
        if (cursor.moveToFirst()){
            do{
                LocationDetails location = new LocationDetails();
                location.setLocation_id(Integer.parseInt(cursor.getString(0)));
                location.setName(cursor.getString(1));
                location.setCategory(cursor.getString(2));
                location.setCoordinates(cursor.getString(3));
                locations.add(location);
            }while (cursor.moveToNext());
        }

        return locations;
    }

    /*
    * Adds an inquiry to the database.
    * */
    public void createInquiry(Inquiry inquiry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //  Appends data to column values.
        values.put(COLUMN_INQUIRIES_INQUIRY_ID, inquiry.getInquiry_id());
        values.put(COLUMN_INQUIRIES_CREATED_AT, inquiry.getCreated_at());
        values.put(COLUMN_INQUIRIES_TYPE, inquiry.getType());
        values.put(COLUMN_INQUIRIES_DESCRIPTION, inquiry.getDescription());
        values.put(COLUMN_INQUIRIES_IMAGE_URL, inquiry.getImage_url());
        values.put(COLUMN_INQUIRIES_COORDINATES, inquiry.getCoordinates());

        //  Table insertion.
        db.insert(TABLE_INQUIRIES, null, values);
    }

    /*
    * Retrieves a list of all inquiries
     * */
    public List<Inquiry> getInquiries(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Inquiry> inquiries = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_INQUIRIES;
        Cursor cursor = db.rawQuery(query, null);

        //  Iterates through all the rows
        if (cursor.moveToFirst()){
            do{
                Inquiry inquiry = new Inquiry();
                inquiry.setInquiry_id(Integer.parseInt(cursor.getString(0)));
                inquiry.setCreated_at(Integer.parseInt(cursor.getString(1)));
                inquiry.setType(cursor.getString(2));
                inquiry.setDescription(cursor.getString(3));
                inquiry.setImage_url(cursor.getString(4));
                inquiry.setCoordinates(cursor.getString(5));
                inquiries.add(inquiry);
            }while (cursor.moveToNext());
        }
        return inquiries;
    }

    /*
    * Adds a new inquiry update to the database.
    * */
    public void createInquiryUpdate(InquiryUpdate update){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //  Appends data to column values.
        values.put(COLUMN_INQUIRY_UPDATES_INQUIRY_UPDATE_ID, update.getInquiry_update_id());
        values.put(COLUMN_INQUIRY_UPDATES_DATE, update.getDate());
        values.put(COLUMN_INQUIRY_UPDATES_STATUS, update.getStatus());
        values.put(COLUMN_INQUIRY_UPDATES_DESCRIPTION, update.getDescription());
        values.put(COLUMN_INQUIRY_UPDATES_INQUIRY_UPDATE_ID, update.getDescription());

        //  Inserts the update into the database.
        db.insert(TABLE_INQUIRY_UPDATES, null, values);
    }



    /*
    * Retrieves all inquiry updates. Pass it an inquiry id number and receive all updates related.
    * */
    public List<InquiryUpdate> getInquiryUpdates(int inquiry_id){
        SQLiteDatabase db = getReadableDatabase();
        List<InquiryUpdate> updates = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_INQUIRY_UPDATES + " WHERE " +
                COLUMN_INQUIRY_UPDATES_INQUIRY_UPDATE_ID + " = " + inquiry_id;
        Cursor cursor = db.rawQuery(query, null);

        //  Iterates through all related rows.
        if (cursor.moveToFirst()){
            do{
                InquiryUpdate update = new InquiryUpdate();
                update.setInquiry_update_id(Integer.parseInt(cursor.getString(0)));
                update.setDate(Integer.parseInt(cursor.getString(1)));
                update.setStatus(cursor.getString(2));
                update.setDescription(cursor.getString(3));
                update.setInquiry_id(Integer.parseInt(cursor.getString(4)));
                updates.add(update);
            }while (cursor.moveToNext());
        }
        return updates;
    }
}
