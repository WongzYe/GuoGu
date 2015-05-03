package com.example.guogu.activity;

import java.util.ArrayList;
import java.util.List;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guogu.R;
import com.example.guogu.adapter.SidingMenuAdapter;
import com.example.guogu.fragment.FollowingActionFragment;
import com.example.guogu.fragment.HomeFragment;
import com.example.guogu.model.MenuItem;

public class MainActivity extends FragmentActivity implements TopListener {

	private MenuDrawer mMenuDrawer;
	private ListView mSidingMenuListView;
	private ImageView mAvatarsImage;
	private TextView mAvatarName;
	private List<MenuItem> mMenuItems = new ArrayList<MenuItem>();
	private Fragment mFragment;
	private Handler mHandler;

	private final int[] mMenuItemNames = { R.string.siding_menu_home,
			R.string.siding_menu_activity, R.string.siding_menu_explore,
			R.string.siding_menu_feedback, R.string.siding_menu_message };
	private final int[] mMenuItemImages = { R.drawable.icon_menu_selection,
			R.drawable.icon_menu_hot, R.drawable.icon_menu_event,
			R.drawable.icon_menu_message, R.drawable.icon_menu_feedback};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND,
				Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW);
		mMenuDrawer.setContentView(R.layout.activity_content);
		mMenuDrawer.setMenuView(R.layout.siding_menu);
		mHandler = new Handler();
		initMenu();
		replaceFragment(0);
		// if (savedInstanceState == null) {
		// HomeFragment fragment = new HomeFragment();
		// getSupportFragmentManager()
		// .beginTransaction()
		// .add(android.R.id.content, fragment,
		// fragment.getClass().getSimpleName()).commit();
		// }
	}

	private void initMenu() {
		MenuItem item;
		mSidingMenuListView = (ListView) findViewById(R.id.siding_menu_listView);
		mAvatarsImage = (ImageView) findViewById(R.id.avatar);
		mAvatarName = (TextView) findViewById(R.id.user_name);
		mAvatarName.setText("WongzYe");
		mAvatarsImage.setBackgroundResource(R.drawable.wongzye);
		mSidingMenuListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		for (int i = 0; i < mMenuItemNames.length; i++) {
			item = new MenuItem(mMenuItemNames[i], mMenuItemImages[i]);
			mMenuItems.add(item);
		}
		mSidingMenuListView.setAdapter(new SidingMenuAdapter(this, mMenuItems));
		mSidingMenuListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						replaceFragment((int) arg2);
						mMenuDrawer.closeMenu();
					}
				}, 100);
			}
		});
	}

	public void replaceFragment(int id) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		if (mFragment != null) {
			transaction.detach(mFragment);
		}
		switch (id) {
		case 0:
			mFragment = new HomeFragment();
			break;
		case 1:
			mFragment = new FollowingActionFragment();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
		transaction.replace(R.id.fragmentContainer, mFragment);
		transaction.commit();
	}

	@Override
	public void toggle() {
		mMenuDrawer.toggleMenu();
	}

}
