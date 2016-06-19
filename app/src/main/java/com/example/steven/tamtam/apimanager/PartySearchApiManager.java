package com.example.steven.tamtam.apimanager;

import android.content.Context;
import android.util.Log;
import com.example.steven.tamtam.Httprequester.HttpParam;
import com.example.steven.tamtam.Httprequester.HttpParamManager;
import com.example.steven.tamtam.Httprequester.HttpRequestManager;
import com.example.steven.tamtam.Models.Party;
import com.example.steven.tamtam.Models.PartySearch;
import com.example.steven.tamtam.Models.PendingParty;
import com.example.steven.tamtam.Models.UserSession;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.ProtocolException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by steven on 6/19/16.
 */
public class PartySearchApiManager extends ApiManager {

    public static boolean partySearch(PartySearch p, Context context) throws IOException, ExecutionException, InterruptedException {

        userSession = new UserSession(context);
        userSession.init();

        SimpleDateFormat simpleDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String URL = URL_PARTYSEARCH_ID + p.getUserId();

        HttpParamManager paramManager = new HttpParamManager();
        paramManager.setParam(new HttpParam("startTime", simpleDate.format(p.getStartTime())));
        paramManager.setParam(new HttpParam("endTime", simpleDate.format(p.getEndTime())));
        paramManager.setParam(new HttpParam("location", p.getLocation().toString()));

        HttpRequestManager requestManager = new HttpRequestManager(URL, paramManager);
        requestManager.setRequestMethod("POST");
        requestManager.setRequestProperty("Authorization:", userSession.getToken());
        requestManager.startRequest();

        if (requestManager.getResponseCode() == 200)
            return true;

        if (requestManager.getResponseCode() == 201) {
            setAPIToken(userSession);
            partySearch(p, context);
        }

        return false;

    }

    public static List<Party> getPendingParty(Context context) throws IOException, ExecutionException, InterruptedException, JSONException, ParseException {
        List<Party> users = new ArrayList<>();

        userSession = new UserSession(context);
        userSession.init();

        String URL = URL_PARTYPENDING_ID + userSession.getId();

        HttpRequestManager requestManager = new HttpRequestManager(URL, null);
        requestManager.setRequestMethod("GET");
        requestManager.setRequestProperty("Authorization:", userSession.getToken());
        requestManager.startRequest();

        if (requestManager.getResponseCode() == 200) {
            JSONArray ja = new JSONArray(requestManager.getResponse());
            for (int i = 0; i < ja.length(); i++) {
                users.add(ModelParser.parsePartySearch(ja.get(i).toString()));
            }
        } else if (requestManager.getResponseCode() == 201) {
            setAPIToken(userSession);
            getPendingParty(context);
        }

        return users;
    }

    public static List<Party> getParty(Context context) throws IOException, ExecutionException, InterruptedException, JSONException, ParseException {
        List<Party> users = new ArrayList<>();

        userSession = new UserSession(context);
        userSession.init();

        String URL = URL_PARTY_ID + userSession.getId();

        HttpRequestManager requestManager = new HttpRequestManager(URL, null);
        requestManager.setRequestMethod("GET");
        requestManager.setRequestProperty("Authorization:", userSession.getToken());
        requestManager.startRequest();

        if (requestManager.getResponseCode() == 200) {
            JSONArray ja = new JSONArray(requestManager.getResponse());
            for (int i = 0; i < ja.length(); i++) {
                users.add(ModelParser.parsePendingParty(ja.get(i).toString()));
            }
        } else if (requestManager.getResponseCode() == 201) {
            setAPIToken(userSession);
            getParty(context);
        }

        return users;
    }

    public static void deletePartyPending(Context context, PendingParty p) throws IOException, ExecutionException, InterruptedException {
        userSession = new UserSession(context);
        userSession.init();

        String URL = URL_PARTY_ID + userSession.getId();

        HttpParamManager paramManager = new HttpParamManager();
        paramManager.setParam(new HttpParam("partyId", p.getId()));
        HttpRequestManager requestManager = new HttpRequestManager(URL, paramManager);
        requestManager.setRequestMethod("DELETE");
        requestManager.setRequestProperty("Authorization:", userSession.getToken());
        requestManager.startRequest();

        if (requestManager.getResponseCode() == 401) {
            setAPIToken(userSession);
            deletePartyPending(context, p);
        }
    }

    public static void deletePartySearch(Context context, PartySearch p) throws IOException, ExecutionException, InterruptedException {
        userSession = new UserSession(context);
        userSession.init();

        String URL = URL_PARTYPENDING_ID + p.getId();

        HttpRequestManager requestManager = new HttpRequestManager(URL, null);
        requestManager.setRequestMethod("DELETE");
        requestManager.setRequestProperty("Authorization:", userSession.getToken());
        requestManager.startRequest();

        if (requestManager.getResponseCode() == 401) {
            setAPIToken(userSession);
            deletePartySearch(context, p);
        }
    }

}
