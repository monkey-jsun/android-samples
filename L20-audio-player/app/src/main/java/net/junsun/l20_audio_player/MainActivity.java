package net.junsun.l20_audio_player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    int songs[] = {R.raw.jazz1, R.raw.jazz2, R.raw.jazz3};
    int currentSong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.jazz1);
    }

    public void start(View view) {
        mediaPlayer.start();
    }

    public void pause(View view) {
        mediaPlayer.pause();
    }

    public void rewind(View view) {
        mediaPlayer.seekTo(0);
    }

    public void next(View view) {
        if (++currentSong == 3) currentSong=0;
        mediaPlayer.stop();
        mediaPlayer.release();

        mediaPlayer = MediaPlayer.create(this, songs[currentSong]);
        mediaPlayer.start();
    }
}
