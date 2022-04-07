package com.example.voluntold;

import android.os.Parcel;
import android.os.Parcelable;

public class VolOpportunity implements Parcelable {
    public String orgID;
    public String volID;

    private String title;

    private int month;
    private int date;
    private int year;

    public static final Parcelable.Creator<VolOpportunity> CREATOR = new Parcelable.Creator<VolOpportunity>() {

        @Override
        public VolOpportunity createFromParcel(Parcel parcel) {
            return new VolOpportunity(parcel);
        }

        @Override
        public VolOpportunity[] newArray(int size) {
            return new VolOpportunity[0];
        }
    };

    public VolOpportunity(Parcel parcel) {
        orgID = parcel.readString();
        volID = parcel.readString();
        title = parcel.readString();
        month = parcel.readInt();
        date = parcel.readInt();
        year = parcel.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    /**
     * This is what is used when we send the Event object through an intent
     * It is also a method that is part of the Parceable interface and is needed
     * to set up the object that is being sent.  Then, when it is received, the
     * other Event constructor that accepts a Parcel reference can "unpack it"
     *
     */
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(orgID);
        dest.writeString(volID);
        dest.writeString(title);
        dest.writeInt(month);
        dest.writeInt(date);
        dest.writeInt(year);
    }
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

    public String toString() {
        return getTitle() + "on " + getMonth() + "/" + getDate() + "/" + getYear();
    }
}
