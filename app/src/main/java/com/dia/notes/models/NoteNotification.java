package com.dia.notes.models;

import android.os.Parcel;

/**
 * Created by Ilya on 10.10.2016.
 */
public class NoteNotification extends Note {
    protected long dateNotification;

    public NoteNotification(int id, String title, String text, long date, long dateNotification) {
        super(id, title, text, date);
        this.dateNotification = dateNotification;
    }

    protected NoteNotification(Parcel in) {
        super(in);
        dateNotification = in.readLong();
    }

    public long getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(long dateNotification) {
        this.dateNotification = dateNotification;
    }

    @Override
    public String toString() {
        return super.toString() + ", date to not " + dateNotification;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(dateNotification);
    }

    public static final Creator<NoteNotification> CREATOR = new Creator<NoteNotification>() {
        @Override
        public NoteNotification createFromParcel(Parcel in) {
            return new NoteNotification(in);
        }

        @Override
        public NoteNotification[] newArray(int size) {
            return new NoteNotification[size];
        }
    };
}
