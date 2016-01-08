package edu.scu.c03lifecycle;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "in onCreate", Toast.LENGTH_SHORT).show();

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "in onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "in onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "in onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "in onPause", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("counter", count);
        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "in onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "in onDestroy", Toast.LENGTH_SHORT).show();
    }
}
