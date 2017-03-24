package com.bignerdranch.android.tingle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.tingle.database.ThingCursorWrapper;
import com.bignerdranch.android.tingle.database.ThingDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class ThingsDB extends Observable {

    private static ThingsDB sThingsDB;
    //fake database
    //private List<Thing> mThingList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ThingsDB getInstance(Context context) {
        if (sThingsDB == null) {
            sThingsDB = new ThingsDB(context);
        }
        return sThingsDB;
    }

    public List<Thing> getThingList() {
        List<Thing> things = new ArrayList<>();
        ThingCursorWrapper cursor = queryThings(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                things.add(cursor.getThing());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return things;
    }

    public void addThing(Thing thing) {
        ContentValues values = getContentValues(thing);
        mDatabase.insert(ThingDbSchema.ThingTable.NAME, null, values);
        setChanged();
        notifyObservers();
    }

    public void deleteThing(Thing thing) {
        //mThingList.remove(thing);
        setChanged();
        notifyObservers();
    }

    public void updateThing (Thing thing) {
        String uuidString = thing.getId().toString();
        ContentValues values = getContentValues(thing);

        mDatabase.update(ThingDbSchema.ThingTable.NAME, values, ThingDbSchema.ThingTable.Cols.WHAT + " = ?", new String[] {uuidString});
    }


   /* public int size() {
        //return mThingList.size();
    }*/

    public Thing getThing(UUID id){
        ThingCursorWrapper cursor = queryThings(ThingDbSchema.ThingTable.Cols.UUID + " = ?", new String[] { id.toString()});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getThing();
        }  finally {
            cursor.close();
        }
    }


    private static ContentValues getContentValues(Thing thing) {
        ContentValues values = new ContentValues();
        values.put(ThingDbSchema.ThingTable.Cols.UUID, thing.getId().toString());
        values.put(ThingDbSchema.ThingTable.Cols.WHAT, thing.getWhat().toString());
        values.put(ThingDbSchema.ThingTable.Cols.WHERE, thing.getWhere().toString());

        return values;
    }

    private ThingCursorWrapper queryThings (String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(ThingDbSchema.ThingTable.NAME, null, whereClause, whereArgs, null, null, null);

        return new ThingCursorWrapper(cursor);
    }

    // Fill database for testing purposes
    private ThingsDB(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ThingBaseHelper(mContext).getWritableDatabase();
        //mThingList = new ArrayList<Thing>();

        /*mThingList.add(new Thing("Android Phone", "Desk"));
        mThingList.add(new Thing("Big Nerd book", "Desk"));
        mThingList.add(new Thing("Pillow", "Bed"));
        mThingList.add(new Thing("Computer", "Desk"));
        mThingList.add(new Thing("T-shirt", "Wardrobe"));
        mThingList.add(new Thing("Charger", "Desk"));
        mThingList.add(new Thing("Backpack", "Floor"));
        mThingList.add(new Thing("Kleenex", "Drawer"));
        */
    }
}