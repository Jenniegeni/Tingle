package com.bignerdranch.android.tingle;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TingleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { //Landscape mode
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragmentLeft = fm.findFragmentById(R.id.tingle_fragment_container);
            if (fragmentLeft == null) {
                fragmentLeft = new TingleFragment();
                fm.beginTransaction().add(R.id.tingle_fragment_container, fragmentLeft).commit();
            }

            Fragment fragmentRight = fm.findFragmentById(R.id.fragment_right);
            if (fragmentRight == null) {
                fragmentRight = new ListFragment();
                fm.beginTransaction().add(R.id.fragment_right, fragmentRight).commit();
            }

        }
        else {                                                                        //Portrait mode
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.tingle_fragment_container);

            if (fragment == null) {
                fragment = new TingleFragment();
                fm.beginTransaction().add(R.id.tingle_fragment_container, fragment).commit();
            }

        }


    }

}