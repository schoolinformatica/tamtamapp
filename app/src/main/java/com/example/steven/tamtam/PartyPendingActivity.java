package com.example.steven.tamtam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.steven.tamtam.Adapters.PartyListAdapter;
import com.example.steven.tamtam.Fragments.MyListFragment;
import com.example.steven.tamtam.Fragments.PartyListFragment;
import com.example.steven.tamtam.Httprequester.HttpParam;
import com.example.steven.tamtam.Httprequester.HttpParamManager;
import com.example.steven.tamtam.Models.Party;
import com.example.steven.tamtam.Models.PartySearch;
import com.example.steven.tamtam.Models.PendingParty;
import com.example.steven.tamtam.apimanager.PartySearchApiManager;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PartyPendingActivity extends AppCompatActivity {

    List<Party> parties = new ArrayList<>();
    List<Party> partieSearch = new ArrayList<>();

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_pending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        getParty();
    }

    public List<Party> getParties() {
        return parties;
    }

    public List<Party> getPartieSearch() {
        return partieSearch;
    }

    private void getParty() {
        try {
            parties = PartySearchApiManager.getParty(getBaseContext());
            partieSearch = PartySearchApiManager.getPendingParty(getBaseContext());


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

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            if (position == 1) {
                return new PartyListFragment() {
                    @Override
                    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                        View view = inflater.inflate(R.layout.fragment_main, container, false);

                        list = getParties();
                        return view;
                    }
                };
            } else {
                return new PartyListFragment() {
                    @Override
                    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                        View view = inflater.inflate(R.layout.fragment_main, container, false);

                        list = getPartieSearch();
                        return view;
                    }
                };
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PENDING PARTY";
                case 1:
                    return "MY PARTY";

            }
            return null;
        }
    }

}
