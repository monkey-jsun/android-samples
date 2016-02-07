package net.junsun.l12_notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final int notificationId = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates an explicit intent for an Activity in your app
                int requestCode=2222;
                Intent resultIntent = new Intent(MainActivity.this, HandleNotificationActivity.class);
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(getApplicationContext(), requestCode, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.jinggling_on)
                                .setContentTitle("A simple notification!")
                                .setContentText("Hello, there! Click me!")
                                .setContentIntent(resultPendingIntent)
                                .setAutoCancel(true);
                                // .addAction(R.drawable.ic_fish, "Fish", resultPendingIntent);

                //mBuilder.setStyle(createBigContent());

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(notificationId, mBuilder.build());
            }

            NotificationCompat.InboxStyle createBigContent() {
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                String[] events = {"First event", "second event", "Third event", "Forth event"};
                // Sets a title for the Inbox in expanded layout
                inboxStyle.setBigContentTitle("Event tracker details:");
                // Moves events into the expanded layout
                for (int i=0; i < events.length; i++) {
                    inboxStyle.addLine(events[i]);
                }
                inboxStyle.setSummaryText("There are total of " + events.length + " events");
                return inboxStyle;
            }
        });

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(notificationId);
            }
        });
    }
}
