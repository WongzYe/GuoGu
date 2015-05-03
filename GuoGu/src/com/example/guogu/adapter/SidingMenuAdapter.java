package com.example.guogu.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guogu.R;
import com.example.guogu.fragment.FollowingActionFragment;
import com.example.guogu.model.MenuItem;

public class SidingMenuAdapter extends BaseAdapter {

	private List<MenuItem> mMenuItems;
	private LayoutInflater mLayoutInflater;

	public SidingMenuAdapter(Context context, List<MenuItem> menuItems) {
		mMenuItems = menuItems;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mMenuItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mMenuItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		View view;
		if (convertView == null) {
			view = mLayoutInflater
					.inflate(R.layout.list_view_siding_menu, null);
			holder.title = (TextView) view.findViewById(R.id.item_name);
			holder.pic = (ImageView) view.findViewById(R.id.item_imageView);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) convertView.getTag();
		}

		MenuItem mItem = mMenuItems.get(position);
		holder.title.setText(mItem.getItemName());
		holder.pic.setImageResource(mItem.getItemDrawable());

		return view;
	}

	private class ViewHolder {
		public TextView title;
		public ImageView pic;
	}

}
