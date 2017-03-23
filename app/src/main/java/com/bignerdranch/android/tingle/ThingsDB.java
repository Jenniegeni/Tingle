package com.bignerdranch.android.tingle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ThingsDB extends Observable {

    private static ThingsDB sThingsDB;
    //fake database
    private List<Thing> mThingList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ThingsDB getInstance(Context context) {
        if (sThingsDB == null) {
            sThingsDB = new ThingsDB(context);
        }
        return sThingsDB;
    }

    public List<Thing> getThingList() {
        return mThingList;
    }

    public void addThing(Thing thing) {
        mThingList.add(thing);
        setChanged();
        notifyObservers();
    }

    public void deleteThing(Thing thing) {
        mThingList.remove(thing);
        setChanged();
        notifyObservers();
    }


    public int size() {
        return mThingList.size();
    }

    public Thing getThing(int i){
        return mThingList.get(i);
    }


    // Fill database for testing purposes
    private ThingsDB(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ThingBaseHelper(mContext).getWritableDatabase();
        mThingList = new ArrayList<Thing>();

        mThingList.add(new Thing("Android Phone", "Desk"));
        mThingList.add(new Thing("Big Nerd book", "Desk"));
        mThingList.add(new Thing("Pillow", "Bed"));
        mThingList.add(new Thing("Computer", "Desk"));
        mThingList.add(new Thing("T-shirt", "Wardrobe"));
        mThingList.add(new Thing("Charger", "Desk"));
        mThingList.add(new Thing("Backpack", "Floor"));
        mThingList.add(new Thing("Kleenex", "Drawer"));
    }
}