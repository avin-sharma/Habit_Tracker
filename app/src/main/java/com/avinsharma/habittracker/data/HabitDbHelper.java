package com.avinsharma.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
        //Create SQL query
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

    //Insert Dummy Habits
    public void insertHabit(Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT, "Drink Water");
        values.put(HabitEntry.COLUMN_DESCRIPTION, "Stay hydrated");
        long newRowId = db.insert(HabitEntry.TABLE_NAME,null,values);
        Toast.makeText(context, "New Row ID: " + newRowId, Toast.LENGTH_SHORT).show();
    }

    // Returns cursor with all data
    public  Cursor readAllHabitsData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(HabitEntry.TABLE_NAME,null,null,null,null,null,null);
    }

    //Delete all rows
    public void deleteAllHabits(Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HabitEntry.TABLE_NAME,null,null);
        Toast.makeText(context, "All rows deleted!", Toast.LENGTH_SHORT).show();
    }
}
