package com.example.dailybp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import my.life.dailybp.R;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
		
	public void onViewData(View arg0) {
		//Intent intent = new Intent(this, RotatedViewActivity.class);
		Intent intent = new Intent(this, ViewDataActivity.class);
		startActivity(intent);
	}
	
	public void onAddData(View arg0) {
		Intent intent = new Intent(this, InputActivity.class);
		startActivity(intent);  
	}
	
	public void onEditData(View arg0) {
		Intent intent = new Intent(this, EditDataActivity.class);
		startActivity(intent);  
	}
	
	public void onAboutOK(View arg0) {
		setContentView(R.layout.activity_main);  
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//TODO MC I am using the same edit_data.xml for menu for now
		//as same options I believe for all
//		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_about) {
//			setContentView(R.layout.activity_about);
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
