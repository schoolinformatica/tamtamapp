package com.example.steven.tamtam.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.steven.tamtam.Fragments.*;
import com.example.steven.tamtam.RegisterActivity;

/**
 * Created by steven on 5/25/16.
 */
public class RegisterFragmentAdapter extends FragmentPagerAdapter {


    public RegisterFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return new RegisterNameFragment();
            case 1:
                return new RegisterEmailFragment();
            case 2:
                return new RegisterDateFragment();
            case 3:
                return new RegisterGamertagFragment();
            case 4:
                return new RegisterPasswordFragment();
            case 5:
                return  new RegisterImageFragment();
            default:
                return new RegisterNameFragment();
        }

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }

}
