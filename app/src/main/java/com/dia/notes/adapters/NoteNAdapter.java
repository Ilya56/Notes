package com.dia.notes.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dia.notes.R;
import com.dia.notes.helper.NoteChangeHelper;
import com.dia.notes.models.Note;
import com.dia.notes.models.NoteNotification;

import java.util.List;

/**
 * Created by Ilya on 11.10.2016.
 */
public class NoteNAdapter extends MyAdapter<NoteNotification> {
    public NoteNAdapter(List<NoteNotification> notes, Activity activity) {
        super(notes, activity);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder h;
        if(view == null) {
            h = new Holder();
            view = layoutInflater.inflate(R.layout.item_note_n, viewGroup, false);
            h.title = (TextView) view.findViewById(R.id.title);
            h.text = (TextView) view.findViewById(R.id.text);
            h.date = (TextView) view.findViewById(R.id.date);
            h.dateN = (TextView) view.findViewById(R.id.date_n);
            view.setTag(h);
        } else {
            h = (Holder) view.getTag();
        }

        NoteNotification note = notes.get(i);
        h.title.setText(note.getTitle());
        int nmax = 100;
        String text =  note.getText().length() < nmax ? note.getText() : note.getText().substring(0, nmax) + "...";
        h.text.setText(text);
        String date = NoteChangeHelper.getFormatDate(note.getDate());
        h.date.setText(date);
        date = NoteChangeHelper.getFormatDate(note.getDateNotification());
        h.dateN.setText(date);

        return view;
    }

    private class Holder {
        public TextView title;
        public TextView text;
        public TextView date;
        public TextView dateN;
    }
}
