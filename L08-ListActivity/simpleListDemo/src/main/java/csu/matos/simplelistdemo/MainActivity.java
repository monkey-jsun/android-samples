package csu.matos.simplelistdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	TextView txtMsg;

	String[] items = { "Data-0", "Data-1", "Data-2", "Data-3", "Data-4",
			"Data-5", "Data-6", "Data-7" };

	// next time try an empty list such as:
	// String[] items = {};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setListAdapter(new ArrayAdapter<String>(
							this,
							android.R.layout.simple_list_item_1, 
							android.R.id.text1, 
							items));
		// getListView().setBackgroundColor(Color.GRAY); //try this idea later

		txtMsg = (TextView) findViewById(R.id.txtMsg);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String text = " Position: " + position + "   " + items[position];
		txtMsg.setText(text);
	}

}