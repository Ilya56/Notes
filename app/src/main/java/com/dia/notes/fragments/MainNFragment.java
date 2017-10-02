package com.dia.notes.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dia.notes.Const;
import com.dia.notes.R;
import com.dia.notes.activities.MainActivity;
import com.dia.notes.activities.newNoteActivities.NewNoteNActivity;
import com.dia.notes.adapters.NoteNAdapter;
import com.dia.notes.database.NoteNDBService;
import com.dia.notes.helper.NoteNChangeHelper;
import com.dia.notes.models.NoteNotification;

import java.util.ArrayList;

/**
 * Created by Ilya on 12.10.2016.
 */
public class MainNFragment extends CoreMainFragment<NoteNotification, NoteNAdapter> {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        assert view != null;
        listView = (ListView) view.findViewById(R.id.list_notes);
        notes = new NoteNDBService(getActivity()).getAll();
        if (notes == null || notes.get(0) == null)
            notes = new ArrayList<>();
        adapter = new NoteNAdapter(notes, getActivity());

        onMyCreate();

        return view;
    }

    @Override
    protected void openNote(NoteNotification note) {
        startActivity(new Intent(getActivity(), NewNoteNActivity.class).putExtra(Const.NOTE_KEY, note));
    }

    public NoteNotification remove() {
        return new NoteNChangeHelper(adapter, new NoteNDBService(getActivity())).remove(removePos);
    }

    public NoteNotification remove(int pos) {
        return new NoteNChangeHelper(adapter, new NoteNDBService(getActivity())).remove(pos);
    }
}
