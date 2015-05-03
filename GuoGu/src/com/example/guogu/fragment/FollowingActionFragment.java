package com.example.guogu.fragment;

import com.example.guogu.R;
import com.example.guogu.activity.TopListener;
import com.example.guogu.adapter.FollowingFragmentAdapter;
import com.example.guogu.xlistVieiw.XListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class FollowingActionFragment extends Fragment implements OnClickListener {
	
	private TextView mNavigationName;
	private ImageView mNavigationButton, mMenuItem1, mMenuItem2;
	private XListView mListView;
	private FollowingFragmentAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_following_action,
				container, false);
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

		mNavigationName.setText("¶¯Ì¬");
		mNavigationButton.setImageResource(R.drawable.ic_navigation_drawer);
		mNavigationButton.setOnClickListener(this);
		mMenuItem1.setImageResource(R.drawable.action_icon_refresh);
		mMenuItem2.setImageResource(R.drawable.action_icon_search);
		
		mListView = (XListView) view.findViewById(R.id.x_list_view);
		mAdapter = new FollowingFragmentAdapter(getActivity());
		mListView.setAdapter(mAdapter);
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
