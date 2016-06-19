package com.example.steven.tamtam.Adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.steven.tamtam.Httprequester.HttpParam;
import com.example.steven.tamtam.Httprequester.HttpParamManager;
import com.example.steven.tamtam.Httprequester.HttpRequestManager;
import com.example.steven.tamtam.Models.*;
import com.example.steven.tamtam.R;
import com.example.steven.tamtam.apimanager.PartySearchApiManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by steven on 5/24/16.
 */
public class PartyListAdapter extends ArrayAdapter<Party> {

    ArrayList<Party> list;
    List<Party> parties;


    public PartyListAdapter(Context context, int resource, List<Party> items) {
        super(context, resource, items);
        parties = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_row_party, null);
        }


        Party p = parties.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        if (p != null) {



            TextView tvTime = (TextView) v.findViewById(R.id.tvTime);
            TextView tvLocation = (TextView) v.findViewById(R.id.tvLocation);
            TextView tvPlayers = (TextView) v.findViewById(R.id.tvPlayers);
            ImageButton btDelete = (ImageButton) v.findViewById(R.id.btRemove);

            tvTime.setText(dateFormat.format(p.getStartTime()) + " - " + dateFormat.format(p.getEndTime()));
            tvLocation.setText(p.getLocation());

            if (p instanceof PendingParty) {
                tvPlayers.setText(((PendingParty) p).getGamersCount() + "/4");
            }

            btDelete.setBackground(getContext().getResources().getDrawable(R.drawable.close_icon));


            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Party p = getItem(position);

                    if(p instanceof PendingParty) {
                        try {
                            PartySearchApiManager.deletePartyPending(getContext(), ((PendingParty) p));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else  {
                        try {
                            PartySearchApiManager.deletePartySearch(getContext(), ((PartySearch) p));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    parties.remove(position);
                   refresh();
                }
            });

        }

        return v;

    }

    public void refresh() {
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return parties.size();
    }

    @Override
    public Party getItem(int position) {

        return parties.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

}
