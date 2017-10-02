package com.dia.notes.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dia.notes.R;
import com.dia.notes.adapters.MyAdapter;
import com.dia.notes.database.NoteNDBService;
import com.dia.notes.dialogs.RemoveDialog;
import com.dia.notes.models.Note;

import java.util.List;

/**
 * Created by Ilya on 14.10.2016.
 */
public abstract class CoreMainFragment<T extends Note, A extends MyAdapter<T>> extends Fragment {
    protected List<T> notes;
    protected ListView listView;
    protected A adapter;
    protected static int removePos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        listView = (ListView) view.findViewById(R.id.list_notes);

        return view;
    }

    protected abstract void openNote(T note);

    public List<T> getNotes() {
        return notes;
    }

    public void setNotes(List<T> notes) {
        this.notes = notes;
        adapter.setAll(notes);
    }

    public A getAdapter() {
        return adapter;
    }

    protected void onMyCreate() {
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openNote(notes.get(i));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                removePos = i;
                new RemoveDialog().show(getFragmentManager(), "remove");
                return true;
            }
        });
    }
}
