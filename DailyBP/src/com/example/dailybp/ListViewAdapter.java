package com.example.dailybp;

import java.util.ArrayList;

import my.life.dailybp.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<BPDataGroup> {

	public ListViewAdapter(Context context, int layout,
			ArrayList<BPDataGroup> arrayListString) {
		super(context, layout, arrayListString);

		this.arrayListString = arrayListString;
		this.layout = layout;
	}

	static class ViewHolder {
		private TextView date;
		private TextView time;
		private TextView slot;
		private TextView bp_data;
	}

	private ArrayList<BPDataGroup> arrayListString;
	private int layout;

	// @Override
	// public long getItemId(int position) {
	// String item = getItem(position);
	// return mIdMap.get(item);
	// }

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getCount() {
		return arrayListString.size();
	}

	@Override
	public View getView(int position, View contentView, ViewGroup viewGroup) {
		// position always 0-7

		View view = null;
		ViewHolder viewHolder = null;

		if (contentView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(layout, null);

			if (view != null) {
				viewHolder = new ViewHolder();
				viewHolder.date = (TextView) view
						.findViewById(R.id.textView1);
				viewHolder.time = (TextView) view
						.findViewById(R.id.textView2);
				viewHolder.slot = (TextView) view
						.findViewById(R.id.textView3);
				viewHolder.bp_data = (TextView) view
						.findViewById(R.id.textView4);
				view.setTag(viewHolder);
			}
		} else {
			view = contentView;
			viewHolder = (ViewHolder) contentView.getTag();
		}
		if (viewHolder != null) {
			BPDataGroup mydata = arrayListString.get(position);
			if (mydata != null) {
				viewHolder.date.setText(mydata.date);
				viewHolder.time.setText(mydata.time); 
				viewHolder.bp_data.setText(mydata.bp_data);
				String str = "";
				for(int i=0;i<mydata.date.length();i++){
					str+=" ";
				}
				viewHolder.slot.setText(str+"\n"+str+"\n"+str+"\n");
			}
		}

		return view;
	}

}