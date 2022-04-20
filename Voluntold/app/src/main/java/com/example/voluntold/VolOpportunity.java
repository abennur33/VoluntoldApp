package com.example.voluntold;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class VolOpportunity implements Parcelable {
    public String orgID;
    public String volID;

    private String title;

    private int month;
    private int date;
    private int year;

    private boolean completed;

    public static final Parcelable.Creator<VolOpportunity> CREATOR = new Parcelable.Creator<VolOpportunity>() {

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public VolOpportunity createFromParcel(Parcel parcel) {
            return new VolOpportunity(parcel);
        }

        @Override
        public VolOpportunity[] newArray(int size) {
            return new VolOpportunity[0];
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.Q) //java suggested this for read boolean
    public VolOpportunity(Parcel parcel) {
        orgID = parcel.readString();
        volID = parcel.readString();
        title = parcel.readString();
        month = parcel.readInt();
        date = parcel.readInt();
        year = parcel.readInt();
        completed = parcel.readBoolean();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
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
        dest.writeBoolean(completed);
    }
    public VolOpportunity(String orgID, String volID, String title, int month, int date, int year) {
        this.orgID = orgID;
        this.volID = volID;
        this.title = title;
        this.month = month;
        this.date = date;
        this.year = year;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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
