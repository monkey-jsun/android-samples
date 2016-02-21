package net.junsun.l17_geofencing;

import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<Status>
{

    GoogleApiClient googleApiClient=null;
    ArrayList<Geofence> geofenceArrayList=null;
    PendingIntent geofencingNotifyIntent=null;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView2);

        // Create an instance of GoogleAPIClient.
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        // build geofencing list
        textView.setText("Set up 200 meter fencing for Torrance Victoria Bowling Club");
        geofenceArrayList = new ArrayList<Geofence>();
        geofenceArrayList.add(new Geofence.Builder()
                .setRequestId("Victoria Bowling Club")
                .setCircularRegion(55.942382, -4.211208, 200)
                //.setCircularRegion(55.942382, -4.214698, 160)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());

        // create pendingintent
        if (geofencingNotifyIntent == null) {
            Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
            // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling
            // addGeofences() and removeGeofences().
            geofencingNotifyIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        // hook button callbacks
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGeofencing();
            }
        });
        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopGeofencing();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        toast("onConnected()");
        // startTrackLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        toast("onConnectionSuspended()");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        toast("onConnectionFailed()");
    }

    private void startGeofencing() {
        toast("start geofencing ...");
        Log.i("jsun", "start geofencing ...");

        // build a geofencing request
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofenceArrayList);

        LocationServices.GeofencingApi.addGeofences(
                googleApiClient,
                // The GeofenceRequest object.
                builder.build(),
                // A pending intent that that is reused when calling removeGeofences(). This
                // pending intent is used to generate an intent when a matched geofence
                // transition is observed.
                geofencingNotifyIntent
        ).setResultCallback(this); // Result processed in onResult().
    }

    private void stopGeofencing() {
        toast("stop geofencing ...");
        LocationServices.GeofencingApi.removeGeofences(
                googleApiClient,
                // This is the same pending intent that was used in addGeofences().
                geofencingNotifyIntent
        ).setResultCallback(this);
    }

    @Override
    public void onResult(Status status) {
        toast("onResult() called : status=" + status.toString());
    }

    private void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /*
    private void startTrackLocation() {
        toast("Start tracking");
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(500);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, MainActivity.this);
    }

    @Override
    public void onLocationChanged(Location location) {
        textView.setText("Current location : "
                + String.format("%.4f", location.getLatitude())
                + ","
                + String.format("%.4f", location.getLongitude()));

    }
    */
}
