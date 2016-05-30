package com.example.steven.tamtam.Fragments;

import android.os.Bundle;
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

/**
 * Created by steven on 5/25/16.
 */
public class RegisterEmailFragment extends Fragment {
    User user;
    Button btnPrev;
    Button btnNext;
    EditText etEmail;
    View inflatedView;
    NoSwipeViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((RegisterActivity) getActivity()).getUser();
        inflatedView = inflater.inflate(R.layout.fragment_register_email, container, false);
        pager = ((RegisterActivity) getActivity()).getmViewPager();
        btnPrev = (Button) inflatedView.findViewById(R.id.btnPrevious);
        btnNext = (Button) inflatedView.findViewById(R.id.btnNext);
        etEmail = (EditText) inflatedView.findViewById(R.id.etEmail);

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
            user.setEmail(etEmail.getText().toString());
            pager.setCurrentItem(pager.getCurrentItem()+1);
        }
    };

}
