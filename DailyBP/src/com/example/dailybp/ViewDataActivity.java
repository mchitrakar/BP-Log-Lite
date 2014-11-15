package com.example.dailybp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import my.life.dailybp.R;

public class ViewDataActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_data);
	}

	public void onViewList(View arg0) {
		DataController mydb;
		mydb = DataController.getDBController(this.getApplicationContext());

		if (mydb.isEmpty()) {
			Toast.makeText(this,"Empty Data", Toast.LENGTH_LONG).show();	 
		} else {
			Intent intent = new Intent(this, ListActivity.class);
			startActivity(intent);
		}
	}

	public void onViewChart(View arg0) {
		DataController mydb;
		mydb = DataController.getDBController(this.getApplicationContext());

		if (mydb.isEmpty()) {
			Toast.makeText(this, "Empty Data", Toast.LENGTH_LONG).show();
			return;
		}
		if (mydb.howMany()< 2){ 
			Toast.makeText(this, "Not enough data", Toast.LENGTH_LONG).show();
			return;
		}
			Intent intent = new Intent(this, RotatedViewActivity.class);
			startActivity(intent);
	
	}

	public void onAboutOK(View arg0) {
		setContentView(R.layout.activity_view_data);  
	}
	
	public void onDismiss() {
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.cancel, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_cancel) {
//			this.onDismiss();
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}
}
