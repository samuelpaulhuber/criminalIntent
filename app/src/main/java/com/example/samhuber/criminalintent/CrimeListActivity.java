package com.example.samhuber.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by samhuber on 12/15/15.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
