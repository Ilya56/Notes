package com.dia.notes.database.core;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import com.dia.notes.Const;
import com.dia.notes.database.core.DBHelper;

/**
 * Created by Ilya on 08.10.2016.
 */
public class DBOpenHelper {
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public boolean isOpen() {
        return sqLiteDatabase != null && dbHelper != null && sqLiteDatabase.isOpen();
    }

    public void open(Activity activity) {
        dbHelper = new DBHelper(activity, Const.TABLE_NAME, null, Const.VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public void close() {
        sqLiteDatabase.close();
    }
}
