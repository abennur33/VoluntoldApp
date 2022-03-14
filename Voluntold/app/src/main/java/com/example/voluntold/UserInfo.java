package com.example.voluntold;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UserInfo implements Parcelable {
    private String name, email, password, uid, accountType, school, organizationName;
    private int age;

    private ArrayList<OrgPost> allPosts= new ArrayList<>();
    private ArrayList<VolOpportunity> allOpportunities = new ArrayList<>();

    // may be implemented later so we can sort by order of importance on list
    // value of 1-3 with 1 being most desired items

    // needed  for the Parcelable code to work
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {

        @Override
        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[0];
        }
    };

    public UserInfo(Parcel parcel) {
        name = parcel.readString();
        email = parcel.readString();
        password = parcel.readString();
        uid = parcel.readString();
        accountType = parcel.readString();
        school = parcel.readString();
        organizationName = parcel.readString();
        age = parcel.readInt();
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
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(uid);
        dest.writeString(accountType);
        dest.writeString(school);
        dest.writeString(organizationName);
        dest.writeInt(age);
    }

    public UserInfo(String name, String email, String password, String uid,
                    String accountType, String school, String organizationName, int age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.uid = uid;
        this.accountType = accountType;
        this.school = school;
        this.organizationName = organizationName;
        this.age = age;
    }



    public UserInfo() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.uid = "";
        this.accountType = "";
        this.school = "";
        this.organizationName = "";
        this.age = 0;
    }

    public String toString() {
        return name + ", " + email + ", " + password + ", " + uid + ", " + accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }

    public String getUserUID() {
        return uid;
    }

    public void setUserUID(String uid) {
        this.uid = uid;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public String getSchool()
    {
        return school;
    }

    public void setSchool(String accountType)
    {
        this.school = school;
    }

    public String getOrganizationName()
    {
        return organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }

    public int getUserAge() {
        return age;
    }

    public void setUserAge(int age) {
        this.age = age;
    }

}
