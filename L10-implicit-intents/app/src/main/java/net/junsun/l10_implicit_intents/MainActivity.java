package net.junsun.l10_implicit_intents;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    String intentExamples[] = {
            "ActionCall",
            "ActionDial",
            "ActionGetContent_Pictures",
            "ActionSendTo",
            "ActionSendEmail",
            "ActionViewWebPage",
            "ActionEditContacts",
            "ActionViewContacts",
            "ActionWebSearch",
            "ActionViewMapsStreetView",
            "ActionViewMapsDirections",
            "ActionViewMapsCoordinates",
            "ActionViewMapsLandmarks",
            "ActionInternalStorageSettings",
            "ActionViewMusicSD",
            "ActionMusicPlayer",
            "ActionCustomIntent",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, intentExamples));
        lv.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (intentExamples[position]) {
            case "ActionCall": ActionCall(); break;
            case "ActionDial": ActionDial(); break;
            case        "ActionGetContent_Pictures": ActionGetContent_Pictures(); break;
            case        "ActionSendTo": ActionSendTo(); break;
            case       "ActionSendEmail": ActionSendEmail(); break;
            case       "ActionViewWebPage": ActionViewWebPage(); break;
            case       "ActionEditContacts":ActionEditContacts(); break;
            case       "ActionViewContacts": ActionViewContacts(); break;
            case       "ActionWebSearch":ActionWebSearch(); break;
            case       "ActionViewMapsStreetView": ActionViewMapsStreetView(); break;
            case       "ActionViewMapsDirections": ActionViewMapsDirections(); break;
            case       "ActionViewMapsCoordinates": ActionViewMapsCoordinates(); break;
            case        "ActionViewMapsLandmarks": ActionViewMapsLandmarks(); break;
            case       "ActionInternalStorageSettings": ActionInternalStorageSettings(); break;
            case       "ActionViewMusicSD": ActionViewMusicSD(); break;
            case       "ActionMusicPlayer":ActionMusicPlayer(); break;
            case "ActionCustomIntent" : ActionCustomIntent(); break;
        }
    }

    private void ActionInternalStorageSettings() {
        startActivity(new Intent(
                android.provider.Settings.ACTION_INTERNAL_STORAGE_SETTINGS
        ));

    }

    private void ActionSendEmail() {
        // send email
        String[] emailReceiverList = {"jsun2@scu.edu"};
        String emailSubject = "howdy?";
        String emailText = "Android programming is fun!";

        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        intent.setType("vnd.android.cursor.dir/email");
        startActivity(Intent.createChooser(intent,
                "To complete action choose:"));

    }

    private void ActionViewMusicSD() {
        Intent myActivity2 = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("file://"
                + Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + "/kgmusic/download/adele - rolling in the deep.mp3");

        myActivity2.setDataAndType(data, "audio/mp3");

        startActivity(myActivity2);

    }

    private void ActionMusicPlayer() {
        Intent myActivity2 = new Intent("android.intent.action.MUSIC_PLAYER");

        startActivity(myActivity2);

    }

    private void ActionViewMapsStreetView() {
        String geoCode = "google.streetview:" + "cbll=37.3496407,-121.9409972&"
                + "cbp=1,220,0,0,0";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
        startActivity(intent);

    }

    private void ActionViewMapsDirections() {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?"
                        + "saddr=9.938083,-84.054430&"
                        + "daddr=9.926392,-84.055964"));
        startActivity(intent);

    }

    private void ActionViewMapsCoordinates() {

        // map is centered around given Lat, Long
        String geoCode = "geo:37.773972,-122.431297?z=12";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
        startActivity(intent);

    }

    private void ActionViewMapsLandmarks() {
        // (you may get multiple results)
        String thePlace = "Golden Gate Bridge, San Francisco";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=(" + thePlace + ")"));
        startActivity(intent);

    }

    private void ActionViewWebPage() {
        String myUriString = "https://www.youtube.com/results?search_query=sailing";

        Intent myActivity2 = new Intent(Intent.ACTION_VIEW,
                Uri.parse(myUriString));
        startActivity(myActivity2);

    }

    private void ActionEditContacts() {
        String myData = ContactsContract.Contacts.CONTENT_URI + "/" + "5";

        Intent myActivity2 = new Intent(Intent.ACTION_EDIT, Uri.parse(myData));

        startActivity(myActivity2);

    }

    // --------------------------------------------------------------
    private void ActionViewContacts() {
        String myData = "content://contacts/people/";
        Intent myActivity2 = new Intent(Intent.ACTION_VIEW, Uri.parse(myData));
        startActivity(myActivity2);

    }

    // --------------------------------------------------------------

    private void ActionSendTo() {
        Intent intent = new Intent(Intent.ACTION_SENDTO,
                Uri.parse("smsto:555-1111"));

        intent.putExtra("sms_body", "are we playing golf next Sunday?");

        startActivity(intent);

    }

    private void ActionGetContent_Pictures() {
        Intent intent = new Intent();

        intent.setType("image/pictures/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivity(intent);

    }

    // --------------------------------------------------------------
    private void ActionWebSearch() {

        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);

        intent.putExtra(SearchManager.QUERY, "straight hitting golf clubs");

        startActivity(intent);

    }

    // --------------------------------------------------------------
    private void ActionCall() {

        String myPhoneNumberUri = "tel:555-1234";
        Intent myActivity2 = new Intent(Intent.ACTION_CALL,
                Uri.parse(myPhoneNumberUri));
        startActivity(myActivity2);

    }

    // --------------------------------------------------------------
    private void ActionDial() {

        String myPhoneNumberUri = "tel:555-1234";
        Intent myActivity2 = new Intent(Intent.ACTION_DIAL,
                Uri.parse(myPhoneNumberUri));
        startActivity(myActivity2);

    }

    private void ActionCustomIntent() {
        startActivity(new Intent("net.junsun.action.VOIP_CALL", Uri.parse("tel:18005551212")));
    }


}
