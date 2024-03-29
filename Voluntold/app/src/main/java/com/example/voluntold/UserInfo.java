package com.example.voluntold;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UserInfo implements Parcelable {
    private String name, email, password, uid, accountType, school, organizationName, orgType;
    private int age;

    private ArrayList<OrgPost> allPosts = new ArrayList<>();
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
        accountType = parcel.readString();
        allPosts = parcel.readArrayList(null);
        name = parcel.readString();
        orgType = parcel.readString();
        organizationName = parcel.readString();
        school = parcel.readString();
        age = parcel.readInt();
        email = parcel.readString();
        password = parcel.readString();
        uid = parcel.readString();
        allOpportunities = parcel.readArrayList(null);
    }

    public UserInfo() {
        accountType = "no acc";
        allPosts = null;
        name = "no name";
        orgType = "no org type";
        organizationName = "no org name";
        school = "no school";
        age = 0;
        email = "no email";
        password = "no password";
        uid = "no uid";
        allOpportunities = null;
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
        dest.writeString(accountType);
        dest.writeList(allPosts);
        dest.writeString(name);
        dest.writeString(orgType);
        dest.writeString(organizationName);
        dest.writeString(school);
        dest.writeInt(age);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(uid);
        dest.writeList(allOpportunities);
    }

    public UserInfo(String accountType, ArrayList<OrgPost> allPosts, String name, String orgType, String organizationName,
                    String school, int age, String email, String password, String uid, ArrayList<VolOpportunity> allOpportunities) {
      this.accountType = accountType;
      this.allPosts = allPosts;
      this.name = name;
      this.orgType = orgType;
      this.organizationName = organizationName;
      this.school = school;
      this.age = age;
      this.email = email;
      this.password = password;
      this.uid = uid;
      this.allOpportunities = allOpportunities;
    }

    //public UserInfo(String name, String age, int age, ) // CONSTRUCTOR FOR EDIT VOL PROFILE

    public String toString() {
        return name + ", " + uid;
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

    public void setSchool(String school)
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

    public String getOrgType(){ return orgType; }

    public void setOrgType(String orgType) {this.orgType = orgType; }

    public ArrayList getAllOrgPosts()
    {
        return allPosts;
    }

    public void setAllOrgPosts(ArrayList newAllPosts){
        this.allPosts = newAllPosts;
    }

    public ArrayList getVolOpportunities()
    {
        return allOpportunities;
    }

    public void setVolOpportunities(ArrayList allOpportunities){
        this.allOpportunities = allOpportunities;
    }

//    public void addVolOpportunity(VolOpportunity volOpportunity)
//    {
//        allOpportunities.add(volOpportunity);
//    }



}
