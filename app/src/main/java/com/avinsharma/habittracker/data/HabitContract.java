package com.avinsharma.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Avin on 21-11-2016.
 */
public final class HabitContract {

    private HabitContract(){}

    public static class HabitEntry implements BaseColumns{

        public static final String TABLE_NAME = "habits";
        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_HABIT = "habit";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_FREQUENCY = "frequency";
        public static final String COLUMN_VALUE = "value";
    }
}
