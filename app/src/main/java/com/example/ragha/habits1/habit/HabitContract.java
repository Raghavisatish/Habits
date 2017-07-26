package com.example.ragha.habits1.habit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ragha on 7/26/2017.
 */

public class HabitContract  {


    private HabitContract() {

//An empty private constructor makes sure that the class is not going to be initialised.
    }

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";



        public  final static String COLUMN_PROJECT_NAME="Title";
        public final static String COLUMN_PROJECT_NO_OF_PEOPLE="People";
        public final static String COLUMN_DIFFICULTY_LEVEL="Difficulty";
        public final static String COLUMN_PROJECT_NO_OF_HOURS="Hours";


        public final static int PROJECT_EASY=0;
        public final static int PROJECT_MEDIUM=1;
        public final static int PROJECT_DIFFICULT=2;


    }



}
