package com.example.steven.tamtam.Models;

import android.net.Uri;
import android.util.Base64;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by steven on 5/22/16.
 */
public abstract class Person implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String gamertag;
    private String description;
    private Date dateOfBirth;
    private Uri imageUrl;
    private boolean playing;
    private boolean rookie;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGamertag() {
        return gamertag;
    }

    public void setGamertag(String gamertag) {
        this.gamertag = gamertag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isRookie() {
        return rookie;
    }

    public void setRookie(boolean rookie) {
        this.rookie = rookie;
    }
}
