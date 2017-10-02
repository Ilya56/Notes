package com.dia.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.dia.notes.Const;
import com.dia.notes.R;
import com.dia.notes.database.NoteNDBService;
import com.dia.notes.dialogs.ClearAllDialog;
import com.dia.notes.dialogs.SortDialog;
import com.dia.notes.fragments.MainNFragment;

public class MainActivity extends AppCompatActivity {
    private static MainNFragment mainActivityFragment;
    private static NoteNDBService noteDBService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteDBService = new NoteNDBService(this);

        mainActivityFragment = new MainNFragment();
        getFragmentManager().beginTransaction().add(R.id.container, mainActivityFragment, "maf").commit();

        //new NoteNDBService(this).clear();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNote();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.clear) {
            new ClearAllDialog().show(getFragmentManager(), "remove");
            return true;
        }
        if (id == R.id.sort) {
            new SortDialog().show(getFragmentManager(), "sort");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void newNote() {
        startActivity(new Intent(this, Const.activity.getClass()).putExtra(Const.LAST_ID, mainActivityFragment.getNotes().size()));
    }

    public static MainNFragment getMainActivityFragment() {
        return mainActivityFragment;
    }

    public static NoteNDBService getNoteDBService() {
        return noteDBService;
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        getFragmentManager().beginTransaction().remove(mainActivityFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getFragmentManager().findFragmentByTag("maf") == null)
            getFragmentManager().beginTransaction().add(R.id.container, mainActivityFragment).commit();
    }*/
}
