package com.example.dailybp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import my.life.dailybp.R;

public class EditDataActivity extends ActionBarActivity {

	Button delRecent, delAll;
	DataController mydb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_data);
		mydb = DataController.getDBController(this.getApplicationContext());
	}

	public void onDeleteLast(View arg0) {

		if (mydb.isEmpty()) {
			Toast.makeText(this, "No data to remove", Toast.LENGTH_LONG).show();
		} else {
			new AlertDialog.Builder(this)
					.setTitle("Confirm Deletion")
					.setMessage("Are you sure you want to delete this entry?")
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// continue with delete
									mydb.deleteRow(mydb.getLastId());
								}
							})
					.setNegativeButton(android.R.string.no,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// do nothing
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();

		}
		// mydb.deleteRow(mydb.getLastId());
	}

	public void onDeleteAll(View arg0) {

		if (mydb.isEmpty()) {
			Toast.makeText(this, "No data to remove", Toast.LENGTH_LONG).show();
		} else {
			new AlertDialog.Builder(this)
					.setTitle("Confirm Delete")
					.setMessage("Are you sure you want to delete all Data?")
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// continue with delete
									mydb.clearTable();
								}
							})
					.setNegativeButton(android.R.string.no,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// do nothing
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();
		}
		// mydb.clearTable();
	}

	public void onAboutOK(View arg0) {
		setContentView(R.layout.activity_edit_data);
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
