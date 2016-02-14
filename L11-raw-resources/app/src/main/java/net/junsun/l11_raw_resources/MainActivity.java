package net.junsun.l11_raw_resources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMsg = (TextView) findViewById(R.id.textView);
        try {
            PlayWithRawFiles();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Problems: " + e.getMessage(), 1).show();
        }
    }

    public void PlayWithRawFiles() throws IOException {
        String str="";
        StringBuffer buf = new StringBuffer();

        int fileResourceId = R.raw.my_raw_text_file;
        InputStream is = this.getResources().openRawResource(fileResourceId);
        // InputStream is = this.getAssets().open("my_assets_text_file");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is));
        if (is!=null) {
            while ((str = reader.readLine()) != null) {
                buf.append(str + "\n" );
            }
        }
        reader.close();
        is.close();
        txtMsg.setText(buf.toString());
    }// PlayWithRawFiles

}
