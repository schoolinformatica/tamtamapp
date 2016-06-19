package com.example.steven.tamtam.apimanager;

import android.content.Context;
import android.util.Log;
import com.example.steven.tamtam.Httprequester.HttpParam;
import com.example.steven.tamtam.Httprequester.HttpParamManager;
import com.example.steven.tamtam.Httprequester.HttpRequestManager;
import com.example.steven.tamtam.Models.PartySearch;
import com.example.steven.tamtam.Models.UserSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

}
