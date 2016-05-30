package com.example.steven.tamtam.Fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.steven.tamtam.Models.User;
import com.example.steven.tamtam.NoSwipeViewPager;
import com.example.steven.tamtam.R;
import com.example.steven.tamtam.RegisterActivity;

/**
 * Created by steven on 5/26/16.
 */
public class RegisterPasswordFragment extends Fragment {
    User user;
    EditText etPassword;
    EditText etPasswordCheck;
    TextView tvAlert;
    Button btnPrev;
    Button btnNext;
    View inflatedView;
    NoSwipeViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((RegisterActivity) getActivity()).getUser();
        inflatedView = inflater.inflate(R.layout.fragment_register_password, container, false);
        pager = ((RegisterActivity) getActivity()).getmViewPager();
        btnPrev = (Button) inflatedView.findViewById(R.id.btnPrevious);
        btnNext = (Button) inflatedView.findViewById(R.id.btnNext);
        etPassword = (EditText) inflatedView.findViewById(R.id.etPassword);
        etPasswordCheck = (EditText) inflatedView.findViewById(R.id.etPasswordCheck);
        tvAlert = (TextView) inflatedView.findViewById(R.id.tvAlert);

        btnPrev.setOnClickListener(btnPrevListener);
        btnNext.setOnClickListener(btnNextListener);
        // Inflate the layout for this fragment

        return inflatedView;


    }

    private boolean checkPassword() {
        if (etPassword.getText().toString().equals(
                etPasswordCheck.getText().toString()
        ))
            return true;
        return false;
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

            if (checkPassword()) {
                user.setPassword(etPassword.getText().toString());
                pager.setCurrentItem(pager.getCurrentItem()+1);
            } else {
                tvAlert.setText("Passwords do not match!");
            }


        }
    };

}
