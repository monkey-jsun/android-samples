package net.junsun.activitylaunchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SingleTaskActivity extends Activity implements OnClickListener {

	String id;
	String className;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		id="0x" + Integer.toHexString(hashCode());
		className = getClass().getName().split("\\.")[3];
		className = className.substring(0, className.length()-8);
		TextView textView = (TextView) findViewById(R.id.textView);
		textView.setText(className + ", " + id);

		toast("in onCreate");
	}

	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		switch (viewId) {
		case R.id.standardMode:
			startActivity(new Intent(this, StandardModeActivity.class));
			break;

		case R.id.singleTop:
			startActivity(new Intent(this, SingleTopActivity.class));
			break;

		case R.id.singleInstance:
			startActivity(new Intent(this, SingleInstanceActivity.class));
			break;

		case R.id.singleTask:
			startActivity(new Intent(this, SingleTaskActivity.class));
			break;
		}

	}
	@Override
	protected void onStart() {
		super.onStart();
		toast("in onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		toast("in onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		toast("in onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		toast("in onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		toast("in onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		toast("in onDestroy");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		toast("in onNewIntent" + intent.getAction());
	}

	private void toast(String msg) {
		Toast.makeText(getApplicationContext(), className + "," + id + "," + msg, Toast.LENGTH_SHORT).show();
	}
}
