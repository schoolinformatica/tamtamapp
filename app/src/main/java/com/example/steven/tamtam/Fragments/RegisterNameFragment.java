package com.example.steven.tamtam.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import com.example.steven.tamtam.Models.User;
import com.example.steven.tamtam.NoSwipeViewPager;
import com.example.steven.tamtam.R;
import com.example.steven.tamtam.RegisterActivity;


public class RegisterNameFragment extends Fragment {

    User user;
    Button btnPrev;
    Button btnNext;
    EditText etFirstname;
    EditText etLastname;
    View inflatedView;
    NoSwipeViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((RegisterActivity) getActivity()).getUser();
        inflatedView = inflater.inflate(R.layout.fragment_register_name, container, false);
        pager = ((RegisterActivity) getActivity()).getmViewPager();
        btnPrev = (Button) inflatedView.findViewById(R.id.btnPrevious);
        btnNext = (Button) inflatedView.findViewById(R.id.btnNext);
        etFirstname = (EditText) inflatedView.findViewById(R.id.etFirstname);
        etLastname = (EditText) inflatedView.findViewById(R.id.etLastname);

        btnPrev.setOnClickListener(btnPrevListener);
        btnNext.setOnClickListener(btnNextListener);
        // Inflate the layout for this fragment

        return inflatedView;


    }


    View.OnClickListener btnPrevListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            pager.setCurrentItem(pager.getCurrentItem()-1);

        }
    };

    View.OnClickListener btnNextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            user.setFirstname(etFirstname.getText().toString());
            user.setLastname(etLastname.getText().toString());
            pager.setCurrentItem(pager.getCurrentItem()+1);
        }
    };

}
