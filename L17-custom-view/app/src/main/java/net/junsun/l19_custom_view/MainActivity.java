package net.junsun.l19_custom_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TouchDrawView view = (TouchDrawView) findViewById(R.id.myview);
        if (view == null) {
            Log.e("jsun", "we have a problem");
        }
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.clear();
            }
        });
    }

}
