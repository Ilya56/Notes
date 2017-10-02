package com.dia.notes.activities.newNoteActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.dia.notes.Const;
import com.dia.notes.R;
import com.dia.notes.database.NoteDBService;
import com.dia.notes.database.NoteNDBService;
import com.dia.notes.helper.NoteChangeHelper;
import com.dia.notes.models.Note;
import com.dia.notes.models.NoteNotification;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Ilya on 14.10.2016.
 */
public abstract class CoreNewNoteActivity<T extends Note> extends AppCompatActivity {
    protected T note;
    protected int id;
    protected EditText title;
    protected EditText text;
    protected int layout;
    protected boolean save = false;

    public CoreNewNoteActivity(int layout) {
        this.layout = layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);

        title = (EditText) findViewById(R.id.edit_title);
        text = (EditText) findViewById(R.id.edit_text);

        Intent i = getIntent();
        if (i != null) {
            note = i.getParcelableExtra(Const.NOTE_KEY);
            if (note != null) {
                title.setText(note.getTitle());
                text.setText(note.getText());
                id = note.getId();
            } else {
                id = i.getIntExtra(Const.LAST_ID, 0);
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home || id == R.id.save) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void save(boolean exit) {
        if (saveNote(note))
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Note not saved! There is some problems.", Toast.LENGTH_SHORT).show();
        if (exit)
            finish();
    }

    protected abstract boolean saveNote(T note);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (title.getText().toString() != "" && save)
            save(false);
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }
}
