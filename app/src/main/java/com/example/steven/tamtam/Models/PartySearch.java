package com.example.steven.tamtam.Models;

import java.util.Date;

/**
 * Created by steven on 6/19/16.
 */
public class PartySearch {
    private Date startTime;
    private Date endTime;
    private String location;
    private String userId;

    public PartySearch() {
    }

    public PartySearch(Date startTime, Date endTime, String location, String userId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
