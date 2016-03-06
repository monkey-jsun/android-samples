package net.junsun.l25_android_wear_notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final int NOTIFICATION_ID = 1111;
    final int PENDING_INTENT_REQUEST = 2222;

    Intent intent1;
    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sanfrancisco.travel/"));
        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=san+francisco"));
    }

    public void createNormalNotification(View v) {
        PendingIntent pendingIntent =
                PendingIntent.getActivity(getApplicationContext(), PENDING_INTENT_REQUEST, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_sf_map)
                        .setContentTitle("A normal notification!")
                        .setContentText("Click me to view San Francisco!")
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jsun))
                        .setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public void createActionNotification(View v) {
        PendingIntent pendingIntent =
                PendingIntent.getActivity(getApplicationContext(), PENDING_INTENT_REQUEST, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_sf_map)
                        .setContentTitle("A notification with action button!")
                        .setContentText("Click me to view San Francisco!")
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jsun))
                        .setAutoCancel(true);

        pendingIntent =
                PendingIntent.getActivity(getApplicationContext(), PENDING_INTENT_REQUEST+1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.addAction(R.drawable.ic_sf_map, "View on map", pendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public void createExtendedNotification(View v) {
        PendingIntent pendingIntent =
                PendingIntent.getActivity(getApplicationContext(), PENDING_INTENT_REQUEST, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_sf_map)
                        .setContentTitle("A notification with wearable extensions!")
                        .setContentText("Click me to view San Francisco!")
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jsun))
                        .setAutoCancel(true);

        pendingIntent =
                PendingIntent.getActivity(getApplicationContext(), PENDING_INTENT_REQUEST+1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.addAction(R.drawable.ic_sf_map, "View on map", pendingIntent);

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.jsun));
        mBuilder.extend(wearableExtender);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
