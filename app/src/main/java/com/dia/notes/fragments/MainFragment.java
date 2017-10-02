package com.dia.notes.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dia.notes.Const;
import com.dia.notes.activities.newNoteActivities.NewNoteActivity;
import com.dia.notes.database.NoteDBService;
import com.dia.notes.models.Note;
import com.dia.notes.adapters.NoteAdapter;
import com.dia.notes.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends CoreMainFragment<Note, NoteAdapter> {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        assert view != null;
        listView = (ListView) view.findViewById(R.id.list_notes);
        notes = new NoteDBService(getActivity()).getAll();
        if (notes == null || notes.get(0) == null)
            notes = new ArrayList<>();
        adapter = new NoteAdapter(notes, getActivity());

        onMyCreate();

        return view;
    }

    @Override
    protected void openNote(Note note) {
        startActivity(new Intent(getActivity(), NewNoteActivity.class).putExtra(Const.NOTE_KEY, note));
    }

    public static int getRemovePos() {
        return removePos;
    }
}
