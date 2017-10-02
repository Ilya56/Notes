package com.dia.notes.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dia.notes.helper.NoteChangeHelper;
import com.dia.notes.models.Note;
import com.dia.notes.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Ilya on 08.10.2016.
 */
public class NoteAdapter extends MyAdapter<Note> {
    public NoteAdapter(List<Note> notes, Activity activity) {
        super(notes, activity);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder h;
        if(view == null) {
            h = new Holder();
            view = layoutInflater.inflate(R.layout.item_note, viewGroup, false);
            h.title = (TextView) view.findViewById(R.id.title);
            h.text = (TextView) view.findViewById(R.id.text);
            h.date = (TextView) view.findViewById(R.id.date);
            view.setTag(h);
        } else {
            h = (Holder) view.getTag();
        }

        Note note = notes.get(i);
        h.title.setText(note.getTitle());
        h.text.setText(note.getText());
        String date = NoteChangeHelper.getFormatDate(note.getDate());
        h.date.setText(date);

        return view;
    }

    private class Holder {
        public TextView title;
        public TextView text;
        public TextView date;
    }
}
