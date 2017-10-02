package com.dia.notes.helper;

import android.annotation.SuppressLint;
import android.util.Log;

import com.dia.notes.adapters.MyAdapter;
import com.dia.notes.database.core.CoreNoteDBHelper;
import com.dia.notes.models.Note;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ilya on 14.10.2016.
 */
public abstract class CoreNoteChangeHelper<T extends Note, U extends MyAdapter<T>, V extends CoreNoteDBHelper<T>> implements CoreHelper<T> {
    protected U adapter;
    protected int n;
    protected V service;

    public CoreNoteChangeHelper(U adapter, V service) {
        this.adapter = adapter;
        n = adapter.getCount();
        this.service = service;
    }

    @Override
    public boolean add(T note) {
        return note != null && adapter.add(note) && service.save(note);
    }

    @Override
    public int add(T note, int pos) {
        if (!service.save(note))
            return -1;
        return adapter.add(note, pos);
    }

    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= n || !service.remove(adapter.getAll().get(pos).getId()))
            return null;
        T t = adapter.remove(pos);
        if (t == null)
            return null;
        for(int i = pos; i < adapter.getAll().size(); i++)
            adapter.getAll().get(i).setId(i);
        if (!reloadDB())
                return null;
        return t;
    }

    @Override
    public void clearAll() {
        adapter.clearAll();
        service.clear();
    }

    @Override
    public boolean change(int pos, T note) {
        return pos >= 0 && pos < n && note != null && adapter.change(pos, note) && service.change(pos, note);
    }

    @Override
    public int find(T note) {
        return adapter.find(note);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getFormatDate(long date) {
        return new SimpleDateFormat("HH:mm dd.MM.yyyy").format(date);
    }

    private boolean reloadDB() {
        return service.clear() && service.saveAll(adapter.getAll());
    }
}
