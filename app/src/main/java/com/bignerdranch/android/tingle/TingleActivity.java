package com.bignerdranch.android.tingle;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TingleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragmentLeft = fm.findFragmentById(R.id.fragment_container);
            if (fragmentLeft == null) {
                fragmentLeft = new TingleFragment();
                fm.beginTransaction().add(R.id.fragment_container, fragmentLeft).commit();
            }

            Fragment fragmentRight = fm.findFragmentById(R.id.fragment_right);
            if (fragmentRight == null) {
                fragmentRight = new ListFragment();
                fm.beginTransaction().add(R.id.fragment_right, fragmentRight).commit();
            }

        }
        else {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.fragment_container);

            if (fragment == null) {
                fragment = new TingleFragment();
                fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
            }

        }


    }

}