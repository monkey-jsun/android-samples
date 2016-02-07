package net.junsun.l14_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jsun on 2/3/2016.
 */
public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.i("jsun", "onCreate() is called");
    }

    @Override
    public void onDestroy() {
        Log.i("jsun", "onDestroy() is called");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("jsun", "onStartCommand() is called: flags=" + flags + ", startId=" + startId);

        // long_running();

        return START_STICKY;
    }

    private void long_running() {
        for(int i=0; i< 100; i++) {
            Log.i("jsun", "do something long running : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
