package com.example.guogu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.example.guogu.R;
import com.example.guogu.activity.GoodsActivity;
import com.example.guogu.activity.PopularActivity;
import com.example.guogu.activity.TopListener;
import com.example.guogu.adapter.HomeFragmentAdapter;
import com.example.guogu.adapter.MyPagerAdapter;
import com.example.guogu.model.Goods;
import com.example.guogu.model.GoodsStoage;
import com.example.guogu.view.HomeListView;

public class HomeFragment extends Fragment implements OnClickListener{

	private TextView mNavigationName;
	private ImageView mNavigationButton, mMenuItem1, mMenuItem2;
	private HomeListView mListView;
	private HomeFragmentAdapter mAdapter;
	private ArrayList<Goods> mGoodsList;
	private List<ImageView> mImageList = new ArrayList<ImageView>();
	private ViewPager mHomeViewPager;
	private Message mMessage;
	private Handler mHandler;

	private int[] ids = { R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
			R.drawable.cat4, R.drawable.cat5, R.drawable.cat6, R.drawable.cat7 };

	private static final int VIEWPAGER_PLAY_STOP = 0;
	private static final int VIEWPAGER_PLAY_START = 1;
	public static final String EXTRA_GOODS_ID = "goods_id";

	@Override
	public void onCreate(Bundle savedInsatanceStateBundle) {
		super.onCreate(savedInsatanceStateBundle);
		setRetainInstance(true);
		mGoodsList = GoodsStoage.get(getActivity()).getGoodsList();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, parent, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		mNavigationName = (TextView) view
				.findViewById(R.id.menu_navigation_text);
		mNavigationButton = (ImageView) view
				.findViewById(R.id.menu_navigation_button);
		mMenuItem1 = (ImageView) view.findViewById(R.id.menu_item_1);
		mMenuItem2 = (ImageView) view.findViewById(R.id.menu_item_2);

		mNavigationName.setText("…Ã∆∑");
		mNavigationButton
				.setBackgroundResource(R.drawable.ic_navigation_drawer);
		mNavigationButton.setOnClickListener(this);
		mMenuItem1.setImageResource(R.drawable.action_icon_refresh);
		mMenuItem2.setImageResource(R.drawable.action_icon_search);

		mListView = (HomeListView) view
				.findViewById(R.id.home_fragment_listView);
		mAdapter = new HomeFragmentAdapter(getActivity(), mGoodsList);
		mListView.setAdapter(mAdapter);
		// setListViewItemHeight(mListView);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (id == 0) {
					return;
				}
				Intent i = new Intent(getActivity(), GoodsActivity.class);
				i.putExtra(EXTRA_GOODS_ID, mGoodsList.get((int) id).getId());
				startActivity(i);
				/*Log.i("TAG", String.valueOf(position) + String.valueOf(id));*/
			}
		});
		mHomeViewPager = (ViewPager) mListView
				.findViewById(R.id.home_view_pager);
		initViewPager(ids);
		mHomeViewPager.setAdapter(new MyPagerAdapter(mImageList));
		mHomeViewPager.setOnTouchListener(mViewPagerTouchListener);

		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case VIEWPAGER_PLAY_START:
					mHomeViewPager.setCurrentItem((mHomeViewPager
							.getCurrentItem() + 1) % ids.length);
					mMessage = mHandler.obtainMessage();
					mMessage.what = VIEWPAGER_PLAY_START;
					mHandler.sendMessageDelayed(mMessage, 4000);
					break;
				case VIEWPAGER_PLAY_STOP:
					mHandler.removeMessages(VIEWPAGER_PLAY_START);
					break;
				}

			}
		};

		mMessage = mHandler.obtainMessage();
		mMessage.what = VIEWPAGER_PLAY_START;
		mHandler.sendMessageDelayed(mMessage, 4000);
	}

	public void initViewPager(int[] ids) {

		for (int i = 0; i < ids.length; i++) {
			ImageView image = new ImageView(getActivity());
			image.setScaleType(ScaleType.CENTER_CROP);
			image.setImageResource(ids[i]);
			mImageList.add(image);
		}

	}

	// @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	// public void setListViewItemHeight(HomeListView listView) {
	// ViewGroup.LayoutParams lp = listView.getLayoutParams();
	// int itemSize = listView.getAdapter().getCount();
	// int totalHeight = 0;
	// for (int i = 0; i < itemSize; i++) {
	// totalHeight += 100;
	// }
	// lp.height = totalHeight
	// + (listView.getDividerHeight() * (itemSize - 1));
	// listView.setLayoutParams(lp);
	// }

	private OnTouchListener mViewPagerTouchListener = new OnTouchListener() {

		@SuppressLint("ClickableViewAccessibility")
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mMessage = mHandler.obtainMessage();
				mMessage.what = VIEWPAGER_PLAY_STOP;
				mHandler.sendMessage(mMessage);
				break;
			case MotionEvent.ACTION_UP:
				mMessage = mHandler.obtainMessage();
				mMessage.what = VIEWPAGER_PLAY_START;
				mHandler.sendMessageDelayed(mMessage, 4000);
				break;
			}
			return false;
		}
	};

	@Override
	public void onPause() {
		mHandler.removeMessages(VIEWPAGER_PLAY_STOP);
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.menu_navigation_button:
			((TopListener) getActivity()).toggle();
			break;
		}
		
	}
}
