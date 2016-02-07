package net.junsun.l14_download_downloadmanager;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String url="http://junsun.net/mp3/Diana%20Krall%20-%20Besame%20Mucho.mp3";
    final String file="Diana%20Krall%20-%20Besame%20Mucho.mp3";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("Download URL is " + url);

        ((Button) findViewById(R.id.button)).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("JS music club");
        request.setDescription("Downloading Diana Kroll song ...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, file);

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}
