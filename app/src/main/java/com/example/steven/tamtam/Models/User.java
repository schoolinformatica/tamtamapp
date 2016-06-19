package com.example.steven.tamtam.Models;

/**
 * Created by steven on 5/22/16.
 */
public class User extends Person {

    private String password;
    private String token;
    private String id;

    public User() {
    }

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
