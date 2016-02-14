package net.junsun.l15_update_calllog;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        displayLastOutgoingCall();

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCallLog();
            }
        });

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLastOutgoingCall();
            }
        });
    }

    void updateCallLog() {
        //only incoming.
        String selection = android.provider.CallLog.Calls.TYPE + " = " + CallLog.Calls.OUTGOING_TYPE;

        //most recent first
        String order = android.provider.CallLog.Calls.DATE + " DESC";

        //get a cursor.
        Cursor cursor = getContentResolver().query(
                CallLog.Calls.CONTENT_URI, //content provider URI
                null, //projection (null to select all)
                selection, //selection
                null, //selection args
                order //sortorder.
        );

        // fetch the first entry, last call
        cursor.moveToFirst();

        // update the entry
        ContentValues values = new ContentValues();
        values.put(CallLog.Calls.NUMBER, "1-900-PSYCHIC");
        values.put(CallLog.Calls.CACHED_NAME, "PSYCHIC HOTLINE");
        getContentResolver().update(
                CallLog.Calls.CONTENT_URI,
                values,
                CallLog.Calls._ID + "=" + cursor.getString(cursor.getColumnIndex(CallLog.Calls._ID)),
                null);
        Log.i("jsun", "Changed phone number to 1-900-PSYCHIC");
    }

    private void displayLastOutgoingCall() {
        //only incoming.
        String selection = android.provider.CallLog.Calls.TYPE + " = " + CallLog.Calls.OUTGOING_TYPE;

        //most recent first
        String order = android.provider.CallLog.Calls.DATE + " DESC";

        //get a cursor.
        Cursor cursor = getContentResolver().query(
                CallLog.Calls.CONTENT_URI, //content provider URI
                null, //projection (null to select all)
                selection, //selection
                null, //selection args
                order //sortorder.
        );

        // fetch the first entry, last call
        cursor.moveToFirst();

        // get field values
        String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
        String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
        textView.setText("Last outgoing call:\n\tName : " + name + "\n\tNumber: " + number);
    }

}
