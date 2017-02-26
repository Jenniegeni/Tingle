package com.bignerdranch.android.tingle;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ThingsDB {

    private static ThingsDB sThingsDB;
    //fake database
    private List<Thing> mThingsDB;

    public static ThingsDB get(Context context) {
        if (sThingsDB == null) {
            sThingsDB= new ThingsDB(context);
        }
        return sThingsDB;
    }

    public List<Thing> getThingsDB() {
        return mThingsDB;
    }

    public void addThing(Thing thing) {
        mThingsDB.add(thing);
    }

    public int size() {
        return mThingsDB.size();
    }

    public Thing get(int i){
        return mThingsDB.get(i);
    }

    // Fill database for testing purposes
    private ThingsDB(Context context) {
        mThingsDB= new ArrayList<Thing>();
        mThingsDB.add(new Thing("Android Phone", "Desk"));
        mThingsDB.add(new Thing("Big Nerd book", "Desk"));
        mThingsDB.add(new Thing("Pillow", "Bed"));
        mThingsDB.add(new Thing("Computer", "Desk"));
        mThingsDB.add(new Thing("T-shirt", "Wardrobe"));
        mThingsDB.add(new Thing("Charger", "Desk"));
        mThingsDB.add(new Thing("Backpack", "Floor"));
        mThingsDB.add(new Thing("Kleenex", "Drawer"));
    }
}