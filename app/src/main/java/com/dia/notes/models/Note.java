package com.dia.notes.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by Ilya on 08.10.2016.
 */
public class Note implements Parcelable {
    protected int id;
    protected String title;
    protected String text;
    protected long date;

    public Note(int id, String title, String text, long date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(text);
        parcel.writeLong(date);
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        text = in.readString();
        date = in.readLong();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public static class SortByDate implements Comparator<Note> {
        @Override
        public int compare(Note note, Note t1) {
            if (note == null || t1 == null)
                return 0;
            return (int) (note.date - t1.date);
        }
    }

    public static class SortByName implements Comparator<Note> {
        @Override
        public int compare(Note note, Note t1) {
            if (note == null || t1 == null)
                return 0;
            return note.title.compareTo(t1.title);
        }
    }

    @Override
    public String toString() {
        return "id " + id + ", title " + title + ", text " + text + ", date " + date;
    }
}
