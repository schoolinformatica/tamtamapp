package com.example.steven.tamtam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.steven.tamtam.Httprequester.HttpParam;
import com.example.steven.tamtam.Httprequester.HttpParamManager;
import com.example.steven.tamtam.Httprequester.HttpRequestManager;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class HttpActivity extends AppCompatActivity {
    String[] perms = {"android.permission.INTERNET"};

    private TextView tvAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvAlert = (TextView) findViewById(R.id.tvResponse);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       tvAlert.setText(makeRequest());
    }

    private String makeRequest() {
        requestPermissions(perms, 200);
        String url = "http://145.24.222.151/users";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYsImlzcyI6Imh0dHA6XC9cLzE0NS4yNC4yMjIuMTUxXC9hcGlcL2F1dGhlbnRpY2F0ZSIsImlhdCI6MTQ2NDYzMzQ5MiwiZXhwIjoxNDY0NjM3MDkyLCJuYmYiOjE0NjQ2MzM0OTIsImp0aSI6ImY3ZWY5NmMwNzRmZTkyMzhlYTU1ZDM0ZTJlYzUzYTI0In0.H5ikv_ltzDPMinPMiU8dLccCbmhPdpqOfGdcjaQdqvs";


          HttpParamManager paramManager = new HttpParamManager();
//        paramManager.setParam(new HttpParam("email", "test@test.nl"));
//        paramManager.setParam(new HttpParam("password", "project78"));

        try {
            HttpRequestManager requestManager = new HttpRequestManager(url, paramManager);
            requestManager.setRequestMethod("GET");
            requestManager.setRequestProperty("Authorization", "Bearer " + token);
            requestManager.startRequest();
            return requestManager.getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "Null";
    }



}
