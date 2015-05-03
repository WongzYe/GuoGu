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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.guogu.R;
import com.example.guogu.activity.ConcentrateGoodsActivity;
import com.example.guogu.activity.PopularActivity;
import com.example.guogu.model.Goods;

public class HomeFragmentAdapter extends BaseAdapter implements OnClickListener {

	private Context mContext;
	private List<Goods> mMenuItems;

	public HomeFragmentAdapter(Context context, List<Goods> menuItems) {
		mContext = context;
		mMenuItems = menuItems;
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_view_home_list, null);
			holder = new ViewHolder();
			holder.pic = (ImageView) convertView.findViewById(R.id.goods_img);
			holder.title = (TextView) convertView.findViewById(R.id.goods_name);
			holder.price = (TextView) convertView
					.findViewById(R.id.goods_price);
			holder.favorite = (ImageView) convertView
					.findViewById(R.id.favorite);
			holder.concentrateLayout = (LinearLayout) convertView
					.findViewById(R.id.concentrate_layout);
			holder.popularLayout = (LinearLayout) convertView
					.findViewById(R.id.popular_layout);
			holder.mainLayout_1 = (RelativeLayout) convertView
					.findViewById(R.id.home_item_1);
			holder.mainLayout_2 = (RelativeLayout) convertView
					.findViewById(R.id.home_item_2);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (position == 0) {
			holder.mainLayout_1.setVisibility(View.VISIBLE);
			holder.mainLayout_2.setVisibility(View.GONE);
			holder.concentrateLayout.setOnClickListener(this);
			holder.popularLayout.setOnClickListener(this);
		} else {
			holder.mainLayout_1.setVisibility(View.GONE);
			holder.mainLayout_2.setVisibility(View.VISIBLE);
			Goods goods = mMenuItems.get(position);
			holder.pic.setImageResource(goods.getDrawable());
			holder.title.setText(goods.getName());
			holder.price.setText(String.valueOf(goods.getPrice()));
			holder.favorite.setOnClickListener(this);
			if (goods.mFavorite) {
				holder.favorite.setImageResource(R.drawable.icon_like_press);
			} else {
				holder.favorite.setImageResource(R.drawable.icon_like);
			}
		}

		return convertView;
	}

	private class ViewHolder {
		private ImageView pic;
		private TextView title;
		private TextView price;
		private ImageView favorite;
		private LinearLayout concentrateLayout, popularLayout;
		private RelativeLayout mainLayout_1, mainLayout_2;
	}

	@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case R.id.popular_layout:
			i = new Intent(mContext, PopularActivity.class);
			mContext.startActivity(i);
			break;
		case R.id.concentrate_layout:
			i = new Intent(mContext, ConcentrateGoodsActivity.class);
			mContext.startActivity(i);
			break;
		}
	}

}
