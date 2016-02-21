package net.junsun.l16_two_pane_url_bookmarks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements UrlListFragment.OnListItemClick {

    String urlName[] = {
            "Google",
            "NBA",
            "Yahoo!",
            "Weather.com",
            "YouTube",
            "Daily Joke"
    };
    String urlLink[] = {
            "http://google.com",
            "http://nba.com",
            "http://yahoo.com",
            "http://weather.com",
            "http://youtube.com",
            "http://dailyjokes.co"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pass list array
        UrlListFragment myList = (UrlListFragment)getFragmentManager().findFragmentById(R.id.listFragment);
        myList.urlName = urlName;
    }

    @Override
    public void onListItemClick(int position) {
        // check two panel mode
        if (findViewById(R.id.webviewContainer) != null) {
            // prepare the argument
            Bundle bundle = new Bundle();
            bundle.putString("url", urlLink[position]);

            WebviewFragment webviewFragment = new WebviewFragment();
            webviewFragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .replace(R.id.webviewContainer, webviewFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, WebviewActivity.class);
            intent.putExtra("url", urlLink[position]);
            startActivity(intent);
        }
    }
}
