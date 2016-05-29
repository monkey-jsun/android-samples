package net.junsun.l15_contact_content_provider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        // startManagingCursor(cursor);

        String from[] = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        int to[] = {
                android.R.id.text1,
                android.R.id.text2
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phone = ((TextView)view.findViewById(android.R.id.text2)).getText().toString();
                Toast.makeText(getApplicationContext(), "Call " + phone, Toast.LENGTH_SHORT).show();
            }
        });

        // Just to show another way to work with cursor
        //ListSelectedContacts();
    }

    private void ListSelectedContacts() {
        // list all phone numbers not in US or Canada
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;  // content://com.android.contacts/data/phones
        String projection[] = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        String selection = ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER + " NOT LIKE ? ";
        String argument[] = {"+1%"};
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC";

        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                uri,
                projection,
                selection,
                argument,
                sortOrder);

        // display
        while (cursor.moveToNext()) {
            Log.d("jsun", cursor.getString(0) + " : " + cursor.getString(1));
        }
        cursor.close();

        // display URI
        Log.i("jsun", "URI is " + uri.toString());
    }
}
