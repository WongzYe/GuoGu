package com.example.guogu.model;

public class MenuItem {
	
	private int mItemName;
	private int mItemDrawable;
	
	public MenuItem(int mItemName, int mItemDrawable) {
		this.mItemName = mItemName;
		this.mItemDrawable = mItemDrawable;
	}

	public int getItemName() {
		return mItemName;
	}

	public void setItemName(int itemName) {
		mItemName = itemName;
	}

	public int getItemDrawable() {
		return mItemDrawable;
	}

	public void setItemDrawable(int itemDrawable) {
		mItemDrawable = itemDrawable;
	}
}
