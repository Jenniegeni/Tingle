package com.bignerdranch.android.tingle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;


public class TingleFragment extends Fragment implements Observer {
    private Button addThingButton;
    private TextView lastAddedText;
    private EditText whatInput, whereInput;
    private Button listAllThings;

    private static ThingsDB thingsDB;

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void updateUI() {
        int s = thingsDB.size();
        if (s > 0) lastAddedText.setText(thingsDB.get(s - 1).toString());
        else lastAddedText.setText(R.string.empty_list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tingle, container, false);

        thingsDB = ThingsDB.get(getActivity());
        thingsDB.addObserver(this);
        //Accessing GUI element
        lastAddedText = (TextView) v.findViewById(R.id.last_thing);
        updateUI();
// Button
        addThingButton =(Button) v.findViewById(R.id.add_button);
// Textfields for describing a thing
        whatInput = (EditText) v.findViewById(R.id.what_text);
        whereInput = (EditText) v.findViewById(R.id.where_text);
        whereInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    addThing();
                    handled = true;
                }
                return handled;
            }
        });
        // view products click event
        addThingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addThing();
            }
        });
        listAllThings = (Button) v.findViewById(R.id.list_all_things);
        listAllThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    private void addThing() {
        if ((whatInput.getText().length() > 0) && (whereInput.getText().length() > 0)) {
            thingsDB.addThing(
                    new Thing(whatInput.getText().toString(), whereInput.getText().toString()));
            whatInput.setText("");
            whereInput.setText("");
            updateUI();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateUI();
    }
}
