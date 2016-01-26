package com.example.samhuber.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by samhuber on 12/26/15.
 */
public class DatePickerFragment extends DialogFragment {
    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "com.example.samhuber.criminalintent.date";
    private DatePicker mDatePicker;

    private void sendDate(int resultCode, Date date){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        if(getTargetFragment() == null)
            getActivity().setResult(resultCode, intent);
        else
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState){
//        Date date = (Date) getArguments().getSerializable(ARG_DATE);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        View v = LayoutInflater.from(getActivity())
//                .inflate(R.layout.date_picker, null);
//
//        mDatePicker = (DatePicker)v.findViewById(R.id.dialog_date_date_picker);
//        mDatePicker.init(year, month, day, null);
//
//        return new AlertDialog.Builder(getActivity())
//                .setView(v)
//                .setTitle(R.string.date_picker_title)
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        int year = mDatePicker.getYear();
//                        int month = mDatePicker.getMonth();
//                        int day = mDatePicker.getDayOfMonth();
//
//                        Date date = new GregorianCalendar(year, month, day).getTime();
//
//                        sendDate(Activity.RESULT_OK, date);
//
//                    }
//                })
//                .create();
//    }

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = inflater.from(getActivity())
                .inflate(R.layout.date_picker_whole, null);

        mDatePicker = (DatePicker)v.findViewById(R.id.date_picker_layout);
        mDatePicker.init(year, month, day, null);

        Button mOkButton = (Button)v.findViewById(R.id.date_picker_ok);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();

                Date date = new GregorianCalendar(year, month, day).getTime();

                sendDate(Activity.RESULT_OK, date);
                getActivity().finish();
            }
        });


        return v;
    }
}
