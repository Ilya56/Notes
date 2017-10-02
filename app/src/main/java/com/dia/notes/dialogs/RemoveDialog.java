package com.dia.notes.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dia.notes.R;
import com.dia.notes.activities.MainActivity;
import com.dia.notes.fragments.MainNFragment;
import com.dia.notes.helper.NoteNChangeHelper;
import com.dia.notes.models.NoteNotification;

/**
 * Created by Ilya on 08.10.2016.
 */
public class RemoveDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("").setMessage("Remove this note?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoteNotification note = MainActivity.getMainActivityFragment().remove();
                        if (note != null)
                            Toast.makeText(getActivity(), getResources().getString(R.string.removed), Toast.LENGTH_SHORT).show();
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
