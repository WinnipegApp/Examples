package com.winnipegapp.examples;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.winnipegapp.examples.Notifications.Notification;

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
    public static final int DATABASE_VERSION = 1;

    //  Table names.
    public static final String TABLE_NOTIFICATIONS = "DEVICE_NOTIFICATIONS";
    public static final String TABLE_USERS = "USERS";
    public static final String TABLE_LOCATIONS = "LOCATIONS";
    public static final String TABLE_EVENTS = "EVENTS";
    public static final String TABLE_INQUIRIES = "INQUIRIES";
    public static final String TABLE_INQUIRY_UPDATES = "INQUIRY_UPDATES";
    public static final String TABLE_READINGS = "READINGS";

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
    public static final String COLUMN_USERS_POSTAL_CODE = "postal_game";
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
    public static final String COLUMN_INQUIRIES_TYPE = "type";
    public static final String COLUMN_INQUIRIES_DESCRIPTION = "description";
    public static final String COLUMN_INQUIRIES_CREATED_AT = "postal_code";
    public static final String COLUMN_INQUIRIES_CITY_ZONE = "city zone";

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
            COLUMN_LOCATIONS_COORDINATES + " NUMERIC)";

    public static final String CREATE_TABLE_EVENTS =  CREATION_MODULE +
            TABLE_EVENTS + "(" +
            COLUMN_EVENTS_EVENT_ID + PRIMARY_KEY_MODULE +
            COLUMN_EVENTS_START_TIME + INTEGER_MODULE +
            COLUMN_EVENTS_END_TIME + INTEGER_MODULE +
            COLUMN_EVENTS_NAME + TEXT_MODULE +
            COLUMN_EVENTS_DESCRIPTION + TEXT_MODULE +
            COLUMN_EVENTS_ADDRESS + TEXT_MODULE +
            COLUMN_EVENTS_COORDINATES + " NUMERIC)";

    public static final String CREATE_TABLE_INQUIRIES =  CREATION_MODULE +
            TABLE_INQUIRIES + "(" +
            COLUMN_INQUIRIES_INQUIRY_ID + PRIMARY_KEY_MODULE +
            COLUMN_INQUIRIES_CREATED_AT + INTEGER_MODULE +
            COLUMN_INQUIRIES_TYPE + TEXT_MODULE +
            COLUMN_INQUIRIES_DESCRIPTION + TEXT_MODULE +
            COLUMN_INQUIRIES_CITY_ZONE + " TEXT)";


    public static final String CREATE_TABLE_INQUIRY_UPDATES =  CREATION_MODULE +
            TABLE_INQUIRY_UPDATES + "(" +
            COLUMN_INQUIRY_UPDATES_INQUIRY_UPDATE_ID + PRIMARY_KEY_MODULE +
            COLUMN_INQUIRY_UPDATES_DATE + NUMERIC_MODULE +
            COLUMN_INQUIRY_UPDATES_DESCRIPTION + TEXT_MODULE +
            COLUMN_INQUIRY_UPDATES_STATUS + TEXT_MODULE +
            COLUMN_INQUIRY_UPDATES_INQUIRY_ID + " INTEGER)";

    public static final String CREATE_TABLE_READINGS = CREATION_MODULE +
            TABLE_READINGS + "(" +
            COLUMN_READINGS_READING_ID + PRIMARY_KEY_MODULE +
            COLUMN_READINGS_DATE + NUMERIC_MODULE +
            COLUMN_READINGS_TYPE + TEXT_MODULE +
            COLUMN_READINGS_AMOUNT + " NUMERIC)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    * On first run, wll create all the tables.*/
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
    private void buildTables(SQLiteDatabase db) {
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
    private void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE " + TABLE_NOTIFICATIONS);
        db.execSQL("DROP TABLE " + TABLE_USERS);
        db.execSQL("DROP TABLE " + TABLE_LOCATIONS);
        db.execSQL("DROP TABLE " + TABLE_EVENTS);
        db.execSQL("DROP TABLE " + TABLE_INQUIRIES);
        db.execSQL("DROP TABLE " + TABLE_INQUIRY_UPDATES);
        db.execSQL("DROP TABLE " + TABLE_READINGS);
    }


    /*
    *   Class creates a notification in the database.
    *
    *   This  method is for testing.
    * */
    public void createNotification(Notification notification){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NOTIFICATIONS_DATE, notification.getNotificationDate());
    }
}
