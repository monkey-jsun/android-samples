package net.junsun.l11_firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    final String FIREBASE_URL="https://dazzling-heat-5235.firebaseio.com/demo1/";
    Firebase firebaseRoot;
    TextView outputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        firebaseRoot = new Firebase(FIREBASE_URL);

        // show URL base
        ((TextView)findViewById(R.id.textView2)).setText(FIREBASE_URL);

        // remember input fields
        final EditText inputPath = (EditText) findViewById(R.id.editText);
        final EditText inputValue = (EditText) findViewById(R.id.editText2);
        outputData = (TextView) findViewById(R.id.textView5);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputData.setText("");
                String child = inputPath.getText().toString();
                String value = inputValue.getText().toString();
                firebaseRoot.child(child).setValue(value);
                toast("setValue() is done");
            }
        });

        // push
        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputData.setText("");
                String child = inputPath.getText().toString();
                String value = inputValue.getText().toString();
                firebaseRoot.child(child).push().setValue(value);
                toast("push()/setValue() is done");
            }
        });

        // push
        ((Button) findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputData.setText("");
                String child = inputPath.getText().toString();
                firebaseRoot.child(child).setValue(null);
            }
        });

        // monitor value changes
        firebaseRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData("onDataChange", dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                toast("Reading data is cancelled!");
            }
        });

        // monitor children events
        firebaseRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                showData("onChildAdded", dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                showData("onChildChanged", dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                showData("onChildRemoved", dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                showData("onChildMoved", dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                toast("cancelled");
            }
        });
    }

    private void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void showData(String tag, DataSnapshot dataSnapshot) {
        String value;
        if (dataSnapshot.getValue() == null )
            value = "null";
        else
            value = dataSnapshot.getValue().toString();
        outputData.append(tag + " : " + value + "\n");
    }
}
