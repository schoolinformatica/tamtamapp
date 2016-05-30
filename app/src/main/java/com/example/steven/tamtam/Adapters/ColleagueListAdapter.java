package com.example.steven.tamtam.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.steven.tamtam.Models.Colleague;
import com.example.steven.tamtam.Models.Person;
import com.example.steven.tamtam.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 5/24/16.
 */
public class ColleagueListAdapter extends ArrayAdapter<Person> implements Filterable {

    ArrayList<Person> list;
    ArrayList<Person> persons;


    public ColleagueListAdapter(Context context, int resource, ArrayList<Person> items) {
        super(context, resource, items);
        persons = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_row, null);
        }


        Person p = persons.get(position);

        if (p != null) {
            ImageView img = (ImageView) v.findViewById(R.id.image);
            TextView txtname = (TextView) v.findViewById(R.id.name);
            TextView txtgamertag = (TextView) v.findViewById(R.id.gamertag);

            if (img != null) {
                img.setImageDrawable(getContext().getResources().getDrawable(R.drawable.pasfoto));
            }

            if (txtname != null) {
                txtname.setText(p.getFirstname() + " " + p.getLastname());
            }

            if (txtgamertag != null) {
                txtgamertag.setText(p.getGamertag());
            }
        }

        return v;

    }

    @Override
    public int getCount() {

        return persons.size();
    }

    @Override
    public Person getItem(int position) {

        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }


    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Person> results = new ArrayList<>();
                if (list == null)
                    list = persons;
                if (constraint != null) {
                    if (list != null && list.size() > 0) {
                        for (final Person p : list) {
                            if (p.getGamertag().toLowerCase().contains(constraint) ||
                                    (p.getFirstname().toLowerCase() + " " + p.getLastname().toLowerCase()).contains(constraint)) {
                                results.add(p);
                            }
                        }
                    }
                    oReturn.values = results;
                    oReturn.count = results.size();
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                persons = (ArrayList<Person>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
