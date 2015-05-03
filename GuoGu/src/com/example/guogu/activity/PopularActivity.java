package com.example.guogu.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guogu.R;
import com.example.guogu.adapter.MyFragmentPagerAdapter;
import com.example.guogu.fragment.PopularInDayFragment;
import com.example.guogu.fragment.PopularInWeekFragment;
import com.example.guogu.swipback.SwipeBackLayout;
import com.example.guogu.widget.swipback.app.SwipeBackActivity;

public class PopularActivity extends SwipeBackActivity implements OnClickListener{

	private TextView mNavigationName;
	private ImageView mNavigationButton, mMenuItem1, mMenuItem2;
	private ViewPager mViewPager;
	private List<Fragment> mFragmentList = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popular);
		mViewPager = (ViewPager) findViewById(R.id.view_pager);

		PopularInDayFragment dayFragment = new PopularInDayFragment();
		PopularInWeekFragment weekFragment = new PopularInWeekFragment();
		mFragmentList.add(dayFragment);
		mFragmentList.add(weekFragment);

		mViewPager.setAdapter(new MyFragmentPagerAdapter(
				getSupportFragmentManager(), mFragmentList));
		initView();
	}
	
	private void initView() {
		mNavigationName = (TextView) findViewById(R.id.menu_navigation_text);
		mNavigationButton = (ImageView) findViewById(R.id.menu_navigation_button);
		mMenuItem1 = (ImageView) findViewById(R.id.menu_item_1);
		mMenuItem2 = (ImageView) findViewById(R.id.menu_item_2);
		
		mNavigationName.setText("»»√≈");
		mNavigationButton.setBackgroundResource(R.drawable.back);
		mNavigationButton.setOnClickListener(this);
		mMenuItem1.setImageResource(R.drawable.action_icon_refresh);
		mMenuItem2.setImageResource(R.drawable.action_icon_search);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.menu_navigation_button:
			scrollToFinishActivity();
			break;
		}
	}
}
