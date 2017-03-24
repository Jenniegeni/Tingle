package com.bignerdranch.android.tingle.database;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.tingle.Thing;

import java.util.Date;
import java.util.UUID;

public class ThingCursorWrapper extends CursorWrapper{
    public ThingCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Thing getThing() {
        String uuidString = getString(getColumnIndex(ThingDbSchema.ThingTable.Cols.UUID));
        String what = getString(getColumnIndex(ThingDbSchema.ThingTable.Cols.WHAT));
        String where = getString(getColumnIndex(ThingDbSchema.ThingTable.Cols.WHERE));


        Thing thing = new Thing(UUID.fromString(uuidString));
        thing.setWhat(what);
        thing.setWhere(where);

        return thing;
    }
}
