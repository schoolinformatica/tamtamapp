package com.example.steven.tamtam.Httprequester;

import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by steven on 5/30/16.
 */
public class HttpRequestManager {
    private URL url;
    private HttpURLConnection urlConnection;
    private HttpParamManager paramManager;
    private String response;
    private boolean get = false;
    private int responseCode;

    public HttpRequestManager(String url, HttpParamManager paramManager) throws IOException {
        this.url = new URL(url);
        urlConnection = (HttpURLConnection) this.url.openConnection();
        this.paramManager = paramManager;
    }

    public void setRequestMethod(String requestMethod) throws ProtocolException {
        this.urlConnection.setRequestMethod(requestMethod);

        if(!requestMethod.equals("POST"))
            get = true;
    }

    public void setRequestProperty(String key, String value) {
        this.urlConnection.setRequestProperty(key, value);
    }

    public String getResponse() {
        return response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void startRequest() throws ExecutionException, InterruptedException {
        response = new RequestToBackground().execute().get();
        this.urlConnection.disconnect();
    }

    private String makeHttpCall() throws IOException {

        if(!get) {
            this.urlConnection.setDoOutput(true);

            OutputStream os = this.urlConnection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            if (paramManager != null)
                writer.write(paramManager.toHttpBody());

            writer.flush();
            writer.close();

            os.close();
        }

        responseCode = urlConnection.getResponseCode();
        Log.d("reponse", responseCode + "");

        if (responseCode == 401) {
            //TODO: get Token and retry
            Log.d("token", "no token provided/invalid credentials");
        }

        InputStream in = new BufferedInputStream(urlConnection.getInputStream());

        return streamToString(in);

    }

    private String streamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private class RequestToBackground extends AsyncTask<String, String, String> {

        protected String doInBackground(String... urls) {
            try {
                return makeHttpCall();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Error in httprequest";
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}
