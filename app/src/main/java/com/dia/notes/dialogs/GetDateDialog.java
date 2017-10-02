package com.dia.notes.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.dia.notes.activities.newNoteActivities.NewNoteNActivity;

import java.util.Calendar;

/**
 * Created by Ilya on 10.10.2016.
 */
public class GetDateDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                ((NewNoteNActivity)getActivity()).setDate(i, i1, i2, true);
            }
        }, year, month, day);
    }
}
