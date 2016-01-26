package com.example.samhuber.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by samhuber on 12/31/15.
 */
public class TimePickerFragment extends DialogFragment {
    private static final String ARG_DATE = "date";
    public static final String EXTRA_TIME = "com.example.samhuber.criminalintent.time";
    private TimePicker mTimePicker;

    private void sendDate(int resultCode, Date date){
        if(getTargetFragment() == null)
            return;

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    public static TimePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceBundle){
        Date date = (Date)getArguments().getSerializable(ARG_DATE);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.time_picker, null);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        mTimePicker = (TimePicker)v.findViewById(R.id.dialog_date_time_picker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(calendar.get(Calendar.HOUR));
            mTimePicker.setMinute(calendar.get(Calendar.MINUTE));
        } else {
            mTimePicker.setCurrentHour(calendar.get(Calendar.HOUR));
            mTimePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Time of Crime")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, mTimePicker.getHour());
                cal.set(Calendar.MINUTE,mTimePicker.getMinute());
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND,0);

                Date d = cal.getTime();
                sendDate(Activity.RESULT_OK, d);
            }
        }).create();
    }
}
