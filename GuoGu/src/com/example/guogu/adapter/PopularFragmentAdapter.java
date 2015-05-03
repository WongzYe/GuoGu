package com.example.guogu.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.example.guogu.R;
import com.example.guogu.activity.GoodsActivity;
import com.example.guogu.fragment.HomeFragment;
import com.example.guogu.model.Goods;
import com.example.guogu.view.PopularGridView;

public class PopularFragmentAdapter extends BaseAdapter {

	private Context mContext;
	private GoodsGridViewAdapter mGridViewAdapter;
	private List<Goods> mListViewData;

	public PopularFragmentAdapter(Context context, List<Goods> goodsList) {
		mContext = context;
		mListViewData = goodsList;
	}

	@Override
	public int getCount() {
		return mListViewData.size() / 2;
	}

	@Override
	public Object getItem(int arg0) {
		return mGridViewAdapter;
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
					R.layout.list_view_popular, null);
			holder = new ViewHolder();
			holder.gridView = (PopularGridView) convertView
					.findViewById(R.id.grid_view_goods);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final List<Goods> mGridViewData = new ArrayList<Goods>();
		Log.i("PopularFragment", String.valueOf(position));
		mGridViewData.add(mListViewData.get(position * 2 + 1));
		mGridViewData.add(mListViewData.get(position * 2 + 2));
		mGridViewAdapter = new GoodsGridViewAdapter(mContext, mGridViewData);
		holder.gridView.setAdapter(mGridViewAdapter);
		holder.gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(mContext, GoodsActivity.class);
				i.putExtra(HomeFragment.EXTRA_GOODS_ID,
						mGridViewData.get((int) arg3).getId());
				mContext.startActivity(i);
			}
		});
		return convertView;
	}

	private class ViewHolder {
		private PopularGridView gridView;
	}

}
