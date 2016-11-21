package com.avinsharma.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.avinsharma.habittracker.data.HabitContract.HabitEntry;
import com.avinsharma.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new HabitDbHelper(this);
    }

    private void displayDatabaseInfo(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query(HabitEntry.TABLE_NAME,null,null,null,null,null,null);
        String id;
        String name;
        String description;
        String frequency;
        String value;
        try {
            TextView rows = (TextView) findViewById(R.id.rows);
            TextView data = (TextView) findViewById(R.id.data);
            rows.setText("Number of rows in the table: " + cursor.getCount());
            for (int i=0; i<cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                id = String.valueOf(cursor.getInt(cursor.getColumnIndex(HabitEntry._ID)));
                name = cursor.getString(cursor.getColumnIndex(HabitEntry.COLUMN_HABIT));
                description = cursor.getString(cursor.getColumnIndex(HabitEntry.COLUMN_DESCRIPTION));
                frequency = String.valueOf(cursor.getInt(cursor.getColumnIndex(HabitEntry.COLUMN_FREQUENCY)));
                value = String.valueOf(cursor.getInt(cursor.getColumnIndex(HabitEntry.COLUMN_VALUE)));
                data.append("\n"+ id + " " + name + " " + description + " " + frequency + " " + value);
            }
        }finally {
            cursor.close();
        }
    }

    private void insert(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT, "Drink Water");
        values.put(HabitEntry.COLUMN_DESCRIPTION, "Stay hydrated");
        long newRowId = db.insert(HabitEntry.TABLE_NAME,null,values);
        Toast.makeText(MainActivity.this, "New Row ID: " + newRowId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insert:
                insert();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
