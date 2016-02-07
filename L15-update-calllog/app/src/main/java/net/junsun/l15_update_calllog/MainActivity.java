package net.junsun.l15_update_calllog;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateCallLog();
    }

    void updateCallLog() {
        //fields to select.
        String[] strFields = {
                android.provider.CallLog.Calls.NUMBER,
                android.provider.CallLog.Calls.TYPE,
                android.provider.CallLog.Calls.CACHED_NAME,
                android.provider.CallLog.Calls.CACHED_NUMBER_TYPE,
                android.provider.CallLog.Calls._ID
        };

        //only incoming.
        String strSelection = android.provider.CallLog.Calls.TYPE + " = " + CallLog.Calls.OUTGOING_TYPE;

        //most recent first
        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";

        //get a cursor.
        Cursor cursor = getContentResolver().query(
                CallLog.Calls.CONTENT_URI, //content provider URI
                strFields, //project (fields to get)
                strSelection, //selection
                null, //selection args
                strOrder //sortorder.
        );

        // fetch the first entry, last call
        cursor.moveToFirst();
        String number = cursor.getString(0);
        String name = (cursor.getString(2) == null) ? "(null)" : cursor.getString(2);
        Log.i("jsun", "Last outgoing call is to " + name + ", " + number);

        // update the entry
        ContentValues values = new ContentValues();
        values.put(CallLog.Calls.NUMBER, "1-900-PSYCHIC");
        values.put(CallLog.Calls.CACHED_NAME, "PSYCHIC HOTLINE");
        getContentResolver().update(
                CallLog.Calls.CONTENT_URI,
                values,
                CallLog.Calls._ID + "=" + cursor.getString(4),
                null);
        Log.i("jsun", "Changed phone number to 1-900-PSYCHIC");
    }
}
