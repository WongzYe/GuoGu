package com.example.guogu.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guogu.R;

public class FollowingFragmentAdapter extends BaseAdapter {

	private Context mContext;

	public FollowingFragmentAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder holder;
		if (arg1 == null) {
			arg1 = LayoutInflater.from(mContext).inflate(
					R.layout.list_view_following_action, null);
			holder = new ViewHolder();
			holder.avatar = (ImageView) arg1.findViewById(R.id.avatar);
			holder.goods_img = (ImageView) arg1.findViewById(R.id.goods_img);
			holder.user_name = (TextView) arg1.findViewById(R.id.user_name);
			holder.goods_name = (TextView) arg1.findViewById(R.id.goods_name);
			holder.goods_price = (TextView) arg1.findViewById(R.id.goods_price);
			holder.comment = (TextView) arg1.findViewById(R.id.comment);
			holder.poke_nums = (TextView) arg1.findViewById(R.id.poke_nums);
			holder.post_date = (TextView) arg1.findViewById(R.id.post_date);

			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}

		holder.avatar.setImageResource(R.drawable.wongzye);
		holder.goods_img.setImageResource(R.drawable.demo5);
		holder.goods_price.setText("￥ " + "99.00");
		holder.user_name.setText("WongzYe");
		holder.goods_name.setText("示例商品");
		holder.comment.setText("美观！");
		holder.poke_nums.setText(String.valueOf(1));
		holder.post_date.setText(new SimpleDateFormat("yyyy-MM-DD").format(new Date()));
		return arg1;
	}

	private class ViewHolder {
		private ImageView avatar, goods_img, likes;
		private TextView user_name, goods_name, goods_price, comment,
				poke_nums, post_date;
	}
}
