package com.example.ragha.habits1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ragha.habits1.habit.HabitContract;
import com.example.ragha.habits1.habit.HabitHelper;

import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.COLUMN_DIFFICULTY_LEVEL;
import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.COLUMN_PROJECT_NAME;
import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.COLUMN_PROJECT_NO_OF_HOURS;
import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.COLUMN_PROJECT_NO_OF_PEOPLE;
import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.habit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,NextActivity.class);
                startActivity(intent);

            }
        });
        displayDatabaseInfo();
    }
    private void displayDatabaseInfo() {

        HabitHelper mDbHelper = new HabitHelper(this);


        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                COLUMN_PROJECT_NAME,
                COLUMN_PROJECT_NO_OF_PEOPLE,
                COLUMN_PROJECT_NO_OF_HOURS,
                COLUMN_DIFFICULTY_LEVEL

        };


        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);



        try {

            TextView displayView = (TextView) findViewById(R.id.text_view_project);
            displayView.setText("Number of rows in project database table: " + cursor.getCount());



            displayView.setText("The projects table contains " + cursor.getCount() + " project.\n\n");
            displayView.append(HabitContract.HabitEntry._ID + " - " +
                    COLUMN_PROJECT_NAME + " - " +
                    COLUMN_PROJECT_NO_OF_PEOPLE + " - " +
                    COLUMN_DIFFICULTY_LEVEL + " - " +
                    COLUMN_PROJECT_NO_OF_HOURS + "\n");


            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_PROJECT_NAME);
            int peopleColumnIndex = cursor.getColumnIndex(COLUMN_PROJECT_NO_OF_PEOPLE);
            int levelColumnIndex = cursor.getColumnIndex(COLUMN_DIFFICULTY_LEVEL);
            int hoursColumnIndex = cursor.getColumnIndex(COLUMN_PROJECT_NO_OF_HOURS);


            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPeople = cursor.getInt(peopleColumnIndex);
                int currentLevel = cursor.getInt(levelColumnIndex);
                int currentHours = cursor.getInt(hoursColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPeople + " - " +
                        currentLevel + " - " +
                        currentHours));
            }
        } finally {

            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void Insertpet() {


        HabitHelper mHelper = new HabitHelper(this);

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PROJECT_NAME, "one");
        values.put(COLUMN_PROJECT_NO_OF_PEOPLE, 3);
        values.put(COLUMN_DIFFICULTY_LEVEL, 1);
        values.put(COLUMN_PROJECT_NO_OF_HOURS, 7);

        db.insert(TABLE_NAME, null, values);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.insert_dummy_data) {
            Insertpet();
            displayDatabaseInfo();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
