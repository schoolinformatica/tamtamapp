package com.example.steven.tamtam.apimanager;

import android.content.Context;
import android.util.Log;
import com.example.steven.tamtam.Models.*;
import org.json.JSONArray;
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
        u.setId(jo.getString("id"));
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

    public static PendingParty parsePendingParty(String json) throws JSONException, ParseException {
        PendingParty p = new PendingParty();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONObject jo = new JSONObject(json);

        p.setId(jo.getString("id"));
        p.setLocation(jo.getString("location"));
        p.setStartTime(simpleDate.parse(jo.getString("startTime")));
        p.setEndTime(simpleDate.parse(jo.getString("endTime")));

        JSONArray ja = jo.getJSONArray("gamers");
        for (int i = 0; i < ja.length(); i++) {
            p.addGamer(parseUser(ja.get(i).toString()));
        }
        return p;
    }

    public static PartySearch parsePartySearch(String json) throws JSONException, ParseException {
        PartySearch p = new PartySearch();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONObject jo = new JSONObject(json);

        p.setEndTime(simpleDate.parse(jo.getString("endTime")));
        p.setStartTime(simpleDate.parse(jo.getString("startTime")));
        p.setLocation(jo.getString("location"));
        p.setUserId(jo.getString("userId"));
        p.setId(jo.getString("id"));

        return p;
    }

}
