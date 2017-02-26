package com.bignerdranch.android.tingle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;


public class TingleFragment extends Fragment implements Observer {
    private Button addThingButton;
    private TextView lastAddedText;
    private EditText whatInput, whereInput;
    private Button listAllThings;

    private static ThingsDB thingsDB;

    @Override                                                       //onCreate
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thingsDB = ThingsDB.getInstance(getActivity());
        thingsDB.addObserver(this);
    }

    private void updateLastThing() {
        int s = thingsDB.size();
        if (s > 0) lastAddedText.setText(thingsDB.getThing(s - 1).toString());
        else lastAddedText.setText(R.string.empty_list);
    }

    @Override                                                       //OnCreateView
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tingle, container, false);

        //Accessing GUI elements
// Textview
        lastAddedText = (TextView) v.findViewById(R.id.last_thing);
        updateLastThing();
// Button
        addThingButton =(Button) v.findViewById(R.id.add_button);
        addThingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addThing();
            }
        });
// EditTexts for describing a thing
        whatInput = (EditText) v.findViewById(R.id.what_text);
        whereInput = (EditText) v.findViewById(R.id.where_text);
        whereInput.setOnEditorActionListener(new TextView.OnEditorActionListener() { //enables enter for 'sending'
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
// Button
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
            updateLastThing();
        }
    } //add thing method

    @Override
    public void update(Observable o, Object arg) { //called from notityObservers
        updateLastThing();
    }
}
