package net.junsun.l16_two_pane_url_bookmarks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String url = getIntent().getExtras().getString("url");

        // prepare the argument
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        WebviewFragment webviewFragment = new WebviewFragment();
        webviewFragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(R.id.webviewContainer, webviewFragment)
                .commit();

        // return to listing
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
