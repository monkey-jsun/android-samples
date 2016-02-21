package net.junsun.l16_two_pane_url_bookmarks;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jsun on 2/15/2016.
 */
public class WebviewFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.webview, container, false);

        WebView webView = ((WebView) rootView.findViewById(R.id.webView));
        webView.loadUrl(getArguments().getString("url"));
        // force opening url links in the same WebviewActivity
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        return rootView;
    }
}
