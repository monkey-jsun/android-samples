package csu.matos.simplelistdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	String[] items = { "Data-0", "Data-1", "Data-2", "Data-3", "Data-4",
			"Data-5", "Data-6", "Data-7" };

	ListView myListView;

   	@Override
   	public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       
       myListView = (ListView) findViewById(R.id.my_list);

       ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
    		   					R.layout.my_text1,
                                items);
       myListView.setAdapter(aa);

       myListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View rowView, int position, long id) {
			String text = " Position: " + position + ", id: " + id + ", data: " + items[position];
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
			
		}
	});
       
   }//onCreate    


//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		super.onListItemClick(l, v, position, id);
//		String text = " Position: " + position + "   " + items[position];
//		txtMsg.setText(text);
//	}

}
