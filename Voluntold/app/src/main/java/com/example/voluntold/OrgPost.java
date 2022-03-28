package com.example.voluntold;

import java.util.ArrayList;

public class OrgPost {
    public String orgID;

    private String title;

    private int month;
    private int date;
    private int year;

    private String body;

    private int maxVolunteers;
    private ArrayList<String> volunteers = new ArrayList<>();

    public OrgPost(String orgID, String title, int month, int date, int year, String body) {
        this.orgID = orgID;
        this.title = title;
        this.month = month;
        this.date = date;
        this.year = year;
        this.body = body;
    }

    public OrgPost(String orgID, String title, int month, int date, int year, String body, int maxVolunteers) {
        this.orgID = orgID;
        this.title = title;
        this.month = month;
        this.date = date;
        this.year = year;
        this.body = body;
        this.maxVolunteers = maxVolunteers;
    }

    public OrgPost() {
        this.orgID = "noOrg";
        this.title = "noTitle";
        this.month = 0;
        this.date = 0;
        this.year = 0;
        this.body = "noBody";
        this.maxVolunteers = 0;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getMaxVolunteers() {
        return maxVolunteers;
    }

    public void setMaxVolunteers(int maxVolunteers) {
        this.maxVolunteers = maxVolunteers;
    }

    public void addVolunteer(String uid) {
        volunteers.add(uid);
    }

    @Override
    public String toString() {
        return title + "on " + month + "/" + date + "/" + year;
    }
}