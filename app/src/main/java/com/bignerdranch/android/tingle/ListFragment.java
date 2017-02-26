package com.bignerdranch.android.tingle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

public class ListFragment extends Fragment implements Observer {

    private static ThingsDB thingsDB;
    private ListView thingListView;
    private ArrayAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        thingsDB = ThingsDB.getInstance(getActivity());
        thingsDB.addObserver(this);
        listAdapter = new ArrayAdapter<Thing>(getActivity(), R.layout.list_item, thingsDB.getThingList());
        thingListView = (ListView) v.findViewById(R.id.thing_list_view);
        thingListView.setAdapter(listAdapter);

        thingListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) { //when an item is being longClicked
                final int POSITION = position;
                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(getActivity());    //creating an alert dialog
                deleteAlert.setMessage(R.string.dialog_message)
                        .setTitle(R.string.dialog_title);
                deleteAlert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        thingsDB.deleteThing((Thing)thingListView.getItemAtPosition(POSITION));
                        // User clicked yes button
                    }
                });
                deleteAlert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                deleteAlert.show();
                return true;
            }
        });

        return v;
    }

    @Override
    public void update(Observable o, Object arg) {
        listAdapter.notifyDataSetChanged();

    }
}
