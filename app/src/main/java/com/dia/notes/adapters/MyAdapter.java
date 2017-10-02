package com.dia.notes.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.dia.notes.models.Note;

import java.util.List;

/**
 * Created by Ilya on 08.10.2016.
 */
public abstract class MyAdapter<T extends Note> extends BaseAdapter {
    protected List<T> notes;
    protected LayoutInflater layoutInflater;

    public MyAdapter(List<T> notes, Activity activity) {
        this.notes = notes;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public boolean add(T t) {
        boolean temp = notes.add(t);
        notifyDataSetChanged();
        return temp;
    }

    public int add(T t, int pos) {
        if (notes.size() < pos)
            notes.add(pos, t);
        else {
            pos = notes.size();
            notes.add(t);
        }
        notifyDataSetChanged();
        return pos;
    }

    public boolean remove(T t) {
        boolean temp = notes.remove(t);
        notifyDataSetChanged();
        return temp;
    }

    public T remove(int pos) {
        T temp = notes.remove(pos);
        notifyDataSetChanged();
        return temp;
    }

    public void clearAll() {
        if (notes != null && !notes.isEmpty())
            notes.clear();
        notifyDataSetChanged();
    }

    public boolean change(int pos, T note) {
        if (pos < 0 || pos >= notes.size())
            return false;
        remove(pos);
        notes.add(pos, note);
        notifyDataSetChanged();
        return true;
    }

    public int find(T t) {
        return notes.indexOf(t);
    }

    public void setAll(List<T> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public List<T> getAll() {
        return notes;
    }
}
