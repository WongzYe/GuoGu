package com.example.guogu.adapter;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guogu.R;
import com.example.guogu.model.Goods;

public class ConcentrateGoodsAdapter extends BaseAdapter {

	private Context mContext;
	private List<Goods> mGoodsList;

	public ConcentrateGoodsAdapter(Context context, List<Goods> goodsList) {
		mContext = context;
		mGoodsList = goodsList;
	}

	@Override
	public int getCount() {
		return mGoodsList.size();
	}

	@Override
	public Object getItem(int position) {
		return mGoodsList.get(position);
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
					R.layout.list_view_concentrate_goods, null);
			holder = new ViewHolder();
			holder.goodsImg = (ImageView) convertView
					.findViewById(R.id.goods_img);
			holder.favoriteImg = (ImageView) convertView
					.findViewById(R.id.favorite);
			// holder.posterAvatar = (ImageView)
			// convertView.findViewById(R.id.poster_avatar);
			holder.favoriteNum = (TextView) convertView
					.findViewById(R.id.favorite_num);
			holder.passedTime = (TextView) convertView
					.findViewById(R.id.passed_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Goods goods = mGoodsList.get(position + 1);
		holder.goodsImg.setImageResource(goods.getDrawable());
		if (goods.mFavorite) {
			holder.favoriteImg.setImageResource(R.drawable.icon_like_press);
		}
		holder.favoriteNum.setText(String.valueOf(goods.mFavoriteNums));
		holder.passedTime.setText(computePassedTime(goods.getPostDate()));
		return convertView;
	}

	private String computePassedTime(Date postTime) {
		long passedTime = System.currentTimeMillis() - postTime.getTime();
		long passedDay = passedTime / (1000 * 60 * 60 * 24);
		long passedHour = passedTime / (1000 * 60 * 60);
		long passedMinute = passedTime / (1000 * 60);

		if (passedDay > 0) {
			return String.valueOf(passedDay) + "天前";
		} else if (passedHour > 0) {
			return String.valueOf(passedHour) + "小时前";
		} else if (passedMinute > 0) {
			return String.valueOf(passedMinute) + "分钟前";
		}
		return "1分钟前";
	}

	private class ViewHolder {
		private ImageView goodsImg, favoriteImg, posterAvatar;
		private TextView favoriteNum, passedTime;
	}

}
