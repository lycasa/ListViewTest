package com.test.listview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ListView;

public class NewListView extends ListView {

	public NewListView(Context context) {
		super(context);
		//setFocusableInTouchMode(true);
		//requestFocusFromTouch();
	}

	public NewListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//setFocusableInTouchMode(true);
		//requestFocusFromTouch();
	}

	public NewListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//setFocusableInTouchMode(true);
		//requestFocusFromTouch();
		
	}

//onFocusChanged是View里定义的方法
@Override
protected void onFocusChanged(boolean gainFocus, int direction,
		Rect previouslyFocusedRect) {
	super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	Log.d("ListViewTest", "==onFocusChanged_gainFocus=="+gainFocus);
	Log.d("ListViewTest", "==onFocusChanged_direction=="+direction);
	Log.d("ListViewTest", "==onFocusChanged_isInTouchMode()=="+isInTouchMode());
	Log.d("ListViewTest", "==onFocusChanged_performClick()=="+performClick());
	
	if(gainFocus){
		if(isInTouchMode()){
			performClick();
		}
	}
	Log.d("ListViewTest", "==onFocusChanged_performClick()=="+performClick());
}
//view里的方法
@Override
protected void onScrollChanged(int l, int t, int oldl, int oldt) {
	//Log.d("ListViewTest", "==l:=="+l);
	//Log.d("ListViewTest", "==t:=="+t);
	//Log.d("ListViewTest", "==oldl:=="+oldl);
	//Log.d("ListViewTest", "==oldl:=="+oldt);
	super.onScrollChanged(l, t, oldl, oldt);
}

}
