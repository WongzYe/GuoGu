package com.example.guogu.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.guogu.R;
import com.example.guogu.adapter.CommentAdapter;
import com.example.guogu.adapter.GoodsPopupWindowsAdapter;
import com.example.guogu.fragment.HomeFragment;
import com.example.guogu.model.Comment;
import com.example.guogu.model.Goods;
import com.example.guogu.model.GoodsStoage;
import com.example.guogu.model.User;
import com.example.guogu.widget.swipback.app.SwipeBackActivity;

public class GoodsActivity extends SwipeBackActivity implements OnClickListener {

	private TextView mTextView, mPrice, mFavorNums, mFrom, mWriteComment,
			mNavigationName;
	private ImageView mGoodsImg, mFavorImg, mWriteCommentImg,
			mNavigationButton, mMenuItem1;
	private Goods mGoods;
	private ListView mListView;
	private CommentAdapter mAdapter;
	private List<Comment> mCommentList = new ArrayList<Comment>();

	private static final int REQUEST_COMMENT = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	public void initView() {
		setContentView(R.layout.activity_goods);
		UUID goodsId = (UUID) getIntent().getSerializableExtra(
				HomeFragment.EXTRA_GOODS_ID);
		mGoods = GoodsStoage.get(this).getGoods(goodsId);
		mTextView = (TextView) findViewById(R.id.goods_name);
		mPrice = (TextView) findViewById(R.id.price_button);
		mFavorNums = (TextView) findViewById(R.id.favor_num);
		mFrom = (TextView) findViewById(R.id.from_which_category);
		mWriteComment = (TextView) findViewById(R.id.write_comment_text);
		mNavigationName = (TextView) findViewById(R.id.menu_navigation_text);
		mGoodsImg = (ImageView) findViewById(R.id.goods_img);
		mFavorImg = (ImageView) findViewById(R.id.favor_img);
		mWriteCommentImg = (ImageView) findViewById(R.id.write_comment_img);
		mNavigationButton = (ImageView) findViewById(R.id.menu_navigation_button);
		mMenuItem1 = (ImageView) findViewById(R.id.menu_item_1);
		mListView = (ListView) findViewById(R.id.comment_list);

		mTextView.setText(mGoods.getName());
		mPrice.setText("￥ " + String.valueOf(mGoods.getPrice()));
		mFavorNums.setText(String.valueOf(mGoods.mFavoriteNums));
		mFrom.setText("来自[" + " ]");
		mNavigationName.setText("商品");
		mNavigationButton.setImageResource(R.drawable.back);
		mNavigationButton.setOnClickListener(this);
		mMenuItem1.setImageResource(R.drawable.ic_menu_share);
		mGoodsImg.setImageResource(mGoods.getDrawable());
		refreshView(mGoods.mFavorite);

		mAdapter = new CommentAdapter(this, mCommentList);
		mListView.setAdapter(mAdapter);

		mFavorImg.setOnClickListener(this);
		mWriteComment.setOnClickListener(this);
		mWriteCommentImg.setOnClickListener(this);
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.favor_img:
			mGoods.tapFavorite();
			refreshView(mGoods.mFavorite);
			break;
		case R.id.write_comment_img:
		case R.id.write_comment_text:
			Intent i = new Intent(this, CommentActivity.class);
			i.putExtra(HomeFragment.EXTRA_GOODS_ID, mGoods.getId());
			startActivityForResult(i, REQUEST_COMMENT);
			break;
		case R.id.menu_navigation_button:
			scrollToFinishActivity();
			break;
		}
	}

	private void refreshView(boolean isFavor) {
		if (isFavor) {
			mFavorImg.setImageResource(R.drawable.icon_like_press);
		} else {
			mFavorImg.setImageResource(R.drawable.icon_unlike);
		}
		mFavorNums.setText(String.valueOf(mGoods.mFavoriteNums));
	}

	public void postComment() {
		Intent i = new Intent(this, CommentActivity.class);
		i.putExtra(HomeFragment.EXTRA_GOODS_ID, mGoods.getId());
		startActivityForResult(i, REQUEST_COMMENT);
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent data) {
		if (data == null) {
			return;
		}
		User user = new User("WongzYe", true);
		user.setAvator(BitmapFactory.decodeResource(getResources(),
				R.drawable.wongzye));
		Comment comment = new Comment(user,
				data.getStringExtra(CommentActivity.EXTRA_COMMENT));
		mCommentList.add(comment);
		mAdapter = new CommentAdapter(this, mCommentList);
		mListView.setAdapter(mAdapter);
		super.onActivityResult(arg0, arg1, data);
	}

}
