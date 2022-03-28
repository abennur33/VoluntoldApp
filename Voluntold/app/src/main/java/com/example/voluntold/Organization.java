package com.example.voluntold;

public class Organization {
    private String name;
    private String orgID;

    public Organization(String name, String orgID) {
        this.name = name;
        this.orgID = orgID;
    }

    public Organization() {
        this.name = "noname";
        this.orgID = "noOrg";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }
}
