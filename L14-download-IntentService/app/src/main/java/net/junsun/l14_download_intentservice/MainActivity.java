package net.junsun.l14_download_intentservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String url="http://junsun.net/mp3/Diana%20Krall%20-%20Besame%20Mucho.mp3";
    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.append("Start downloading " + url + "\n");

                Intent intent = new Intent(MainActivity.this, DownloadService.class);
                intent.putExtra("url", url);
                intent.putExtra("receiver", new ProgressUpdateReceiver(new Handler()));
                startService(intent);
            }
        });

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DownloadService.class);
                stopService(intent);
            }
        });
    }

    private class ProgressUpdateReceiver extends ResultReceiver {
        public ProgressUpdateReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == DownloadService.PROGRESS_UPDATE_REQUEST) {
                int progress = resultData.getInt("progress");
                progressBar.setProgress(progress);
            } else if (resultCode == DownloadService.ERROR_MESSAGE_REQUEST) {
                textView.append(resultData.getString("message") + "\n");
            }
        }
    }
}
