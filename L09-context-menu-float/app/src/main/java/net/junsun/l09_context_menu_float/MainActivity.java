package net.junsun.l09_context_menu_float;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView) findViewById(R.id.textView);
        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, view, info);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_decrease:
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getTextSize()-10);
                textView.setBackgroundColor(Color.RED);
                break;
            case R.id.action_increase:
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getTextSize() + 10);
                textView.setBackgroundColor(Color.GREEN);
                break;
            case R.id.action_change_color:
                textView.setBackgroundColor(Color.GRAY);
                break;
        }
        return true;
    }
}
