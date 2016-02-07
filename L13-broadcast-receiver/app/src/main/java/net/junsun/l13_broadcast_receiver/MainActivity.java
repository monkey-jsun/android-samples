package net.junsun.l13_broadcast_receiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent("net.junsun.action.VOIP_CALL");
                callIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, "8005551111");
                sendBroadcast(callIntent);
            }
        });

        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent("net.junsun.action.VOIP_CALL");
                callIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, "9005551111");
                sendOrderedBroadcast(callIntent, null);
            }
        });
    }
}
