package net.junsun.l14_asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    DownloadTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                progressBar.setProgress(0);

                if (downloadTask != null && downloadTask.getStatus() == AsyncTask.Status.RUNNING) {
                    downloadTask.cancel(true);
                }

                downloadTask = new DownloadTask();
                downloadTask.execute("Start running");
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

    private class DownloadTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            for (int progress=0; progress<=100; progress+=1) {
                if(this.isCancelled()) return "Cancelled!";
                publishProgress(progress);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "All done!";
        }

        @Override
        protected void onPreExecute() {
            textView.append("onPreExecute() is called\n");
        }

        @Override
        protected void onCancelled() {
            textView.append("onCancelled() is called\n");
        }

        @Override
        protected void onProgressUpdate(Integer... p) {
            Log.i("jsun", "Current progress is " + p[0]);
            progressBar.setProgress(p[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            textView.append("onPostExecute() is called : " + result + "\n");
        }
    }
}
