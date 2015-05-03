package com.example.guogu.adapter;

import java.util.List;

import com.example.guogu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsPopupWindowsAdapter extends BaseAdapter {

	private Context mContext;
	private String[] mCategoryList;

	public GoodsPopupWindowsAdapter(Context context, String[] categoryList) {
		mContext = context;
		mCategoryList = categoryList;
	}

	@Override
	public int getCount() {
		return mCategoryList.length;
	}

	@Override
	public Object getItem(int position) {
		return mCategoryList[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_view_goods_popup_window, null);
			holder = new ViewHolder();
			holder.categoryName = (TextView) convertView.findViewById(R.id.category_name);
			holder.touchTag = (ImageView) convertView.findViewById(R.id.touch_tag);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String categoryName = mCategoryList[position];
		holder.categoryName.setText(categoryName);
		return convertView;
	}

	private class ViewHolder {
		private TextView categoryName;
		private ImageView touchTag;
	}

}
