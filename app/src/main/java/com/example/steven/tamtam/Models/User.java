package com.example.steven.tamtam.Models;

/**
 * Created by steven on 5/22/16.
 */
public class User extends Person {

    private String password;
    private String token;

    public User() {
    }

    public String getToken() {
        return token;
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
