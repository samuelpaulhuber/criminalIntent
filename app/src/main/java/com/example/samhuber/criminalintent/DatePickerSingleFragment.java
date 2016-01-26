package com.example.samhuber.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.Date;

/**
 * Created by samhuber on 1/2/16.
 */
public class DatePickerSingleFragment extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_DATE = "com.example.samhuber.criminalintent.date1";

    public static Intent newIntent(Context packageContext, Date crimedate){
        Intent intent = new Intent(packageContext, DatePickerSingleFragment.class);
        intent.putExtra(EXTRA_CRIME_DATE, crimedate);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        Date crimeDate = (Date) getIntent().getSerializableExtra(EXTRA_CRIME_DATE);
        return DatePickerFragment.newInstance(crimeDate);
    }
}
