package net.junsun.l20_text_to_speech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    Locale currentLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLocale = Locale.US;
                tts = new TextToSpeech(MainActivity.this, MainActivity.this);
            }
        });

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLocale=Locale.UK;
                tts = new TextToSpeech(MainActivity.this, MainActivity.this);
            }
        });
    }


    @Override
    public void onInit(int status) {
        if (currentLocale == Locale.US) {
            tts.setLanguage(Locale.US);
            // using API 21+ API.  Set minimumSDK to 21
            tts.speak("Wake up! Wake up! Your house is on fire!", TextToSpeech.QUEUE_ADD, null, null);
        } else if (currentLocale == Locale.UK) {
            tts.setLanguage(Locale.UK);
            // using API 21+ API.  Set minimumSDK to 21
            tts.speak("Wake up! Wake up! Your house is on fire!", TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
}
