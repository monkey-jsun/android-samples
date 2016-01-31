package net.junsun.l11_internal_files;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    final String myFile = "user-preference";
    // create a reference to the shared preferences object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readEditMyFile();
            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMyFile();
            }
        });

        Log.i("jsun", "internal file directory is " + getFilesDir());
    }

    private void readEditMyFile() {
        final Dialog customDialog = new Dialog(MainActivity.this);
        customDialog.setTitle("Input your preference");

        // inflate custom layout
        customDialog.setContentView(R.layout.preference_dialog);

        final EditText nameInput = (EditText) customDialog.findViewById(R.id.editText);
        final EditText colorInput = (EditText) customDialog.findViewById(R.id.editText2);

        // open file and set existings values
        try {
            FileInputStream fis = openFileInput(myFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            nameInput.setText(reader.readLine());
            colorInput.setText(reader.readLine());
        } catch (Exception e) {
            nameInput.setText("");
            colorInput.setText("");
        }

        ((Button) customDialog.findViewById(R.id.button3))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // save new values to file
                        try {
                            FileOutputStream fos = openFileOutput(myFile, Context.MODE_PRIVATE);
                            fos.write((nameInput.getText() + "\n").getBytes());
                            fos.write((colorInput.getText() + "\n").getBytes());
                            fos.close();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to save to file!", Toast.LENGTH_SHORT).show();
                        }

                        customDialog.dismiss();
                    }
                });


        customDialog.show();
    }

    private void deleteMyFile() {
        File dir = getFilesDir();
        File file = new File(dir, myFile);
        boolean deleted = file.delete();

        if (deleted)
            Toast.makeText(getApplicationContext(), "File is deleted!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Failed to delete the file", Toast.LENGTH_SHORT).show();
    }
}
