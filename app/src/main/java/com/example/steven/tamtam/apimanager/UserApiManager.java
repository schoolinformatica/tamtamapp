package com.example.steven.tamtam.apimanager;

import android.util.Log;
import com.example.steven.tamtam.Httprequester.HttpParam;
import com.example.steven.tamtam.Httprequester.HttpParamManager;
import com.example.steven.tamtam.Httprequester.HttpRequestManager;
import com.example.steven.tamtam.Models.User;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by steven on 5/30/16.
 */
public class UserApiManager {
    private final static String BASE_URL = "http://145.24.222.151";
    private final static String URL_API_AUTHENTICATION = BASE_URL + "/api/authenticate?";
    private final static String URL_USERS_REGISTER = BASE_URL + "/users";


    public static Boolean setAPIToken(User u) {
        HttpParamManager paramManager = new HttpParamManager();
        paramManager.setParam(new HttpParam("email", u.getEmail()));
        paramManager.setParam(new HttpParam("password", u.getPassword()));

        try {
            HttpRequestManager requestManager = new HttpRequestManager(URL_API_AUTHENTICATION, paramManager);
            requestManager.setRequestMethod("POST");
            requestManager.startRequest();
            u.setToken(requestManager.getResponse());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean register(User u) {
        HttpParamManager paramManager = new HttpParamManager();
        paramManager.setParam(new HttpParam("firstname", u.getFirstname()));
        paramManager.setParam(new HttpParam("lastname", u.getLastname()));
        paramManager.setParam(new HttpParam("email", u.getEmail()));
        paramManager.setParam(new HttpParam("password", u.getPassword()));
        paramManager.setParam(new HttpParam("dateOfBirth", u.getDateOfBirth().toString()));
        paramManager.setParam(new HttpParam("gamertag", u.getGamertag()));
        paramManager.setParam(new HttpParam("description", "description"));

        try {
            HttpRequestManager requestManager = new HttpRequestManager(URL_USERS_REGISTER, paramManager);
            requestManager.setRequestMethod("POST");
            requestManager.startRequest();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return false;
    }

    public static boolean signIn(User u) {
        HttpParamManager paramManager = new HttpParamManager();
        paramManager.setParam(new HttpParam("email", u.getEmail()));
        paramManager.setParam(new HttpParam("password", u.getPassword()));

        try {
            HttpRequestManager requestManager = new HttpRequestManager(URL_API_AUTHENTICATION, paramManager);
            requestManager.setRequestMethod("POST");
            requestManager.startRequest();
            if(requestManager.getResponseCode() == 200)
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean signOut() {
        //TODO: sign out behavior
        return true;
    }

}
