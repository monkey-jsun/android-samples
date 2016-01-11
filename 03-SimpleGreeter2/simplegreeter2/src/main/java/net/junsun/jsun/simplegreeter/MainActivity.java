package net.junsun.jsun.simplegreeter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                EditText et = (EditText)findViewById(R.id.editText);
                RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);

                int selected=rg.getCheckedRadioButtonId();
                if (selected == -1)
                    message = "Hello";
                else
                    message = "Good " + ((RadioButton)findViewById(selected)).getText().toString();
                message += ", " + et.getText() + "!";

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
