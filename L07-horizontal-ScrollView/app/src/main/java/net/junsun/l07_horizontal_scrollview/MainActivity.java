package net.junsun.l07_horizontal_scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View v) {
        String buttonLabel = ((Button)v).getText().toString();
        Toast.makeText(getApplicationContext(), "Button " + buttonLabel + " is clicked!", Toast.LENGTH_SHORT).show();
    }
}
