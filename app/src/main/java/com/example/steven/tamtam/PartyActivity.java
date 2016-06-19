package com.example.steven.tamtam;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.steven.tamtam.Models.PartySearch;
import com.example.steven.tamtam.Models.UserSession;
import com.example.steven.tamtam.apimanager.PartySearchApiManager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class PartyActivity extends AppCompatActivity {

    EditText etStartTime;
    EditText etEndTime;
    Spinner spLocation;
    Button btSubmit;

    Date startTime;
    Date endTime;
    String location;

    UserSession userSession;

    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calendar.add(Calendar.MONTH, 1);

        userSession = new UserSession(getBaseContext());
        userSession.init();

        etStartTime = (EditText) findViewById(R.id.etStartTime);
        etEndTime = (EditText) findViewById(R.id.etEndTime);
        spLocation = (Spinner) findViewById(R.id.spLocation);
        btSubmit = (Button) findViewById(R.id.btSubmit);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spLocation.setAdapter(adapter);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        etStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PartyActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        try {
                            startTime = simpleDate.parse(
                                    calendar.get(Calendar.YEAR) + "-"
                                            + calendar.get(Calendar.MONTH) + "-"
                                            + calendar.get(Calendar.DAY_OF_MONTH) + " "
                                            + selectedHour + ":"
                                            + selectedMinute + ":00"
                            );
                            etStartTime.setText(simpleDate.format(startTime));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });

        etEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PartyActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        try {
                            endTime = simpleDate.parse(
                                    calendar.get(Calendar.YEAR) + "-"
                                            + calendar.get(Calendar.MONTH) + "-"
                                            + calendar.get(Calendar.DAY_OF_MONTH) + " "
                                            + selectedHour + ":"
                                            + selectedMinute + ":00"
                            );
                            etEndTime.setText(simpleDate.format(endTime));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = spLocation.getSelectedItem().toString();
                PartySearch partySearch = new PartySearch(startTime, endTime, location, userSession.getId());

                try {
                    if (PartySearchApiManager.partySearch(partySearch, getBaseContext())) {
                        Intent intent = new Intent(getBaseContext(), PartyPendingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
