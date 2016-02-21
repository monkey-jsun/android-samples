package net.junsun.l16_fragment_dialog;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements MyDialogFragment.MyDialogListener {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment dialog=new MyDialogFragment();

                // set arguments
                Bundle bundle = new Bundle();
                bundle.putString("title", "daily checking ...");
                bundle.putString("message", "How are you doing today?");
                bundle.putString("yesButton", "feeling good");
                bundle.putString("noButton", "feeling blue");
                dialog.setArguments(bundle);

                dialog.show(getFragmentManager(),"daily checking dialog tag");
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        textView.setText("I'm feeling good");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        textView.setText("I'm feeling blue");
    }
}
