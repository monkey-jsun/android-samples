package csu.matos.simplelistdemo;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	TextView txtMsg;

	String[] items = { "Data-0", "Data-1", "Data-2", "Data-3", "Data-4", "Data-5", "Data-6", "Data-7", "Data-8", "Data-9", "Data-10", "Data-11" };
	// String[] items = {};
	// Integer[] items = {123, 345,678};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setListAdapter(new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				items));
		// getListView().setBackgroundColor(Color.GRAY); //try this idea later

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String text = " Position: " + position + ", id: " + id + ", value: " + items[position];
		Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
	}

}
