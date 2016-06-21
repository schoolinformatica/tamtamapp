package com.example.steven.tamtam.Fragments;



import android.app.DatePickerDialog;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.steven.tamtam.Date.DatePicker;
import com.example.steven.tamtam.Models.User;
import com.example.steven.tamtam.NoSwipeViewPager;
import com.example.steven.tamtam.R;
import com.example.steven.tamtam.RegisterActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by steven on 5/25/16.
 */
public class RegisterDateFragment extends Fragment {
    User user;
    Button btnPrev;
    Button btnNext;
    EditText etDate;
    View inflatedView;
    NoSwipeViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((RegisterActivity) getActivity()).getUser();
        inflatedView = inflater.inflate(R.layout.fragment_register_date, container, false);
        pager = ((RegisterActivity) getActivity()).getmViewPager();
        btnPrev = (Button) inflatedView.findViewById(R.id.btnPrevious);
        btnNext = (Button) inflatedView.findViewById(R.id.btnNext);
        etDate = (EditText) inflatedView.findViewById(R.id.etDate);

        btnPrev.setOnClickListener(btnPrevListener);
        btnNext.setOnClickListener(btnNextListener);
        etDate.setOnClickListener(etDateListener);
        // Inflate the layout for this fragment

        return inflatedView;


    }

    private Date stringToDate(String stringDate) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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

            user.setDateOfBirth(stringToDate(etDate.getText().toString()));
            pager.setCurrentItem(pager.getCurrentItem()+1);

        }
    };

    View.OnClickListener etDateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new DatePickerDialog(getContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        }
    };

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            etDate.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
        }

    };



}
