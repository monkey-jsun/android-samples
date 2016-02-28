package net.junsun.l20_audio_player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    String songs[] = {
            "android.resource://net.junsun.l20_audio_player/" + R.raw.jazz1,
            "android.resource://net.junsun.l20_audio_player/" + R.raw.jazz2,
            "android.resource://net.junsun.l20_audio_player/" + R.raw.jazz3,
            "http://junsun.net/local/vincent.mp3",
            "file:///storage/emulated/0/Download/Diana Krall - Besame Mucho.mp3"
    };
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

    public void next(View view) throws IOException {
        if (++currentSong == songs.length) currentSong=0;

        mediaPlayer.reset();
        mediaPlayer.setDataSource(this, Uri.parse(songs[currentSong]));
        mediaPlayer.prepare();
        mediaPlayer.start();
    }
}
