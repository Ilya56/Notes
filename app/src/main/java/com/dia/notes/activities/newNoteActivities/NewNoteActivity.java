package com.dia.notes.activities.newNoteActivities;

import android.widget.EditText;

import com.dia.notes.R;
import com.dia.notes.activities.MainActivity;
import com.dia.notes.helper.NoteChangeHelper;
import com.dia.notes.database.NoteDBService;
import com.dia.notes.models.Note;

import java.util.Date;

/**
 * Created by Ilya on 08.10.2016.
 */
public class NewNoteActivity extends CoreNewNoteActivity<Note> {
    protected Note note;
    protected int id;
    protected EditText title;
    protected EditText text;

    public NewNoteActivity() {
        super(R.layout.activity_new_note);
    }

    protected boolean saveNote(Note note) {
        long date = new Date().getTime();
        NoteChangeHelper helper = null;// = new NoteChangeHelper(MainActivity.getMainActivityFragment().getAdapter(), new NoteDBService(this));
        if (note == null) {
            note = new Note(id, title.getText().toString(), text.getText().toString(), date);
            return helper.add(note);
        } else {
            note.setDate(date);
            return helper.change(note.getId(), note);
        }
        //return false;
    }
}
