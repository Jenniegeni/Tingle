package com.bignerdranch.android.tingle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.tingle_fragment_container);

        if (fragment == null) {
            fragment = new ListFragment();
            fm.beginTransaction().add(R.id.tingle_fragment_container, fragment).commit();
        }

    }
}
