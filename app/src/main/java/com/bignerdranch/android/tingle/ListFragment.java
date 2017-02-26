package com.bignerdranch.android.tingle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListFragment extends Fragment {

    private static ThingsDB thingsDB;
    private ListView thingListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        thingsDB = ThingsDB.get(getActivity());
        ArrayAdapter<Thing> adapter = new ArrayAdapter<Thing>(getActivity(), R.layout.list_item, thingsDB.getThingsDB());
        thingListView = (ListView) v.findViewById(R.id.thing_list_view);
        thingListView.setAdapter(adapter);
        return v;
    }

}
