package database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.samhuber.criminalintent.Crime;

import java.util.Date;
import java.util.UUID;

/**
 * Created by samhuber on 1/26/16.
 */
public class CrimeCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime(){
        String uuId = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        Long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        int solved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuId));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(solved != 0);

        return crime;
    }

}
