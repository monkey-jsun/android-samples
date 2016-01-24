package csu.matos.scrollviewdemo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    //GUI controls
	TextView txtMsg;
	ViewGroup scrollViewgroup;
	//large image frame for displaying high-quality selected image 
	ImageView imageSelected;
	
	//each frame in the ScrollView has [icon, caption]
	ImageView icon;
	TextView caption;
	
	
	//frame-captions
	String[] items = { "Data-1", "Data-2", "Data-3", "Data-4", "Data-5",
			"Data-3", "Data-7", "Data-8", "Data-9", "Data-10", "Data-11",
			"Data-12", "Data-13", "Data-14", "Data-15" };
	//frame-icons
	Integer[] thumbnails = { R.drawable.pic01_small, R.drawable.pic02_small,
			R.drawable.pic03_small, R.drawable.pic04_small,
			R.drawable.pic05_small, R.drawable.pic06_small,
			R.drawable.pic07_small, R.drawable.pic08_small,
			R.drawable.pic09_small, R.drawable.pic10_small,
			R.drawable.pic11_small, R.drawable.pic12_small,
			R.drawable.pic13_small, R.drawable.pic14_small,
			R.drawable.pic15_small };
	
	//large images to be shown (individually) in an ImageView widge
	Integer[] largeImages = { R.drawable.pic01_large,
			R.drawable.pic02_large, R.drawable.pic03_large,
			R.drawable.pic04_large, R.drawable.pic05_large,
			R.drawable.pic06_large, R.drawable.pic07_large,
			R.drawable.pic08_large, R.drawable.pic09_large, 
			R.drawable.pic10_large, R.drawable.pic11_large, 
			R.drawable.pic12_large, R.drawable.pic13_large, 
			R.drawable.pic14_large, R.drawable.pic15_large };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//bind GUI controls to Java classes
		txtMsg = (TextView) findViewById(R.id.txtMsg);
		imageSelected = (ImageView) findViewById(R.id.imageSelected);

		// this layout goes inside the HorizontalScrollView
		scrollViewgroup = (ViewGroup) findViewById(R.id.viewgroup);
		
		for (int i = 0; i < items.length; i++) {
			//create single frames [icon & caption] using XML inflater
			final View singleFrame = getLayoutInflater()
					       .inflate( R.layout.frame_icon_caption, null );

			// id is double-purposed for indexing the picture files
			singleFrame.setId(i);
			
			TextView caption = (TextView) singleFrame.findViewById(R.id.caption);
			ImageView icon = (ImageView) singleFrame.findViewById(R.id.icon);
			//put data [icon, caption] in each single frame
			icon.setImageResource( thumbnails[i] );
			caption.setText( items[i] );
			//add frame to the scrollView
			scrollViewgroup.addView( singleFrame );		
			
			//each single frame gets its own click listener
			singleFrame.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String text = "Selected position: " + singleFrame.getId();
					txtMsg.setText(text);
					imageSelected.setImageResource(largeImages[singleFrame.getId()]);
				}
			});// listener

			
		}// for

	}//onCreate
}
