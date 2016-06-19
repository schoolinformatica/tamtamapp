package com.example.steven.tamtam;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.steven.tamtam.Models.Colleague;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class UserInfoActivity extends AppCompatActivity {

    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView imgView = (ImageView) findViewById(R.id.imgProfile);
        imgView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.pasfoto));

        Colleague c = (Colleague) getIntent().getSerializableExtra("person");
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        TextView tvDateOfBirth = (TextView) findViewById(R.id.tvDateOfBirth);
        TextView tvGamertag = (TextView) findViewById(R.id.tvGamertag);
        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvName.setText(c.getFirstname() + " " + c.getLastname());
        tvEmail.setText(c.getEmail());
        tvDateOfBirth.setText(format.format(c.getDateOfBirth()));
        tvGamertag.setText(c.getGamertag());
        tvDescription.setText(c.getDescription());


        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);



    }



}
