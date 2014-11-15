package com.example.dailybp;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import my.life.dailybp.R;

public class AboutActivity  {

	Button about_okay;
	Activity activity;
	int layout;
	private static AboutActivity instance;

	
	public void setContentView(Activity act, int prev_layout, int new_layout)
	{
		activity = act;
		layout = prev_layout;
		activity.setContentView(new_layout);
		aboutOKListener();	
	}

	public void aboutOKListener() {	
 		about_okay = (Button) activity.findViewById(R.id.btn_about_ok);
 		
		about_okay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				activity.inflateUI_and_Listen();
				activity.setContentView(layout);  
			}
		});		
	}
	
    public static synchronized AboutActivity get()
    {
        if (instance == null)
            instance = new AboutActivity();
        return instance;
    }
	
}
