package com.example.steven.tamtam.Models;

import java.util.Date;

/**
 * Created by steven on 6/19/16.
 */
public class PartySearch extends Party {

    private String userId;

    public PartySearch() {
    }

    public PartySearch(Date startTime, Date endTime, String location, String userId) {
        setStartTime(startTime);
        setEndTime(endTime);
        setLocation(location);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
