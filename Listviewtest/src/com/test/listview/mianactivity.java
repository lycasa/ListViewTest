package com.test.listview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;


public class mianactivity extends Activity implements OnItemSelectedListener,OnItemClickListener 
					,OnKeyListener,OnClickListener,OnTouchListener{
	
	private NewListView  mlistview;
	
	private listviewadapter mlistviewadapter;
	private Context mcontext;
	private List<String> data;
	private Button btn_up,btn_down;
	private int currentitem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("ListViewTest", "==onCreate_begin==");
		setContentView(R.layout.mainview);
		mcontext = this;
		mlistview = (NewListView) findViewById(R.id.lv_test);
		initview();
		data = new ArrayList<String>();
		for(int i=0;i<30;i++){
			data.add("testdata"+i);
		}
		//MotionEvent
		
		mlistviewadapter = new listviewadapter(mcontext, data);
		
		mlistview.setAdapter(mlistviewadapter);
		mlistview.setOnItemSelectedListener(this);
		mlistview.setOnItemClickListener(this);
		mlistview.setOnKeyListener(this);
		
		
		
		//mlistview.setFocusableInTouchMode(true);
		/*mlistviewadapter.getView(0, null, null).setSelected(true);
		mlistviewadapter.getView(0, null, null).setFocusable(true);
		mlistviewadapter.getView(0, null, null).setPressed(true);
		mlistviewadapter.notifyDataSetChanged();*/
		Log.d("ListViewTest", "==onCreate_end==");
	}
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("ListViewTest", "==onResume==");
		//mlistview.setSelection(currentitem);
		actionkey();
	}
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("ListViewTest", "==onPause==");
	}
	public void initview(){
		btn_up = (Button) findViewById(R.id.btn_up);
		btn_up.setOnClickListener(this);
		btn_up.setOnTouchListener(this);
		btn_down = (Button) findViewById(R.id.btn_down);
		btn_down.setOnClickListener(this);
		btn_down.setOnTouchListener(this);
	}
	
	//父类AbsListView的父类AdapterView中OnItemSelectedListener接口中定义的方法
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Log.d("ListViewTest", "==onItemSelected_position=="+position);
		currentitem = position;
		mlistviewadapter.setCurrentitem(position);
		//view.requestFocus();
		mlistviewadapter.notifyDataSetChanged();
		//mlistviewadapter.notifyDataSetInvalidated();
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Log.d("ListViewTest", "==onAttachedToWindow==");
		mlistview.setSelection(currentitem);
		//actionkey();
		//mlistview.getChildAt(currentitem).requestFocus();
		
	}
	//父类AbsListView的父类AdapterView中OnItemClickListener接口中定义的方法
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Log.d("ListViewTest", "==onItemClick_clickposition=="+position);
			Log.d("ListViewTest", "==onItemClick_view.isSelected()=="+view.isSelected());
			Log.d("ListViewTest", "==onItemClick_view.isFocused()=="+view.isFocused());
			Log.d("ListViewTest", "==onItemClick_view.isFocusableInTouchMode()=="+view.isFocusableInTouchMode());
			currentitem = position;
			//view.setSelected(true);
			//view.setFocusable(true);
			//view.requestFocusFromTouch();
			//view.setFocusableInTouchMode(true);
			view.setPressed(true);
	
			
			mlistviewadapter.setCurrentitem(position);
			//view.requestFocus();
			mlistviewadapter.notifyDataSetChanged();
			Intent intent = new Intent(this, ItemshowActivity.class);
			startActivity(intent);
			
		}
		
	//Activity.callback接口中定义的方法，activity实现了该接口
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		Log.d("ListViewTest", "==dispatchKeyEvent_event.getKeyCode()=="+event.getKeyCode());
		if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT){
			if(event.getAction() == KeyEvent.ACTION_DOWN) {
				mlistview.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, event);
			}
		}else if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT){
			if(event.getAction() == KeyEvent.ACTION_DOWN) {
				mlistview.onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, event);
			}
		}
		return super.dispatchKeyEvent(event);
	}
	
	//接口KeyEvent.callback接口中的方法
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		Log.d("ListViewTest", "==onKeyDown_keyCode=="+keyCode);
		
		
		
		
		
		return super.onKeyDown(keyCode, event);
	}
	
	//父类View中OnKeyListener接口中的抽象方法
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		//该方法不会执行，原因还不清楚
		Log.d("ListViewTest", "==onKey_keyCode=="+keyCode);
		return false;
	}
	//父类View中OnClickListener接口中的抽象方法
	@Override
	public void onClick(View v) {
		int selecteditemposition = mlistview.getSelectedItemPosition();
		Log.d("ListViewTest", "==onClick_selecteditemposition=="+selecteditemposition);
		switch (v.getId()) {
		case R.id.btn_up:
			if(selecteditemposition>1){
				mlistview.setSelection(selecteditemposition-1);
			}
			break;
		case R.id.btn_down:
			if(selecteditemposition>0){
				mlistview.setSelection(selecteditemposition+1);
			}
		
			break;

		default:
			break;
		}
		
	}

	//该方法是在父类Activity中定义的
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("ListViewTest", "==onTouchEvent_event.getaction=="+event.getAction());
		return super.onTouchEvent(event);
	}
	//父类view中OnKeyListener接口中的抽象方法
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.d("ListViewTest", "==onTouch_event.getaction=="+event.getAction());
		return false;
	}
	public void actionkey(){
		try{
			   Runtime runtime=Runtime.getRuntime();
			   runtime.exec("input keyevent " + 22);
			  }catch(IOException e){
			    Log.e("Exception when doBack", e.toString());
			  }
	}
	
}
