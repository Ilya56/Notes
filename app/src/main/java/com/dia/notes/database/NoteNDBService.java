package com.dia.notes.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.dia.notes.Const;
import com.dia.notes.database.core.CoreNoteDBHelper;
import com.dia.notes.models.Note;
import com.dia.notes.models.NoteNotification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ilya on 10.10.2016.
 */
public class NoteNDBService extends CoreNoteDBHelper<NoteNotification> {
    public NoteNDBService(Activity activity) {
        super(activity);
    }

    protected List<NoteNotification> parseCursor(Cursor cursor) {
        List<NoteNotification> notes = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Const.ID));
                String title = cursor.getString(cursor.getColumnIndex(Const.TITLE));
                String text = cursor.getString(cursor.getColumnIndex(Const.TEXT));
                long date = cursor.getLong(cursor.getColumnIndex(Const.DATE));
                long dateN = cursor.getLong(cursor.getColumnIndex(Const.DATE_N));
                notes.add(new NoteNotification(id, title, text, date, dateN));
            } while(cursor.moveToNext());
            notes = sort(notes);
            return notes;
        }
        return null;
    }

    protected ContentValues getContentValues(NoteNotification note) {
        ContentValues c = new ContentValues();
        c.put(Const.ID, note.getId());
        c.put(Const.TITLE, note.getTitle());
        c.put(Const.TEXT, note.getText());
        c.put(Const.DATE, note.getDate());
        c.put(Const.DATE_N, note.getDateNotification());
        return c;
    }

    @Override
    protected List<NoteNotification> sort(List<NoteNotification> notes) {
        NoteNotification[] temp = new NoteNotification[notes.size()];
        return sortAll(temp, notes);
    }
}
