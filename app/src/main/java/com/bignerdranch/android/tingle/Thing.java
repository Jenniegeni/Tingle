package com.bignerdranch.android.tingle;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Thing extends RealmObject {
    private String mWhat = null;
    private String mWhere = null;
    @PrimaryKey
    private String mId; //Realm does not allow UUID fields

    public Thing(){
    }

    public Thing(String what, String where) {
        mWhat = what;
        mWhere = where;
        mId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return oneLine("is here: ");
    }

    public String getWhat() {
        return mWhat;
    }

    public void setWhat(String what) {
        mWhat = what;
    }

    public String getWhere() {
        return mWhere;
    }

    public void setWhere(String where) {
        mWhere = where;
    }

    public String oneLine(String post) {
        return mWhat + " " + post + mWhere;
    }

    public String getId() {
        return mId;
    }
}

