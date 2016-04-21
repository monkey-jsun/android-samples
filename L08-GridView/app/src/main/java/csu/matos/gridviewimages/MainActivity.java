package csu.matos.gridviewimages;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {
	//GUI control  bound to screen1 (holding GidView)
	GridView gridview;
	//GUI controls bound to screen2 (holding single ImageView)
	TextView txtSoloMsg;
	ImageView imgSoloPhoto;
	Button btnBack;
		
	// initialize array of smallImages (100x75 thumbnails)
	Integer[] smallImages = { R.drawable.pic01_small,
			R.drawable.pic02_small, R.drawable.pic03_small,
			R.drawable.pic04_small, R.drawable.pic05_small,
			R.drawable.pic06_small, R.drawable.pic07_small,
			R.drawable.pic08_small, R.drawable.pic09_small, 
			R.drawable.pic10_small, R.drawable.pic11_small, 
			R.drawable.pic12_small, R.drawable.pic13_small, 
			R.drawable.pic14_small, R.drawable.pic15_small };
	
	//initialize array of high-resolution images (1024x768)
	Integer[] largeImages = { R.drawable.pic01_large,
			R.drawable.pic02_large, R.drawable.pic03_large,
			R.drawable.pic04_large, R.drawable.pic05_large,
			R.drawable.pic06_large, R.drawable.pic07_large,
			R.drawable.pic08_large, R.drawable.pic09_large, 
			R.drawable.pic10_large, R.drawable.pic11_large, 
			R.drawable.pic12_large, R.drawable.pic13_large, 
			R.drawable.pic14_large, R.drawable.pic15_large };
	
	//in case you want to use-save state values
	Bundle myOriginalMemoryBundle; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myOriginalMemoryBundle = savedInstanceState;
		// setup GridView with its custom adapter and listener
		gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new MyImageAdapter(this, smallImages));
		gridview.setOnItemClickListener(this);
		
	}


    // on response to tapping, display a high-resolution 
	// version of the chosen thumbnail
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		showBigScreen(position);
	}//onItemClick

	private void showBigScreen(int position) {
		// show the selected picture as a single frame
		setContentView(R.layout.solo_picture);
		txtSoloMsg = (TextView) findViewById(R.id.txtSoloMsg);
		imgSoloPhoto = (ImageView) findViewById(R.id.imgSoloPhoto);
		txtSoloMsg.setText("image " + position );

		imgSoloPhoto.setImageResource( largeImages[position] );

		btnBack = (Button) findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// redraw the main screen showing the GridView
				onCreate(myOriginalMemoryBundle);
				//recreate();
			}
		});

	}// showBigScreen

}
