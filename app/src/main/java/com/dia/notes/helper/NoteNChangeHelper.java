package com.dia.notes.helper;

import android.app.Activity;

import com.dia.notes.adapters.NoteNAdapter;
import com.dia.notes.database.NoteNDBService;
import com.dia.notes.models.NoteNotification;

/**
 * Created by Ilya on 14.10.2016.
 */
public class NoteNChangeHelper extends CoreNoteChangeHelper<NoteNotification, NoteNAdapter, NoteNDBService> {
    public NoteNChangeHelper(NoteNAdapter adapter, NoteNDBService service) {
        super(adapter, service);
    }
}
