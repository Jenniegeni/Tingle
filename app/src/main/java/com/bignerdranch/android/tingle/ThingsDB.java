package com.bignerdranch.android.tingle;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmResults;

public class ThingsDB extends Observable {

    private static Realm realm;
    private static ThingsDB sThingsDB;
    //fake database
    private List<Thing> mThingList;


    public static ThingsDB getInstance(Context context) {
        if (sThingsDB == null) {
            realm = Realm.getDefaultInstance();
            sThingsDB = new ThingsDB(context);
        }
        return sThingsDB;
    }


    public OrderedRealmCollection<Thing> getThingsDB() {
        return realm.where(Thing.class).findAll();    }

    public void addThing(Thing thing) {
        final Thing fThing = thing;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(fThing);
            }});
        setChanged();
        notifyObservers();
    }

    public void deleteThing(Thing thing) {
        final Thing fThing= thing;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Thing> rows=realm.where(Thing.class).
                        equalTo("mId", fThing.getId()).findAll();
                if (rows.size() > 0) rows.get(0).deleteFromRealm();
            }});        setChanged();
        notifyObservers();
    }


    public int size() {
        return getThingsDB().size();
    }

    public Thing getThing(int i){
        return getThingsDB().get(i);
    }


    // Fill database for testing purposes
    private ThingsDB(Context context) {
    }
}