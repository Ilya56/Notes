package com.dia.notes.activities.newNoteActivities;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dia.notes.Const;
import com.dia.notes.activities.MainActivity;
import com.dia.notes.R;
import com.dia.notes.database.NoteNDBService;
import com.dia.notes.dialogs.GetDateDialog;
import com.dia.notes.dialogs.GetTimeDialog;
import com.dia.notes.dialogs.SaveDialog;
import com.dia.notes.helper.NoteNChangeHelper;
import com.dia.notes.models.NoteNotification;
import com.dia.notes.services.Receiver;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Ilya on 10.10.2016.
 */
public class NewNoteNActivity extends CoreNewNoteActivity<NoteNotification> {
    protected NotificationManager notificationManager;
    protected AlarmManager alarmManager;
    protected int hour;
    protected int minute;
    protected int day;
    protected int month;
    protected int year;
    protected boolean exit = false;

    public NewNoteNActivity() {
        super(R.layout.activity_new_note_n);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Button dateTime = (Button) findViewById(R.id.date_time);
        assert dateTime != null;
        dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readDateN();
            }
        });

        Button saveB = (Button) findViewById(R.id.save);
        assert saveB != null;
        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (note == null) {
                    exit = true;
                    readDateN();
                }
                else {
                    note.setTitle(title.getText().toString());
                    note.setText(text.getText().toString());
                    long date = new Date().getTime();
                    note.setDate(date);
                    save(true);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save) {
            if (note == null) {
                exit = true;
                readDateN();
            }
            else
                finish();
            return true;
        }
        if (id == android.R.id.home) {
            if (note == null)
                new SaveDialog().show(getFragmentManager(), "save");
            else
                finish();
            return true;
        }
        if (id == R.id.remove) {
            finish();
            new NoteNChangeHelper(MainActivity.getMainActivityFragment().getAdapter(), new NoteNDBService(this)).remove(id);
        }

        return super.onOptionsItemSelected(item);
    }

    public void readDateN() {
        new GetDateDialog().show(getFragmentManager(), "date");
    }

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        if (exit) {
            save = true;
            finish();
        }
        else
            save(false);
    }

    public void setDate(int year, int month, int day, boolean show) {
        this.year = year;
        this.month = month;
        this.day = day;
        if (show)
            new GetTimeDialog().show(getFragmentManager(), "time");
    }

    @Override
    protected boolean saveNote(NoteNotification note) {
        long date = new Date().getTime();
        Calendar c = new GregorianCalendar(year, month, day, hour, minute);
        NoteNChangeHelper helper = new NoteNChangeHelper(MainActivity.getMainActivityFragment().getAdapter(), new NoteNDBService(this));
        long dateN = c.getTime().getTime();
        if (note == null) {
            this.note = new NoteNotification(id, title.getText().toString(), text.getText().toString(), date, dateN);
            return helper.add(this.note);
        } else {
            note.setDateNotification(dateN);
            return helper.change(note.getId(), note);
        }
    }

    protected void createIntent() {
        Intent intent = new Intent(this, Receiver.class);
        intent.setAction("action");
        intent.putExtra(Const.NOTE_KEY, note);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long date = new Date().getTime();
        long dateN = new GregorianCalendar(year, month, day, hour, minute).getTime().getTime();
        long delta = dateN - date;

        if (delta > 0)
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + delta, pendingIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (title.getText().toString() != "" && note != null) {
            createIntent();
        }
    }
}
