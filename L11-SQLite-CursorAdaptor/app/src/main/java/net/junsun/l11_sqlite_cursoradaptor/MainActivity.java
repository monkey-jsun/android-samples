package net.junsun.l11_sqlite_cursoradaptor;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {
    private int maxRecId;
    ContactAdapter ca;
    ContactDbHelper dbHelper;
    Cursor cursor;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new ContactDbHelper(this);
        cursor = dbHelper.fetchAll();
        ca = new ContactAdapter(this, cursor, 0);

        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(ca);
        list.setOnItemClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewContact();
            }
        });

        maxRecId = dbHelper.getMaxRecID();
        toastShow("MacRecID is " + maxRecId);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String surname = cursor.getString(cursor.getColumnIndex("surname"));
        toastShow("Deleting Contact : " + name + " " + surname);
        deleteContact(cursor.getInt(cursor.getColumnIndex("_id")));
    }

    private void addNewContact() {
        int i = maxRecId++;

        ContactInfo ci = new ContactInfo();
        ci.name = ContactInfo.NAME_PREFIX + i;
        ci.surname = ContactInfo.SURNAME_PREFIX + i;
        ci.email = ContactInfo.EMAIL_PREFIX + i + "@test.com";
        ci.phone = randomDigit() + "-" + randomDigit() + "-" + randomDigit();

        dbHelper.add(ci);

        // update cursor as well as notifying the listview on the change
        cursor.requery();
        ca.notifyDataSetChanged();
        // alternatively we can call list.invalidateViews();
    }

    private void deleteContact(int id) {
        dbHelper.delete(id);
        cursor.requery();
        ca.notifyDataSetChanged();
    }

    private String randomDigit() {
        int num = 100 + (new Random().nextInt(900));
        return String.valueOf(num);
    }

    private void toastShow(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
