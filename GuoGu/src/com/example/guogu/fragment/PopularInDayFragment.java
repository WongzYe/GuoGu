package com.example.guogu.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.guogu.R;
import com.example.guogu.adapter.GoodsGridViewAdapter;
import com.example.guogu.adapter.PopularFragmentAdapter;
import com.example.guogu.model.Goods;
import com.example.guogu.model.GoodsStoage;
import com.example.guogu.xlistVieiw.IXListViewRefreshListener;
import com.example.guogu.xlistVieiw.XListView;
import com.example.guogu.xlistVieiw.XListView.IStartScroll;

public class PopularInDayFragment extends Fragment {

	private XListView mXListView;
	private PopularFragmentAdapter mAdapter;
	private ArrayList<Goods> mGoodsList;
	private View mMainView;
//	private LinearLayout.LayoutParams mNomalParams, mRefeshLayoutParams;

	protected static final int REFRESH_MORE = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGoodsList = GoodsStoage.get(getActivity()).getGoodsList();
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(R.layout.fragment_popular,
				(ViewGroup) getActivity().findViewById(R.id.view_pager), false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		ViewGroup viewParent = (ViewGroup) mMainView.getParent();
		if (viewParent != null) {
			viewParent.removeAllViewsInLayout();
		}
//		initLayoutParams(getActivity());
		initView(mMainView);
		return mMainView;
	}

	private void initView(View view) {
		mXListView = (XListView) view.findViewById(R.id.x_list_view);
		mAdapter = new PopularFragmentAdapter(getActivity(), mGoodsList);
		mXListView.setAdapter(mAdapter);
		mXListView.setPullRefreshEnable(mRefreshListener);
		mXListView.setStartScroll(mStartScoll);
	}

//	private void initLayoutParams(Context context) {
//		int width = context.getResources().getDisplayMetrics().widthPixels;
//		int height = LinearLayout.LayoutParams.MATCH_PARENT;
//		int topMargin = (int) (50 * (context.getResources().getDisplayMetrics().density) + 0.5f);
//
//		mNomalParams = new LinearLayout.LayoutParams(width, height);
//		mRefeshLayoutParams = new LinearLayout.LayoutParams(width, height);
//		mRefeshLayoutParams.topMargin = topMargin;
//	}

	private IStartScroll mStartScoll = new IStartScroll() {

		@Override
		public void startScroll() {
//			mXListView.setLayoutParams(mRefeshLayoutParams);
		}
	};

	private IXListViewRefreshListener mRefreshListener = new IXListViewRefreshListener() {

		@Override
		public void onRefresh() {
			mHandler.sendEmptyMessageDelayed(REFRESH_MORE, 1000);
		}
	};

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case REFRESH_MORE:
				mXListView.stopRefresh(new SimpleDateFormat("MM-dd HH:mm")
						.format(new Date()));
				mAdapter.notifyDataSetChanged();
//				mXListView.setLayoutParams(mNomalParams);
				break;
			default:
				break;
			}
		};
	};
}
