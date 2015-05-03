package com.example.guogu.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

public class MyPagerAdapter extends PagerAdapter {

	private List<ImageView> mImageList;

	public MyPagerAdapter(List<ImageView> imageList) {
		super();
		mImageList = imageList;
	}

	@Override
	public int getCount() {
		return mImageList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(View container, int position) {
		ImageView img = mImageList.get(position % mImageList.size());
		((ViewPager) container).removeView(img);
		if (img.getParent() == null) {
			((ViewPager) container).addView(img);
		}
		return img;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// ImageView img = mImageList.get(position % mImageList.size());
		// if (img.getParent() != null) {
		// ((ViewPager) container).removeView(img);
		// }
	}
}
