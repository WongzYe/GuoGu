package com.example.guogu.adapter;

import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.guogu.R;
import com.example.guogu.model.Goods;

public class GoodsGridViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<Goods> mGoodss;
	private Goods mGoods;

	public GoodsGridViewAdapter(Context context, List<Goods> goodss) {
		mContext = context;
		mGoodss = goodss;
	}

	@Override
	public int getCount() {
		return mGoodss.size();
	}

	@Override
	public Object getItem(int position) {
		return mGoodss.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder mHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.gird_view_goods, null);
			mHolder = new ViewHolder();
			mHolder.pic = (ImageView) convertView.findViewById(R.id.goods_img);
			mHolder.favorite = (ImageView) convertView
					.findViewById(R.id.favorite);
			mHolder.favoriteNums = (TextView) convertView
					.findViewById(R.id.favorite_num);
			mHolder.price = (TextView) convertView
					.findViewById(R.id.goods_price);
			mHolder.pic.setScaleType(ScaleType.CENTER_INSIDE);
			LayoutParams params = (LayoutParams) mHolder.pic.getLayoutParams();
			int fixHeight = mContext.getResources().getDisplayMetrics().widthPixels / 2;
			params.height = fixHeight;
			mHolder.pic.setLayoutParams(params);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		mGoods = mGoodss.get(position);

		mHolder.pic.setImageResource(mGoods.getDrawable());

		mHolder.refreshView(mGoods.mFavorite);
		mHolder.favorite.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mGoods.tapFavorite();
				mHolder.refreshView(mGoods.mFavorite);
			}
		});

		mHolder.favoriteNums.setText(String.valueOf(mGoods.mFavoriteNums));
		mHolder.price.setText(String.valueOf(mGoods.getPrice()));

		return convertView;
	}

	private class ViewHolder {
		private ImageView pic, favorite;
		private TextView favoriteNums, price;

		private void refreshView(boolean isFavor) {
			if (isFavor) {
				favorite.setImageResource(R.drawable.icon_like_press);
			} else {
				favorite.setImageResource(R.drawable.icon_unlike);
			}
			favoriteNums.setText(String.valueOf(mGoods.mFavoriteNums));
		}
	}

}
