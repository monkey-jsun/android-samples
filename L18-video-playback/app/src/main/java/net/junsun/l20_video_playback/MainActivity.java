package net.junsun.l20_video_playback;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView myVideo = (VideoView) findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        myVideo.setVideoURI(videoUri);
        // myVideo.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4");
        myVideo.start();

        MediaController mc = new MediaController(this);
        myVideo.setMediaController(mc);
    }
}
