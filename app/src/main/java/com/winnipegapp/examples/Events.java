package com.winnipegapp.examples;


import java.util.List;

/**
 * Created by Mauricio on 2016-05-30.
 */

class Events {

    String eventName;
    String eventLocation;
    String eventDescription;
    int eventImage;

    Events(String name, String age, String photoId, int eventImage) {
        this.eventName = name;
        this.eventLocation = age;
        this.eventDescription = photoId;
        this.eventImage = eventImage;

    }

    private List<Events> events;

}
