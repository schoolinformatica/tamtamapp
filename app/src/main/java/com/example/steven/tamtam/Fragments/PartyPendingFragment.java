package com.example.steven.tamtam.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import com.example.steven.tamtam.Adapters.ColleagueListAdapter;
import com.example.steven.tamtam.MainActivity;
import com.example.steven.tamtam.Models.Person;
import com.example.steven.tamtam.R;
import com.example.steven.tamtam.UserInfoActivity;

import java.util.ArrayList;

/**
 * Created by steven on 6/19/16.
 */
public class PartyPendingFragment extends ListFragment implements AdapterView.OnItemClickListener {

    ArrayList<Person> list;
    ColleagueListAdapter adapter;
    EditText searchField;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.imageviewclick));
        Intent intent = new Intent(getActivity().getBaseContext(), UserInfoActivity.class);
        intent.putExtra("person", adapter.getItem(position));
        startActivity(intent);

    }
}
