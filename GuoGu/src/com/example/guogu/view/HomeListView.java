package com.example.guogu.view;

import android.content.Context;
import android.util.AttributeSet;

public class HomeListView extends BaseListView {

	public HomeListView(Context context) {
		super(context);
	}
	
	public HomeListView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}
	
	public HomeListView(Context context, AttributeSet attributeSet, int defStyle) {
		super(context, attributeSet, defStyle);
	}

	@Override
	public void setViewPagerVisibility(boolean visible) {
		// TODO Auto-generated method stub
		super.setViewPagerVisibility(true);
	}
}
