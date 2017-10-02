package com.dia.notes.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.dia.notes.activities.MainActivity;
import com.dia.notes.models.Note;
import com.dia.notes.models.NoteNotification;

import java.util.Arrays;

/**
 * Created by Ilya on 17.03.2017.
 */
public class SortDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] list = {"Name", "Date"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sort by").
                setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoteNotification[] array = new NoteNotification[1];
                        array = MainActivity.getMainActivityFragment().getNotes().toArray(array);
                        switch (i) {
                            case 0:
                                Arrays.sort(array, new Note.SortByName());
                                MainActivity.getMainActivityFragment().setNotes(Arrays.asList(array));
                                break;
                            case 1:
                                Arrays.sort(array, new Note.SortByDate());
                                MainActivity.getMainActivityFragment().setNotes(Arrays.asList(array));
                                break;
                        }
                    }
                });
        return builder.create();
    }
}
