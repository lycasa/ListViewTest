package com.test.listview;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class listviewadapter extends BaseAdapter {
	private Context ncontext;
	private List<String> data;
	private LayoutInflater mlayoutinflate;
	private ViewHolder mviewholder;
	private int currentitem;
	public int getCurrentitem() {
		return currentitem;
	}

	public void setCurrentitem(int currentitem) {
		this.currentitem = currentitem;
	}

	public listviewadapter(Context mcontex, List<String> data) {
		this.ncontext = mcontex;
		this.data = data;
		this.mlayoutinflate = LayoutInflater.from(mcontex);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		mviewholder = new ViewHolder();
		String str = (String) getItem(position);
		if(convertView==null){
			convertView = mlayoutinflate.inflate(R.layout.listviewitem,null);
			
			mviewholder.mtextview = (TextView) convertView.findViewById(R.id.tv_item);
			//mviewholder.mtextview.setFocusableInTouchMode(true);
			//mviewholder.mtextview.requestFocus();
			convertView.setTag(mviewholder);
		}else {
			mviewholder = (ViewHolder) convertView.getTag();
		}
		mviewholder.mtextview.setText(str);
		if(position==currentitem){
			//mviewholder.mtextview.setBackgroundDrawable(ncontext.getResources().getDrawable(R.drawable.img_mm_05_01_01_09d));
			convertView.setBackgroundDrawable(ncontext.getResources().getDrawable(R.drawable.img_mm_05_01_01_09d));
			//convertView.setFocusableInTouchMode(true);
			convertView.requestFocus();
			convertView.setPressed(true);
		}else{
			//mviewholder.mtextview.setBackgroundDrawable(ncontext.getResources().getDrawable(R.drawable.img_mm_05_01_01_09));
			convertView.setBackgroundDrawable(ncontext.getResources().getDrawable(R.drawable.img_mm_05_01_01_09));
			//convertView.setFocusableInTouchMode(true);
			convertView.clearFocus();
		}
		
		return convertView;
	}
	class ViewHolder{
		private TextView mtextview;
	}

}
