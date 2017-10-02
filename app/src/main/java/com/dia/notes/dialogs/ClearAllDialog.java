package com.dia.notes.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.FrameLayout;

import com.dia.notes.R;
import com.dia.notes.activities.MainActivity;
import com.dia.notes.helper.NoteNChangeHelper;

/**
 * Created by Ilya on 08.10.2016.
 */
public class ClearAllDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.container);
        builder.setTitle("").setMessage("Remove all notes?").
                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new NoteNChangeHelper(MainActivity.getMainActivityFragment().getAdapter(), MainActivity.getNoteDBService()).clearAll();
                        Snackbar.make(layout, "You delete all your notes", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //nothing
                    }
                });
        return builder.create();
    }
}
