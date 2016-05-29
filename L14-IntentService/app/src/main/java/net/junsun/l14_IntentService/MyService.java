package net.junsun.l14_IntentService;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jsun on 2/3/2016.
 */
public class MyService extends IntentService {

    public MyService() {
        super("MyService");
    }

    @Override
    public void onCreate() {
        Log.i("jsun", "onCreate() is called");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i("jsun", "onDestroy() is called");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "a toast", Toast.LENGTH_LONG).show();
        Log.i("jsun", "onStartCommand() is called: flags=" + flags + ", startId=" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("jsun", "onHandleIntent() is called");
        long_running();
    }

    private void long_running() {
        for(int i=0; i< 10; i++) {
            Log.i("jsun", "do something long running : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
