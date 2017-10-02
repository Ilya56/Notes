package com.dia.notes.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.dia.notes.activities.newNoteActivities.NewNoteNActivity;

import java.util.Calendar;

/**
 * Created by Ilya on 10.10.2016.
 */
public class GetTimeDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                ((NewNoteNActivity)getActivity()).setTime(i, i1);
            }
        }, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
