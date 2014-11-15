package com.example.dailybp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import my.life.dailybp.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends ActionBarActivity {

	DataController mydb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		mydb = DataController.getDBController(this.getApplicationContext());
	
		showList();
	}

	public ArrayList<String> LoadDataToList()
	{
		ArrayList<TableBP> bpdata = new ArrayList<TableBP>();
		bpdata = mydb.readBPList();
		ArrayList<String> list = new ArrayList<String>();
		
		
		for (TableBP d : bpdata){
			String[] date_time = d.time.split("time");
			String data = date_time[0];
			            
             data += "   Systolic: "+d.systolic;
             data += "\n                      Diastolic: "+d.diastolic;
             data += "\n                      Heart Rate: "+d.heart_rate;
             list.add(data);
		}
		return list;

	}
	
	public ArrayList<BPDataGroup> LoadDataToList(int a)
	{
		ArrayList<TableBP> bpdata = new ArrayList<TableBP>();
		bpdata = mydb.readBPList();
		ArrayList<BPDataGroup> list = new ArrayList<BPDataGroup>();
		
		
		for (TableBP d : bpdata){
			String[] date_time = d.time.split("time");
			BPDataGroup mygrp = new BPDataGroup();
			mygrp.date = date_time[0];
			mygrp.time = date_time[1];
			mygrp.bp_data = "Systolic:"+d.systolic+"\nDiastolic: "+d.diastolic + "\nHeart Rate:"+d.heart_rate;
            list.add(mygrp);
		}
		return list;

	}
	
	public void showList()
	{
		final ListView listview = (ListView) findViewById(R.id.listView1);
		ArrayList<BPDataGroup> list = new ArrayList<BPDataGroup>();
		list =LoadDataToList(0);
	    final ListViewAdapter adapter = new ListViewAdapter(this,
	        R.layout.layout_customlist, list);
		
//		ArrayList<String> list = new ArrayList<String>();
//		list =LoadDataToList();
//	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
//	        android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);
	}
	
	public void onDismiss() {
		Intent intent = new Intent(this, ViewDataActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void onAboutOK(View arg0) {
		setContentView(R.layout.activity_list);  
		showList();
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
//	        this.onDismiss();
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}
	
	  private class StableArrayAdapter extends ArrayAdapter<String> {

		    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		    public StableArrayAdapter(Context context, int textViewResourceId,
		        List<String> objects) {
		      super(context, textViewResourceId, objects);
		      for (int i = 0; i < objects.size(); ++i) {
		        mIdMap.put(objects.get(i), i);
		      }
		    }

		    @Override
		    public long getItemId(int position) {
		      String item = getItem(position);
		      return mIdMap.get(item);
		    }

		    @Override
		    public boolean hasStableIds() {
		      return true;
		    }

		  }
	  		    
	
}
