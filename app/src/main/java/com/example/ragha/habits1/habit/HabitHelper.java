package com.example.ragha.habits1.habit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ragha on 7/26/2017.
 */

public class HabitHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="myhabits.db";

    public static final int version=1;

    public HabitHelper(Context context){super(context,DATABASE_NAME,null,version);}

    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_HABITS_TABLE =  "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +HabitContract.HabitEntry.COLUMN_PROJECT_NAME + " TEXT NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_PROJECT_NO_OF_PEOPLE + " INTEGER, "
                + HabitContract.HabitEntry.COLUMN_DIFFICULTY_LEVEL + " INTEGER NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_PROJECT_NO_OF_HOURS + " INTEGER NOT NULL DEFAULT 0);";


        db.execSQL(SQL_CREATE_HABITS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
