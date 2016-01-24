package net.junsun.l09_context_menu_actionmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ContextMenuCallback cb = new ContextMenuCallback(MainActivity.this, textView);
                ActionMode actionMode = startActionMode(cb);
                actionMode.setTitle("Edit text");
                return true;
            }
        });
    }
}
