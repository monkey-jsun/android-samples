package net.junsun.l14_download_intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jsun on 2/5/2016.
 */
public class DownloadService extends IntentService {
    public static final int PROGRESS_UPDATE_REQUEST=1234;
    public static final int ERROR_MESSAGE_REQUEST=1235;

    Boolean cancelled=false;
    ResultReceiver receiver;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url= intent.getStringExtra("url");
        receiver = intent.getParcelableExtra("receiver");

        String outputFileName=makeOutputFileName(url);
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;

        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.connect();

            // TODO: check connection OK

            // get file size
            int fileLength = connection.getContentLength();

            // do the downloading
            input = connection.getInputStream();
            output = new FileOutputStream(outputFileName);
            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // voluntarily check for cancellation
                if (cancelled) {
                    input.close();
                    output.close();
                    (new File(outputFileName)).delete();
                    reportError("Downloading is cancelled");
                    Log.w("jsun", "Downloading is cancelled");
                    return;
                }

                // do the writing
                output.write(data, 0, count);

                // pass message to do UI progress update
                Bundle bundle = new Bundle();
                total += count;
                bundle.putInt("progress", (int)(total*100/fileLength));
                receiver.send(PROGRESS_UPDATE_REQUEST, bundle);
            }
        } catch (Exception e) {
            Log.e("jsun", "DownloadService:onHandleIntent() error : " + e.toString());
            reportError("DownloadService:onHandleIntent() error : " + e.toString());
            return;
        }

        // report
        reportError("Downloading is done, file is left at " + outputFileName);
    }

    @Override
    public void onDestroy() {
        cancelled = true;
        super.onDestroy();
    }

    private String makeOutputFileName(String url) {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        path += "/" + url.substring(url.lastIndexOf('/')+1);
        Log.i("jsun", "download file path is " + path);
        return path;
    }

    private void reportError(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("message", msg);
        receiver.send(ERROR_MESSAGE_REQUEST, bundle);
    }
}
