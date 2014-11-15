package com.example.dailybp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import my.life.dailybp.R;

public class RotatedViewActivity extends ActionBarActivity {

	DataPainter dp;
	LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rotated_view);
	
		dp = new DataPainter();
		layout = (LinearLayout) findViewById(R.id.mylayout);
		layout.addView(dp.getGraphView(this));
	
	}
	
	public void onAboutOK(View arg0) {
		setContentView(R.layout.activity_rotated_view);  
		layout = (LinearLayout) findViewById(R.id.mylayout);	
		layout.addView(dp.getGraphView(this));
	}
	
	public void onDismiss() {
		Intent intent = new Intent(this, ViewDataActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cancel, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_cancel) {
			this.onDismiss();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
