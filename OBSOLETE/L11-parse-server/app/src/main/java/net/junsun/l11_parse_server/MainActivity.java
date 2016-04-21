package net.junsun.l11_parse_server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseNetworkInterceptor;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);
        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
        /*
        Parse.addParseNetworkInterceptor(new ParseNetworkInterceptor() {
            @Override
            public ParseHttpResponse intercept(Chain chain) throws IOException {
                ParseHttpRequest request = chain.getRequest();
                Log.d("jsun", "Parse request is : " + request.toString() );

                ParseHttpResponse response = chain.proceed(request);
                Log.d("jsun", "Parse response is : " + response.toString());

                return response;
            }
        });
        */
        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId("parse-playground")
                        .clientKey("asdfjl343ads0asfjljl0f")
                        .server("http://52.53.229.91:8080/parse/").build()
        );

        final TextView textView = (TextView) findViewById(R.id.textView);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update check-in time
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
                Log.i("jsun", "current time is " + timeStamp);

                ParseObject checkin = new ParseObject("LastCheckIn");
                checkin.put("timestamp", timeStamp);
                checkin.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.w("jsun", "Save is done with error " + ((e==null)?"null":e.toString()));
                    }
                });
            }
        });

        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // query
                new ParseQuery<ParseObject>("LastCheckIn").findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        textView.setText("Last check-in time is " + objects.get(objects.size()-1).getString("timestamp"));
                        Log.w("jsun", "Query is done with error " + ((e == null) ? "null" : e.toString()));
                    }
                });
            }
        });
    }
}
