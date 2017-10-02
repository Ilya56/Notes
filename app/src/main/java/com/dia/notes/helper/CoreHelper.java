package com.dia.notes.helper;

/**
 * Created by Ilya on 13.10.2016.
 */
public interface CoreHelper<T> {
    boolean add(T t);
    int add(T t, int pos);
    T remove(int pos);
    void clearAll();
    boolean change(int pos, T t);
    int find(T t);
}
