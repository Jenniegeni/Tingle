package com.bignerdranch.android.tingle;

import android.app.Application;

import io.realm.Realm;

public class Tingle extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
