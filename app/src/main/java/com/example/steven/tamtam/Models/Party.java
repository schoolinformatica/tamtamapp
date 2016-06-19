package com.example.steven.tamtam.Models;

import java.util.Date;

/**
 * Created by steven on 6/19/16.
 */
public class Party {
    private Date startTime;
    private Date endTime;
    private String location;
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
