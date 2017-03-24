package com.bignerdranch.android.tingle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.tingle.database.ThingDbSchema;

public class ThingBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public ThingBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ThingDbSchema.ThingTable.NAME + "(" + " _id integer primary key autoincrement, " + ThingDbSchema.ThingTable.Cols.UUID + ", " + ThingDbSchema.ThingTable.Cols.WHAT + ", " + ThingDbSchema.ThingTable.Cols.WHERE + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
