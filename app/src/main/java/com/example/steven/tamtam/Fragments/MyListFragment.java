package com.example.steven.tamtam.Fragments;



import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import com.example.steven.tamtam.Adapters.ColleagueListAdapter;
import com.example.steven.tamtam.MainActivity;
import com.example.steven.tamtam.Models.Colleague;
import com.example.steven.tamtam.Models.Person;
import com.example.steven.tamtam.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    ArrayList<Person> list;
    ColleagueListAdapter adapter;
    EditText searchField;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        list = ((MainActivity)getActivity()).getPersonList();
        adapter = ((MainActivity)getActivity()).getAdapter();

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fillList();

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    public void fillList() {
        Colleague c = new Colleague();
        c.setFirstname("John");
        c.setLastname("Doe");
        c.setEmail("johndoe@example.com");
        c.setGamertag("grumpyOldMan123");
        c.setDateOfBirth(new Date(1985, 2, 28));
        c.setDescription("Lots and lots of text");
       list.add(0, c);
       list.add(1, c);
       list.add(2, c);

    }
}
