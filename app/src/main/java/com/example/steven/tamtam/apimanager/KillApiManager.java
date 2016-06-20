package com.example.steven.tamtam.apimanager;

import android.content.Context;
import com.example.steven.tamtam.Httprequester.HttpRequestManager;
import com.example.steven.tamtam.Models.Colleague;
import com.example.steven.tamtam.Models.UserSession;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.ProtocolException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by steven on 6/20/16.
 */
public class KillApiManager extends ApiManager {

    public static List<Colleague> getKilledUsers(Context context) throws IOException, ExecutionException, InterruptedException, JSONException, ParseException {
        List<Colleague> list = new ArrayList<>();
        userSession = new UserSession(context);
        userSession.init();

        String URL = URL_KILLS_KILLEDBYID_ID + 2;

        HttpRequestManager requestManager = new HttpRequestManager(URL, null);
        requestManager.setRequestMethod("GET");
        requestManager.setRequestProperty("Authorization:", userSession.getToken());
        requestManager.startRequest();

        if (requestManager.getResponseCode() == 200) {
            JSONArray ja = new JSONArray(requestManager.getResponse());
            for (int i = 0; i < ja.length(); i++) {
                list.add(ModelParser.parseUser(ja.getJSONObject(i).getJSONObject("userkilled").toString()));
            }
        }

        if (requestManager.getResponseCode() == 401) {
            setAPIToken(userSession);
            getKilledUsers(context);
        }
        return list;
    }

    public static List<Colleague> getKilledByUsers(Context context) throws IOException, ExecutionException, InterruptedException, JSONException, ParseException {
        List<Colleague> list = new ArrayList<>();
        userSession = new UserSession(context);
        userSession.init();

        String URL = URL_KILLS_USERID_ID + 1;

        HttpRequestManager requestManager = new HttpRequestManager(URL, null);
        requestManager.setRequestMethod("GET");
        requestManager.setRequestProperty("Authorization:", userSession.getToken());
        requestManager.startRequest();

        if (requestManager.getResponseCode() == 200) {
            JSONArray ja = new JSONArray(requestManager.getResponse());
            for (int i = 0; i < ja.length(); i++) {
                list.add(ModelParser.parseUser(ja.getJSONObject(i).toString()));
            }
        }

        if (requestManager.getResponseCode() == 401) {
            setAPIToken(userSession);
            getKilledByUsers(context);
        }
        return list;

    }

}
