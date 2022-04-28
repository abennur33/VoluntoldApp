package com.example.voluntold;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class OrgPost implements Parcelable {
    public String orgID;
    private String docID;

    private String title;

    private int month;
    private int date;
    private int year;
    private int comparisonDate;

    private String body;

    private int maxVolunteers;
    private ArrayList<UserInfo> volunteers = new ArrayList<>();

    public static final Parcelable.Creator<OrgPost> CREATOR = new Parcelable.Creator<OrgPost>() {

        @Override
        public OrgPost createFromParcel(Parcel parcel) {
            return new OrgPost(parcel);
        }

        @Override
        public OrgPost[] newArray(int size) {
            return new OrgPost[0];
        }
    };

    public OrgPost(Parcel parcel) {
        orgID = parcel.readString();
        docID = parcel.readString();
        title = parcel.readString();
        month = parcel.readInt();
        date = parcel.readInt();
        year = parcel.readInt();
        maxVolunteers = parcel.readInt();
        //volunteers = parcel.readArrayList(null);
        comparisonDate = parcel.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(orgID);
        dest.writeString(docID);
        dest.writeString(title);
        dest.writeInt(month);
        dest.writeInt(date);
        dest.writeInt(year);
        dest.writeInt(maxVolunteers);
        dest.writeList(volunteers);
        dest.writeInt(comparisonDate);
    }


    public OrgPost(String orgID, String title, int month, int date, int year, String body) {
        this.orgID = orgID;
        this.title = title;
        this.month = month;
        this.date = date;
        this.year = year;
        this.body = body;
        this.volunteers = null;
        this.comparisonDate = (int) (10000*(year + ((double) month/100) + ((double) date/10000)));
    }

    public OrgPost(String orgID, String title, int month, int date, int year, String body, int maxVolunteers) {
        this.orgID = orgID;
        this.title = title;
        this.month = month;
        this.date = date;
        this.year = year;
        this.body = body;
        this.maxVolunteers = maxVolunteers;
        this.volunteers  = null;
        this.comparisonDate = (int) (10000*(year + ((double) month/100) + ((double) date/10000)));

    }

    public OrgPost() {
        this.orgID = "noOrg";
        this.docID = "";
        this.title = "noTitle";
        this.month = 0;
        this.date = 0;
        this.year = 0;
        this.body = "noBody";
        this.maxVolunteers = 0;
        this.volunteers  = null;
        this.comparisonDate = 0;
    }

    public double getComparisonDate() {
        return comparisonDate;
    }

    public void setComparisonDate(int comparisonDate) {
        this.comparisonDate = comparisonDate;
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

    public void addVolunteer(UserInfo u) { volunteers.add(u);
    }

    public void setVolunteers(ArrayList<UserInfo> users) {
        this.volunteers = users;
    }

    public ArrayList<UserInfo> getVolunteers() {
        return volunteers;
    }

    public void decrementMaxVolunteers()
    {
        maxVolunteers -= 1;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    @Override
    public String toString() {
        if (volunteers == null) {
            return getTitle() + " on " + getMonth() + "/" + getDate() + "/" + getYear() + "   " + "0/" + getMaxVolunteers() + " volunteers";
        }
        else {
            return getTitle() + " on " + getMonth() + "/" + getDate() + "/" + getYear() + "   " + volunteers.size() + "/" + getMaxVolunteers() + " volunteers";
        }
    }
}