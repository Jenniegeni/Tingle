package com.bignerdranch.android.tingle;

import java.util.UUID;

/**
 * Created by jennie on 08/02/2017.
 */

public class Thing {
    private UUID mId;
    private String mWhat = null;
    private String mWhere = null;

    public Thing(String what, String where) {
        this(UUID.randomUUID());
        mWhat = what;
        mWhere = where;
    }

    public Thing(UUID id) {
        mId = id;
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

    public UUID getId() {
        return mId;
    }
}

