package net.junsun.l17_geofencing;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * Created by jsun on 2/16/2016.
 */
public class GeofenceTransitionsIntentService extends IntentService {

    public GeofenceTransitionsIntentService() {
        super("GeofenceTransitionsIntentService");
    }

    // @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.e("jsun", "We have an error in geofencing intent.");
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

        Geofence target = triggeringGeofences.get(0);
        String name = target.getRequestId();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            Log.i("jsun", "Entering geofencing of " + name);
            sendNotification(target);
        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            Log.i("jsun", "Leaving geofencing of " + name);
            cancelNotification();
        } else {
            Log.w("jsun", "unknown/unexpected geofencing event : " + geofenceTransition);
        }
    }

    private void sendNotification(Geofence target) {
        String geoCode = "geo:55.942382, -4.211208?z=19";
        Intent resultIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(getApplicationContext(), 1111, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Point of interests entered")
                        .setContentText(target.getRequestId())
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(2222, mBuilder.build());
    }

    private void cancelNotification() {
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(2222);
    }
}
