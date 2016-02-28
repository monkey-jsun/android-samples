package net.junsun.l20_examine_mediastore;

import android.content.Intent;
import android.net.Uri;
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
                Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Intent myIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(myIntent);
            }
        });

        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                Intent myIntent = new Intent(Intent.ACTION_PICK, uri);
                startActivity(myIntent);
            }
        });

        ((Button) findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                Intent myIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(myIntent);
            }
        });
    }
}
