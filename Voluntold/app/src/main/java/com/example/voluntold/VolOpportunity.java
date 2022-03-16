package com.example.voluntold;

public class VolOpportunity {
    public String orgID;
    public String volID;

    private String title;

    private int month;
    private int date;
    private int year;

    public VolOpportunity(String orgID, String volID, String title, int month, int date, int year) {
        this.orgID = orgID;
        this.volID = volID;
        this.title = title;
        this.month = month;
        this.date = date;
        this.year = year;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getVolID() {
        return volID;
    }

    public void setVolID(String volID) {
        this.volID = volID;
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
}
