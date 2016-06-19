package com.example.steven.tamtam.apimanager;

import android.content.Context;
import android.util.Log;
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
 * Created by steven on 5/30/16.
 */
public class UserApiManager extends ApiManager {

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

    public static boolean signIn(User u, Context context) throws JSONException {
        HttpParamManager paramManager = new HttpParamManager();
        paramManager.setParam(new HttpParam("email", u.getEmail()));
        paramManager.setParam(new HttpParam("password", u.getPassword()));

        try {
            HttpRequestManager requestManager = new HttpRequestManager(URL_API_AUTHENTICATION, paramManager);
            requestManager.setRequestMethod("POST");
            requestManager.startRequest();
            if(requestManager.getResponseCode() == 200) {
                u.setToken(new JSONObject(requestManager.getResponse()).getString("token"));
                HttpParamManager param = new HttpParamManager();
                HttpRequestManager httpRequestManager = new HttpRequestManager(URL_USER_GET + u.getEmail(), param);
                httpRequestManager.setRequestMethod("GET");
                httpRequestManager.setRequestProperty("Authorization:", u.getToken());
                httpRequestManager.startRequest();

                if (httpRequestManager.getResponseCode() == 200) {
                    UserSession userSession = ModelParser.parseUserSession(httpRequestManager.getResponse(), context);
                    userSession.setToken(u.getToken());
                    userSession.setPassword(u.getPassword());
                    userSession.save();
                    return true;
                }
            }
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
