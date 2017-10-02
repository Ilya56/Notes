package com.dia.notes.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.dia.notes.R;
import com.dia.notes.activities.newNoteActivities.NewNoteNActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Ilya on 23.01.2017.
 */
public class SaveDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("").setMessage(getResources().getString(R.string.savenote))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Date date = new Date();
                        ((NewNoteNActivity)getActivity()).setDate(date.getYear(), date.getMonth(), date.getDay(), false);
                        getActivity().finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((NewNoteNActivity)getActivity()).setSave(false);
                        getActivity().finish();
                    }
                });
        return builder.create();
    }
}
