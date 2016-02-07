package net.junsun.l13_broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jsun on 2/3/2016.
 */
public class PhoneCallInterceptor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String phoneNumber = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
        toast(context, "got broadcast " + action + " on " + phoneNumber);

        if (phoneNumber.startsWith("1900") || phoneNumber.startsWith("900")) {
            this.setResultData(null);
            this.abortBroadcast();
            toast(context, "900 number is blocked.");
        }
    }

    private void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
