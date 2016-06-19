package com.example.steven.tamtam.Fragments;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import com.example.steven.tamtam.Adapters.ColleagueListAdapter;
import com.example.steven.tamtam.Adapters.PartyListAdapter;
import com.example.steven.tamtam.MainActivity;
import com.example.steven.tamtam.Models.Colleague;
import com.example.steven.tamtam.Models.Party;
import com.example.steven.tamtam.Models.Person;
import com.example.steven.tamtam.R;
import com.example.steven.tamtam.RegisterActivity;
import com.example.steven.tamtam.UserInfoActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PartyListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public List<Party> list;
    PartyListAdapter mAdapter;
    EditText searchField;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new PartyListAdapter(getContext(), R.layout.list_row, list);
        setListAdapter(mAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.imageviewclick));

    }


}
