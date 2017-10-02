package com.dia.notes.services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.dia.notes.Const;
import com.dia.notes.R;
import com.dia.notes.activities.newNoteActivities.NewNoteNActivity;
import com.dia.notes.models.Note;
import com.dia.notes.models.NoteNotification;

/**
 * Created by Ilya on 10.10.2016.
 */
public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NoteNotification note = intent.getParcelableExtra(Const.NOTE_KEY);
        Log.d("qwe", note.toString());

        Intent i = new Intent(context, NewNoteNActivity.class);
        i.putExtra(Const.NOTE_KEY, note);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setTicker(note.getTitle())
                .setAutoCancel(true)
                .setContentTitle(note.getTitle())
                .setContentText(note.getText())
                .setSound(sound);

        Notification notification = builder.getNotification();
        notification.ledARGB = Color.BLUE;
        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        notification.ledOnMS = 1000;
        notification.ledOffMS = 1000;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(note.getId(), notification);
    }
}
