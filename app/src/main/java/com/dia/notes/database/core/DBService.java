package com.dia.notes.database.core;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * Created by Ilya on 08.10.2016.
 */
public interface DBService<T> {
    boolean save(T t);
    List<T> getAll();
    boolean remove(int i);
    boolean clear();
    boolean change(int pos, T t);
    boolean saveAll(List<T> list);
}
