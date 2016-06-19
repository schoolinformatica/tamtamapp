package com.example.steven.tamtam.apimanager;

import com.example.steven.tamtam.Httprequester.HttpParam;
import com.example.steven.tamtam.Httprequester.HttpParamManager;
import com.example.steven.tamtam.Httprequester.HttpRequestManager;
import com.example.steven.tamtam.Models.User;
import com.example.steven.tamtam.Models.UserSession;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by steven on 6/19/16.
 */
public abstract class ApiManager {

    static UserSession userSession;

    private final static String BASE_URL = "http://145.24.222.151";

    final static String URL_API_AUTHENTICATION = BASE_URL + "/api/authenticate?";
    final static String URL_USERS_REGISTER = BASE_URL + "/users";
    final static String URL_USER_GET = BASE_URL + "/users/email/";
    final static String URL_PARTYSEARCH_ID = BASE_URL + "/partysearch/";
    final static String URL_PARTYUSERS_ID = BASE_URL + "/partyusers/";
    final static String URL_PARTYPENDING_ID = BASE_URL + "/partypending/";
    final static String URL_PARTY_ID = BASE_URL + "/party/";

    public static Boolean setAPIToken(User u) {
        HttpParamManager paramManager = new HttpParamManager();
        paramManager.setParam(new HttpParam("email", u.getEmail()));
        paramManager.setParam(new HttpParam("password", u.getPassword()));

        try {
            HttpRequestManager requestManager = new HttpRequestManager(URL_API_AUTHENTICATION, paramManager);
            requestManager.setRequestMethod("POST");
            requestManager.startRequest();
            u.setToken(new JSONObject(requestManager.getResponse()).getString("token"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
