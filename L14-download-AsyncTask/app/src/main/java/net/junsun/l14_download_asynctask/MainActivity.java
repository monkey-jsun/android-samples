package net.junsun.l14_download_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String url="http://junsun.net/mp3/Diana%20Krall%20-%20Besame%20Mucho.mp3";
    DownloadUrlAsyncTask downloadTask;
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
                if (downloadTask != null && downloadTask.getStatus() == AsyncTask.Status.RUNNING) {
                    downloadTask.cancel(true);
                }

                downloadTask = new DownloadUrlAsyncTask(getApplicationContext(), progressBar, textView );
                downloadTask.execute(url);

                textView.append("Start downloading " + url + "\n");
            }
        });

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadTask != null)
                    downloadTask.cancel(true);
            }
        });
    }
}
