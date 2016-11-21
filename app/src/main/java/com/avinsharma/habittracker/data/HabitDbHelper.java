package com.avinsharma.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.avinsharma.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Avin on 21-11-2016.
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + "("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_DESCRIPTION + " TEXT, "
                + HabitEntry.COLUMN_FREQUENCY + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_VALUE + " INTEGER NOT NULL DEFAULT -1);";
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
