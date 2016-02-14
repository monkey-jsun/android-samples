package net.junsun.l11_shared_preferences;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String myPreference = "user-preference";
    // create a reference to the shared preferences object
    SharedPreferences mySharedPreferences;
    // obtain an editor to add data to my SharedPreferences object
    SharedPreferences.Editor myEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readEditPreference();
            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePreference();
            }
        });

        // mySharedPreferences = getSharedPreferences(myPreference, Activity.MODE_PRIVATE);
        mySharedPreferences = getPreferences(Activity.MODE_PRIVATE);

    }

    private void readEditPreference() {
        final Dialog customDialog = new Dialog(MainActivity.this);
        customDialog.setTitle("Input your preference");

        // inflate custom layout
        customDialog.setContentView(R.layout.preference_dialog);

        final EditText nameInput = (EditText) customDialog.findViewById(R.id.editText);
        final EditText colorInput = (EditText) customDialog.findViewById(R.id.editText2);

        nameInput.setText(mySharedPreferences.getString("name", ""));
        colorInput.setText(mySharedPreferences.getString("color", ""));

        ((Button) customDialog.findViewById(R.id.button3))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        myEditor =mySharedPreferences.edit();
                        myEditor.putString("name", nameInput.getText().toString());
                        myEditor.putString("color", colorInput.getText().toString());
                        myEditor.commit();

                        customDialog.dismiss();
                    }
                });


        customDialog.show();
    }

    private void deletePreference() {
        myEditor =mySharedPreferences.edit();
        myEditor.clear();
        myEditor.commit();

        Toast.makeText(getApplicationContext(), "Preference is all cleared", Toast.LENGTH_SHORT).show();
    }
}
