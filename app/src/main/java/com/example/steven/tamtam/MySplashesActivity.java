package com.example.steven.tamtam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.steven.tamtam.Models.Colleague;
import com.example.steven.tamtam.apimanager.KillApiManager;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MySplashesActivity extends AppCompatActivity {
    List<Colleague> colleaguesKilled = new ArrayList<>();
    List<Colleague> colleaguesKilledBy = new ArrayList<>();

    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_splashes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refreshLists();

        ll = (LinearLayout) findViewById(R.id.rlContentScrollview);
        drawHorizontalTiles(colleaguesKilled);

        ll = (LinearLayout) findViewById(R.id.rlContentScrollviewKilledBy);
        drawHorizontalTiles(colleaguesKilledBy);



        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

    }

    private void refreshLists() {
        try {
            colleaguesKilled = KillApiManager.getKilledUsers(getBaseContext());
            colleaguesKilledBy = KillApiManager.getKilledByUsers(getBaseContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void drawHorizontalTiles(final List<Colleague> list) {
        for(int i = 0; i < list.size(); i++) {

            View child = getLayoutInflater().inflate(R.layout.item_splash, null);
            child.setId(i);

            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), UserInfoActivity.class);
                    intent.putExtra("person", list.get(v.getId()));
                    startActivity(intent);
                }
            });
            TextView txt = (TextView) child.findViewById(R.id.tvName);
            ImageView img = (ImageView) child.findViewById(R.id.imgProfile);

            img.setImageDrawable(getResources().getDrawable(R.drawable.pasfoto));
            txt.setText(list.get(i).getFirstname() + " " + list.get(i).getLastname());
            ll.addView(child);
        }
    }


}
