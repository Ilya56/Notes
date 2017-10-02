package com.dia.notes.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.dia.notes.Const;
import com.dia.notes.database.core.CoreNoteDBHelper;
import com.dia.notes.database.core.DBOpenHelper;
import com.dia.notes.database.core.DBService;
import com.dia.notes.models.Note;
import com.dia.notes.models.NoteNotification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ilya on 08.10.2016.
 */
public class NoteDBService extends CoreNoteDBHelper<Note> {
    public NoteDBService(Activity activity) {
        super(activity);
    }

    @Override
    protected List<Note> parseCursor(Cursor cursor) {
        List<Note> notes = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Const.ID));
                String title = cursor.getString(cursor.getColumnIndex(Const.TITLE));
                String text = cursor.getString(cursor.getColumnIndex(Const.TEXT));
                long date = cursor.getLong(cursor.getColumnIndex(Const.DATE));
                notes.add(new Note(id, title, text, date));
            } while(cursor.moveToNext());
            notes = sort(notes);
            return notes;
        }
        return null;
    }

    @Override
    protected ContentValues getContentValues(Note note) {
        ContentValues c = new ContentValues();
        c.put(Const.ID, note.getId());
        c.put(Const.TITLE, note.getTitle());
        c.put(Const.TEXT, note.getText());
        c.put(Const.DATE, note.getDate());
        return c;
    }

    @Override
    protected List<Note> sort(List<Note> notes) {
        Note[] temp = new Note[notes.size()];
        return sortAll(temp, notes);
    }
}
