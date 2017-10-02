package com.dia.notes;

import com.dia.notes.activities.newNoteActivities.NewNoteNActivity;

/**
 * Created by Ilya on 08.10.2016.
 */
public class Const {
    public static final String NOTE_KEY = "note";
    public static final String LAST_ID = "last_id";

    //db
    public static final String TABLE_NAME = "notes";
    public static final int VERSION = 1;
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String DATE = "date";
    public static final String DATE_N = "date_n";
    public static final String SEPARATOR = "separator";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER KEY, " + TITLE + " TEXT(255), " + TEXT + " TEXT(255), " + DATE + " INTEGER," + DATE_N + " INTEGER, " + SEPARATOR + " TEXT " + ");";

    //versions
    public static final NewNoteNActivity activity = new NewNoteNActivity();
}
