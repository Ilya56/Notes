package com.dia.notes.helper;

import com.dia.notes.adapters.NoteAdapter;
import com.dia.notes.database.NoteDBService;
import com.dia.notes.models.Note;

/**
 * Created by Ilya on 08.10.2016.
 */
public class NoteChangeHelper extends CoreNoteChangeHelper<Note, NoteAdapter, NoteDBService> {
    public NoteChangeHelper(NoteAdapter adapter, NoteDBService service) {
        super(adapter, service);
    }
}
