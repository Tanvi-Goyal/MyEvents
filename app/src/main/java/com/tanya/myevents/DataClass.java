package com.tanya.myevents;

/**
 * Created by Tanya on 11/26/2017.
 */

public class DataClass {
    private String title, organiser, venue, time;
    private static final String TITLE = "title";
    private static final String ORGANISER = "organiser";
    private static final String VENUE = "venue";
    private static final String DATE = "date";

    public DataClass()
    {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
