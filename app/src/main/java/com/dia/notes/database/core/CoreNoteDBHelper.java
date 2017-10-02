package com.dia.notes.database.core;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.dia.notes.Const;
import com.dia.notes.models.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ilya on 14.10.2016.
 */
public abstract class CoreNoteDBHelper<T extends Note> extends DBOpenHelper implements DBService<T> {
    protected Activity activity;

    public CoreNoteDBHelper(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean save(T note) {
        try {
            if (!isOpen())
                open(activity);
            return getSqLiteDatabase().insert(Const.TABLE_NAME, null, getContentValues(note)) != -1;
        } finally {
            if (isOpen())
                close();
        }
    }

    @Override
    public List<T> getAll() {
        try {
            if (!isOpen())
                open(activity);
            Cursor cursor = getSqLiteDatabase().rawQuery("select * from " + Const.TABLE_NAME, null);
            return parseCursor(cursor);
        } finally {
            if (isOpen())
                close();
        }
    }

    @Override
    public boolean remove(int i) {
        try {
            if (!isOpen())
                open(activity);
            return getSqLiteDatabase().delete(Const.TABLE_NAME, Const.ID + " = " + i, null) != 0;
        } finally {
            if (isOpen())
                close();
        }
    }

    @Override
    public boolean clear() {
        try {
            if (!isOpen())
                open(activity);
            return getSqLiteDatabase().delete(Const.TABLE_NAME, null, null) != 0;
        } finally {
            if (isOpen())
                close();
        }
    }

    protected abstract List<T> parseCursor(Cursor cursor);

    protected abstract ContentValues getContentValues(T note);

    @Override
    public boolean change(int pos, T note) {
        return remove(pos) && save(note);
    }

    protected abstract List<T> sort(List<T> notes);

    protected List<T> sortAll(T[] temp, List<T> notes) {
        temp = notes.toArray(temp);
        Arrays.sort(temp, new Note.SortByDate());
        notes = new ArrayList<>(Arrays.asList(temp));
        return notes;
    }

    @Override
    public boolean saveAll(List<T> list) {
        try {
            if (!isOpen())
                open(activity);
            boolean okey = true;
            for(int i = 0; i < list.size(); i++)
                if (getSqLiteDatabase().insert(Const.TABLE_NAME, null, getContentValues(list.get(i))) == -1)
                    okey = false;
            return okey;
        } finally {
            if (isOpen())
                close();
        }
    }
}
