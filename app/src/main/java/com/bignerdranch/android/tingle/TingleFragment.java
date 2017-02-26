package com.bignerdranch.android.tingle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class TingleFragment extends Fragment {
    private Button addThingButton;
    private TextView lastAddedText;
    private EditText whatInput, whereInput;
    private Button listAllThings;
    private static ThingsDB thingsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void updateUI() {
        int s = thingsDB.size();
        if (s > 0) lastAddedText.setText(thingsDB.get(s - 1).toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tingle, container, false);

        thingsDB = ThingsDB.get(getActivity());
        //Accessing GUI element
        lastAddedText = (TextView) v.findViewById(R.id.last_thing);
        updateUI();
// Button
        addThingButton =(Button) v.findViewById(R.id.add_button);
// Textfields for describing a thing
        whatInput = (EditText) v.findViewById(R.id.what_text);
        whereInput = (EditText) v.findViewById(R.id.where_text);
        // view products click event
        addThingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((whatInput.getText().length() > 0) && (whereInput.getText().length() > 0)) {
                    thingsDB.addThing(
                            new Thing(whatInput.getText().toString(), whereInput.getText().toString()));
                    whatInput.setText("");
                    whereInput.setText("");
                    updateUI();
                }
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

}
