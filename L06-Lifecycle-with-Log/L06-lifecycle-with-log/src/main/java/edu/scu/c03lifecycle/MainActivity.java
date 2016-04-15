package edu.scu.c03lifecycle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private int count = 0;

    final String TAG="jsun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "in onCreate");

        // The SharedPreferences data is private to this app if Context.MODE_PRIVATE is used.
        final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        count = sharedPref.getInt("counter", 0);

        final TextView textView = (TextView) findViewById(R.id.counter);
        textView.setText(Integer.toString(count));

        Button button = (Button) findViewById(R.id.my_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textView.setText(Integer.toString(count));
            }
        });

        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "in onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "in onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "in onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "in onPause");

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("counter", count);
        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "in onDestroy");
    }
}
