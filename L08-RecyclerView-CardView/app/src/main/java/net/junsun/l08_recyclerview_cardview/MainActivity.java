package net.junsun.l08_recyclerview_cardview;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<ContactInfo> nameList;
    private int numNames = 0;
    ContactAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recList = (RecyclerView) findViewById(R.id.nameList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        nameList = new ArrayList<ContactInfo>();
        ca = new ContactAdapter(nameList);
        recList.setAdapter(ca);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewContact();
                ca.notifyItemInserted(nameList.size()-1);
            }
        });

        TouchHelperCallback callback = new TouchHelperCallback(ca);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recList);
    }

    private void addNewContact() {

        int i = numNames++;

        ContactInfo ci = new ContactInfo();
        ci.name = ContactInfo.NAME_PREFIX + i;
        ci.surname = ContactInfo.SURNAME_PREFIX + i;
        ci.email = ContactInfo.EMAIL_PREFIX + i + "@test.com";
        ci.phone = randomDigit() + "-" + randomDigit() + "-" + randomDigit();

        nameList.add(ci);
    }

    private String randomDigit() {
        int num = 100 + (new Random().nextInt(900));
        return String.valueOf(num);
    }
}
