package net.junsun.morewidgets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton);
                    if (tb.isChecked()) {
                        progress += 5;
                        if (progress >= 100) progress -= 100;
                        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
                        pb.setProgress(progress);
                        Log.i("jsun", "Set progress to " + progress);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

        Button iconButton = (Button) findViewById(R.id.button);
        iconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button with icon is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Image button is clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ImageView is clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String message = isChecked ? "Switch is ON" : "Switch is OFF";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        SeekBar sb=(SeekBar) findViewById(R.id.seekBar);
        sb.setProgress(50);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(getApplicationContext(), (fromUser? "User" : "System") + "change value to " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("jsun", "seekbar dragging start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("jsun", "seekbar dragging stop");
            }
        });

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), "Rating is changed to " + rating, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
