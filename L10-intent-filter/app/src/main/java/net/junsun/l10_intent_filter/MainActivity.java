package net.junsun.l10_intent_filter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Receive Intent Data: " + intent.getAction() + " - " + intent.getDataString());
    }
}
