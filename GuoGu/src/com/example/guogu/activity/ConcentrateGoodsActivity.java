package com.example.guogu.activity;

import java.util.ArrayList;

import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.example.guogu.R;
import com.example.guogu.adapter.ConcentrateGoodsAdapter;
import com.example.guogu.adapter.GoodsPopupWindowsAdapter;
import com.example.guogu.model.Goods;
import com.example.guogu.model.GoodsStoage;
import com.example.guogu.view.BaseListView;
import com.example.guogu.widget.swipback.app.SwipeBackActivity;

public class ConcentrateGoodsActivity extends SwipeBackActivity implements
		OnClickListener {

	private TextView mNavigationName;
	private ImageView mNavigationButton, mPopWindowButton, mMenuItem1,
			mMenuItem2, mPopupWindowTouchTag;
	private View mPopupView;
	private PopupWindow mPopupWindow;
	private ArrayList<Goods> mGoodsList;
	private BaseListView mListView;
	private ListView mPopupListView;
	private ConcentrateGoodsAdapter mAdapter;
	private GoodsPopupWindowsAdapter mPopupWindowsAdapter;

	private String[] mCategoryList = { "所有", "女装", "男装", "孩童", "配饰", "美容",
			"科技", "居家", "户外", "文化", "美食", "玩乐" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_concentrate_goods);
		mGoodsList = GoodsStoage.get(this).getGoodsList();
		mAdapter = new ConcentrateGoodsAdapter(this, mGoodsList);
		initView();
	}

	public void initView() {
		mNavigationName = (TextView) findViewById(R.id.menu_navigation_text);
		mNavigationButton = (ImageView) findViewById(R.id.menu_navigation_button);
		mNavigationButton.setOnClickListener(this);
		mPopWindowButton = (ImageView) findViewById(R.id.pop_window_button);
		mMenuItem1 = (ImageView) findViewById(R.id.menu_item_1);
		mMenuItem2 = (ImageView) findViewById(R.id.menu_item_2);
		mListView = (BaseListView) findViewById(R.id.goods_list_view);

		mNavigationName.setText("所有 ");
		mNavigationButton.setImageResource(R.drawable.back);
		mPopWindowButton.setImageResource(R.drawable.bg_spinner);
		mPopWindowButton.setOnClickListener(this);
		mMenuItem1.setImageResource(R.drawable.action_icon_refresh);
		mMenuItem2.setImageResource(R.drawable.action_icon_search);
		mListView.setAdapter(mAdapter);

		mPopupView = getLayoutInflater().inflate(R.layout.popup_window_goods,
				null);
		mPopupWindowTouchTag = (ImageView) mPopupView
				.findViewById(R.id.touch_tag);
		mPopupListView = (ListView) mPopupView
				.findViewById(R.id.popup_window_list_view);
		mPopupWindowsAdapter = new GoodsPopupWindowsAdapter(this, mCategoryList);
		mPopupListView.setAdapter(mPopupWindowsAdapter);
		mPopupWindow = new PopupWindow(mPopupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.setFocusable(true);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu_navigation_button:
			scrollToFinishActivity();
			break;
		case R.id.pop_window_button:
			showPopupWindow();
			break;
		}
	}

	private void showPopupWindow() {
		if (!mPopupWindow.isShowing()) {
			mPopupWindow.showAsDropDown(mNavigationName, 0, 10);
			mPopupWindow.setOutsideTouchable(true);
		} else {
			mPopupWindow.dismiss();
		}
		mPopupWindow.update();
	}
}
