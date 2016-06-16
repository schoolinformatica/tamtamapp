package com.example.steven.tamtam.apimanager;

import android.content.Context;
import android.util.Log;
import com.example.steven.tamtam.Models.Colleague;
import com.example.steven.tamtam.Models.Person;
import com.example.steven.tamtam.Models.User;
import com.example.steven.tamtam.Models.UserSession;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by steven on 6/14/16.
 */
public class ModelParser {

    public static UserSession parseUserSession(String json, Context context) throws JSONException {
        UserSession u = new UserSession(context);
        JSONObject jo = new JSONObject(json);
        u.setFirstname(jo.getString("firstname"));
        u.setLastname(jo.getString("lastname"));
        u.setEmail(jo.getString("email"));
        u.setGamertag(jo.getString("gamertag"));

        return u;
    }

    public static Colleague parseUser(String json) throws JSONException, ParseException {
        Colleague u = new Colleague();
        JSONObject jo = new JSONObject(json);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        u.setFirstname(jo.getString("firstname"));
        u.setLastname(jo.getString("lastname"));
        u.setEmail(jo.getString("email"));
        u.setDateOfBirth(format.parse(jo.getString("dateOfBirth")));
        u.setGamertag(jo.getString("gamertag"));
        u.setDescription(jo.getString("description"));

        return u;
    }

}
