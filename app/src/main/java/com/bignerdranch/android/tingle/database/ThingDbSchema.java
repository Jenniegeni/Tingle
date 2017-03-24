package com.bignerdranch.android.tingle.database;


import java.util.UUID;

public class ThingDbSchema {
    public static final class ThingTable {
        public static final String NAME = "Things";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String WHAT = "what";
            public static final String WHERE = "location";
        }
    }
}
