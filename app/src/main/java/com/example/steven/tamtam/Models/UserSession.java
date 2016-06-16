package com.example.steven.tamtam.Models;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.steven.tamtam.apimanager.UserApiManager;

/**
 * Created by steven on 6/14/16.
 */
public class UserSession extends User {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    String PREFER_NAME = "signeduser";
    int PRIVATE_MODE = 0;

    String KEY_PASSWORD = "password";
    String KEY_EMAIL = "email";
    String KEY_FIRSTNAME = "firstname";
    String KEY_LASTNAME = "lastname";
    String KEY_GAMERTAG = "gamertag";
    String KEY_TOKEN = "token";

    public UserSession(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void save() {
        editor.putString(KEY_PASSWORD, getPassword());
        editor.putString(KEY_EMAIL, getEmail());
        editor.putString(KEY_FIRSTNAME, getFirstname());
        editor.putString(KEY_LASTNAME, getLastname());
        editor.putString(KEY_GAMERTAG, getGamertag());
        editor.putString(KEY_TOKEN, getToken());
        editor.apply();
    }

    public void init() {
        setPassword(pref.getString("password", null));
        setFirstname(pref.getString("firstname", null));
        setLastname(pref.getString("lastname", null));
        setGamertag(pref.getString("gamertag", null));
        setToken(pref.getString("token", null));
        setEmail(pref.getString("email", null));
    }

    public void refreshToken() {
        UserApiManager.setAPIToken(this);
    }
}
