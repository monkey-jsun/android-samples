package net.junsun.l21_webview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);

        // enable javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // set javascript interface
        webView.addJavascriptInterface(new WebAppInterface(this), "AndroidInterface");

        // set webclient to handle linking
        webView.setWebViewClient(new MyWebViewClient());

        webView.loadUrl("file:///android_asset/first-page.html");

        // webView.loadUrl("http://nba.com");
        // webView.loadUrl("file:///storage/emulated/0/test.html");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    // javascript interface
    private class WebAppInterface {
        Context context;
        public WebAppInterface(Context c) {
            context=c;
        }

        @android.webkit.JavascriptInterface
        public void toast(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("junsun.net")) {
                // my external test page
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            // Otherwise, the link is a generic web page, let webview load it
            return false;
        }
    }
}
