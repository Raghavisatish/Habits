package com.example.ragha.habits1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ragha.habits1.habit.HabitContract;
import com.example.ragha.habits1.habit.HabitHelper;

import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.COLUMN_DIFFICULTY_LEVEL;
import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.COLUMN_PROJECT_NAME;
import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.COLUMN_PROJECT_NO_OF_HOURS;
import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.COLUMN_PROJECT_NO_OF_PEOPLE;
import static com.example.ragha.habits1.habit.HabitContract.HabitEntry.TABLE_NAME;

public class NextActivity extends AppCompatActivity {


    private EditText mNameEditText;


    private EditText mPeopleEditText;


    private EditText mHoursEditText;


    private Spinner mDifficultySpinner;

    private int mLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);


        mNameEditText = (EditText) findViewById(R.id.edit_project_name);
        mPeopleEditText = (EditText) findViewById(R.id.edit_no_of_people);
        mHoursEditText = (EditText) findViewById(R.id.edit_no_of_hours);
        mDifficultySpinner = (Spinner) findViewById(R.id.spinner_level);

        setupSpinner();
    }

    private void setupSpinner() {

        ArrayAdapter levelSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_difficulty_options, android.R.layout.simple_spinner_item);


        levelSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


        mDifficultySpinner.setAdapter(levelSpinnerAdapter);


        mDifficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.level_easy))) {
                        mLevel = HabitContract.HabitEntry.PROJECT_EASY;
                    } else if (selection.equals(getString(R.string.level_medium))) {
                        mLevel = HabitContract.HabitEntry.PROJECT_MEDIUM;
                    } else {
                        mLevel = HabitContract.HabitEntry.PROJECT_DIFFICULT;
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mLevel = HabitContract.HabitEntry.PROJECT_EASY;
            }
        });


    }

    private void insertPet() {

        String nameString = mNameEditText.getText().toString().trim();
        String noOfPeople = mPeopleEditText.getText().toString().trim();
        int people = Integer.parseInt(noOfPeople);
        String noOfHours = mHoursEditText.getText().toString().trim();
        int hours = Integer.parseInt(noOfHours);


        HabitHelper mDbHelper = new HabitHelper(this);


        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(COLUMN_PROJECT_NAME, nameString);
        values.put(COLUMN_PROJECT_NO_OF_PEOPLE, people);
        values.put(COLUMN_DIFFICULTY_LEVEL, mLevel);
        values.put(COLUMN_PROJECT_NO_OF_HOURS, hours);

        long newrow = db.insert(TABLE_NAME, null, values);
        if (newrow == -1) {
            Toast.makeText(this, "not valid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "valid with id  " + newrow, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.next, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save:
                insertPet();
                finish();
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


