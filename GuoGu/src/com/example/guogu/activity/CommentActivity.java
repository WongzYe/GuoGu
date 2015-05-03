package com.example.guogu.activity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.example.guogu.R;
import com.example.guogu.fragment.HomeFragment;
import com.example.guogu.model.Goods;
import com.example.guogu.model.GoodsStoage;
import com.example.guogu.widget.swipback.app.SwipeBackActivity;

public class CommentActivity extends SwipeBackActivity implements
		OnClickListener {

	private TextView mNavigationName, mPostText;
	private ImageView mNavigationButton, mGoodsImg;
	private EditText mEditText;
	private Goods mGoods;
	private String mComment;
	protected static final String EXTRA_COMMENT = "comment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_comment);
		initView();
	}

	private void initView() {
		mGoods = GoodsStoage.get(this).getGoods(
				(UUID) getIntent().getSerializableExtra(
						HomeFragment.EXTRA_GOODS_ID));
		mNavigationName = (TextView) findViewById(R.id.menu_navigation_text);
		mPostText = (TextView) findViewById(R.id.upper_right_quarter_text);
		mEditText = (EditText) findViewById(R.id.editText);
		mGoodsImg = (ImageView) findViewById(R.id.goods_img);
		mNavigationButton = (ImageView) findViewById(R.id.menu_navigation_button);

		mNavigationButton.setImageResource(R.drawable.back);
		mNavigationButton.setOnClickListener(this);
		mGoodsImg.setImageResource(mGoods.getDrawable());
		mNavigationName.setText("µ„∆¿");
		mPostText.setVisibility(View.VISIBLE);
		mPostText.setText("∑¢≤º");
		mPostText.setOnClickListener(this);

		mEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				mComment = s.toString();
			}

		});
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				InputMethodManager inputManager = (InputMethodManager) mEditText
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				inputManager.showSoftInput(mEditText, 0);
			}
		}, 300);

		// mEditText.setOnEditorActionListener(new OnEditorActionListener() {
		//
		// @Override
		// public boolean onEditorAction(TextView v, int actionId,
		// KeyEvent event) {
		// if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
		// sendComment();
		// }
		// return false;
		// }
		// });
	}

	private void sendComment() {
		Intent data = new Intent();
		data.putExtra(EXTRA_COMMENT, mComment);
		setResult(RESULT_OK, data);
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu_navigation_button:
			scrollToFinishActivity();
			break;
		case R.id.upper_right_quarter_text:
			if (mComment != null) {
				sendComment();
			}
			break;
		}
	}
}
