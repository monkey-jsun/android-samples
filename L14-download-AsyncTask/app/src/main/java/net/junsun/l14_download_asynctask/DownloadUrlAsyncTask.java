package net.junsun.l14_download_asynctask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jsun on 2/4/2016.
 */
public class DownloadUrlAsyncTask extends AsyncTask<String, Integer, String> {
    private Context context;
    private PowerManager.WakeLock wakeLock;
    private ProgressBar progressBar;
    TextView textView;

    public DownloadUrlAsyncTask(Context context, ProgressBar progressBar, TextView textView) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
    }

    protected String doInBackground(String... params) {
        String url= params[0]; // only download first URL
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;

        try {
            URL u = new URL(url);
            connection = (HttpURLConnection)u.openConnection();
            connection.connect();

            // TODO: check connection OK

            // get file size
            int fileLength = connection.getContentLength();

            // do the downloading
            input = connection.getInputStream();
            output = new FileOutputStream(makeOutputFileName(url));
            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // check cancellation
                if (isCancelled()) {
                    input.close();
                    output.close();
                    return "Download is cancelled!";
                }

                total += count;
                if (fileLength > 0) publishProgress((int)(total * 100/ fileLength));
                output.write(data, 0, count);
            }

        } catch (Exception e) {
            return "Caught error : " + e.toString();
        }

        return "File is downloaded at " + makeOutputFileName(url);
    }

    private String makeOutputFileName(String url) {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        path += url.substring(url.lastIndexOf('/')+1);
        Log.i("jsun", "download file path is " + path);
        return path;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // take CPU lock to prevent CPU from going off if the user
        // presses the power button during download
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                getClass().getName());
        wakeLock.acquire();
    }

    @Override
    protected void onCancelled() {
        wakeLock.release();
        textView.append("Downloading is cancelled.\n");
    }

    @Override
    protected void onProgressUpdate(Integer... p) {
        progressBar.setProgress(p[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        wakeLock.release();
        textView.append("Downloading is done : " + result + "\n");
    }
}
