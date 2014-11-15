package com.example.dailybp;

import java.util.Calendar;
import my.life.dailybp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputActivity extends ActionBarActivity {
	Button add,save,clear;
	EditText txtheartbeat, txtdiast, txtsyst;
	TextView txtVwDate;
	DataController mydb;
	add_states btn_add;
	TableBP mydata;
	
	private enum add_states{
		ADD_ENABLED,
		ADD_DISABLED
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout_input);
		mydb = DataController.getDBController(this.getApplicationContext());
		get_UI();
		init_UI();
		add_UI_value_listener();
	}
	
	private void add_UI_value_listener()
	{
//		txtsyst.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//	        @Override
//	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//	            if (actionId == EditorInfo.IME_ACTION_DONE) {
//	            	if(getValue(txtsyst)<getValue(txtdiast))
//	        		{
//	        	      txtsyst.setError("This is the Upper Value");
//	        		}
//	        		if(getValue(txtsyst)>180)
//	        		{
//	        	      txtsyst.setError("Please seek medical attention now");
//	        		}
//
//	                return true;
//	            }
//	            return false;
//	        }
//	    });
//		txtdiast.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//	        @Override
//	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//	            if (actionId == EditorInfo.IME_ACTION_DONE) {
//	            	if(getValue(txtsyst)<getValue(txtdiast))
//	        		{
//	        	      txtsyst.setError("This is the Lower Value");
//	        		}
//	        		if(getValue(txtsyst)>110)
//	        		{
//	        	      txtsyst.setError("Please seek medical attention now");
//	        		}
//	                return true;
//	            }
//	            return false;
//	        }
//	    });		
		txtsyst.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            	
//        		if(getValue(txtsyst)<80)
//        		{
//        	      txtsyst.setError("This does not look right");
//        	      //this shoud not set error and should allow save
//        		}
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            	if(txtsyst.getText().toString().length() != 0)
            	{
            	if(txtdiast.getText().toString().length() != 0)
        		{
	             	if(getValue(txtsyst)<getValue(txtdiast))
	        		{
	        	      txtsyst.setError("the Upper Value here");
	        		}
        		}
	        		if(getValue(txtsyst)>180)
	        		{
	        	      txtsyst.setError("This does not look right");
	        	      //this shoud not allow save
	        		}
	 
            }
            }
        });
		txtdiast.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
            	boolean error=false, error1=false,error2=false;
                // TODO Auto-generated method stub
            	if(txtdiast.getText().toString().length() != 0)
            	{
            	if(txtsyst.getText().toString().length() != 0)
        		{
            	    if(getValue(txtsyst)<getValue(txtdiast))
	        		{
            	    	error1=true;
	        	      txtdiast.setError("the Lower Value here");
	        		}
        		}
	        		if(getValue(txtdiast)>110)
	        		{
	        			error2 =true;
	        	      txtdiast.setError("This does not look right");
	        		}
	        		error = (error1 | error2);
            	}
            }
        });
	}
	
	private void get_UI(){
		add = (Button) findViewById(R.id.button1);
		//save = (Button) findViewById(R.id.button2);
		clear = (Button) findViewById(R.id.button3);
		txtdiast = (EditText) findViewById(R.id.editText2);
		txtsyst = (EditText) findViewById(R.id.edit_about);
		txtheartbeat = (EditText) findViewById(R.id.editText3);
		txtVwDate = (TextView)findViewById(R.id.textView1);;
	}
	
   private void init_UI(){
		txtsyst.setText("");
		txtdiast.setText("");
		txtheartbeat.setText("");
		Calendar c = Calendar.getInstance(); 
		String date = Integer.toString(c.get(Calendar.YEAR))+
		         "/"+Integer.toString(c.get(Calendar.MONTH))+
		         "/"+Integer.toString(c.get(Calendar.DAY_OF_MONTH))+
		         " "+Integer.toString(c.get(Calendar.HOUR))+
		         ":"+Integer.toString(c.get(Calendar.MINUTE));
		txtVwDate.setText(date);
		txtdiast.setTag(txtdiast.getKeyListener()); 
		txtsyst.setTag(txtsyst.getKeyListener()); 
		txtheartbeat.setTag(txtheartbeat.getKeyListener()); 
		btn_add=add_states.ADD_ENABLED;
		//save.setEnabled(false);
//		save.setBackgroundResource(R.drawable.ic_action_btn_save_disabled);
//		add.setBackgroundResource(R.drawable.buttonhighlight_input_save);
	}

	public void onAdd(View arg0) {

		mydata = new TableBP();
		
		Calendar c = Calendar.getInstance(); 
		String date = Integer.toString(c.get(Calendar.YEAR))+
		         "/"+Integer.toString(c.get(Calendar.MONTH))+
		         "/"+Integer.toString(c.get(Calendar.DAY_OF_MONTH))+
		         "time"+Integer.toString(c.get(Calendar.HOUR))+
		         ":"+Integer.toString(c.get(Calendar.MINUTE));

		if (isValueTyped(txtsyst) && isValueTyped(txtdiast)
				&& isValueTyped(txtheartbeat)) 
		{
			mydata.systolic = getValue(txtsyst);
			mydata.diastolic = getValue(txtdiast);
			mydata.heart_rate = getValue(txtheartbeat);
			mydata.time = date;//now.toString();
		
			txtdiast.setKeyListener(null);
			txtsyst.setKeyListener(null);
			txtheartbeat.setKeyListener(null);
			txtdiast.setEnabled(false);
			txtsyst.setEnabled(false);
			txtheartbeat.setEnabled(false);
			//save.setEnabled(true);
//			save.setBackgroundResource(R.drawable.buttonhighlight_input_save);
//			add.setBackgroundResource(R.drawable.ic_action_btn_save_disabled);
			add.setEnabled(false);
			btn_add = add_states.ADD_DISABLED;
			onSave(arg0);
		}
	}

	public void onClear(View arg0) {

		txtsyst.setText("");
		txtdiast.setText("");
		txtheartbeat.setText("");
		if (btn_add == add_states.ADD_DISABLED) {
			txtsyst.setKeyListener((KeyListener) txtsyst.getTag());
			txtdiast.setKeyListener((KeyListener) txtdiast.getTag());
			txtheartbeat.setKeyListener((KeyListener) txtheartbeat.getTag());
			txtdiast.setEnabled(true);
			txtsyst.setEnabled(true);
			txtheartbeat.setEnabled(true);
			add.setEnabled(true);
			//save.setEnabled(false);
//			save.setBackgroundResource(R.drawable.ic_action_btn_save_disabled);
//			add.setBackgroundResource(R.drawable.buttonhighlight_input_save);
			btn_add = add_states.ADD_ENABLED;
		}

	}	
	
	public void onSave(View arg0) {
		Toast.makeText(this, 
				"Data Saved", Toast.LENGTH_LONG).show();	 	        
		mydb.insertData(mydata);
		
		//TODO should we allow multiple additions?
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	private int getValue(EditText edtxt) {
		return Integer.valueOf(edtxt.getText().toString());
	}
	
	private boolean isValueTyped(EditText edtxt) 
	{
		if(edtxt.getText().toString().length() == 0)
		{
	      edtxt.setError( "Value is required!" );
		  return false;	
		}
		return true;
	}

	private boolean isValueValid(EditText edtxt, int max, String error) 
	{
		if(getValue(edtxt)>max)
		{
	      edtxt.setError(error );
		  return false;	
		}
		return true;
	}
	
	
	public void onAboutOK(View arg0) {
		setContentView(R.layout.activity_input); 
		get_UI();
		init_UI();
	}
	
	public void onDismiss() {
		Intent intent = new Intent(this, MainActivity.class);
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
