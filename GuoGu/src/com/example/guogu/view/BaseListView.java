package com.example.guogu.view;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.guogu.R;

public class BaseListView extends ListView implements OnScrollListener {

	private View header;
	private View footer;
	private RelativeLayout mPullToRefreshLayout;
	private ViewPager mViewPager;
	private ImageView mRefreshImg;
	private int mHeaderHeight;
	private int mFooterHeight;
	private OnRefreshListener mRefreshListener;
	private float mStartDegree;
	private float mEndDegree = 10;
	private float mMoveSpace = 0;
	private float mVelocityX;

	private int STATE;
	private final int NONE = 0;
	private final int PULL = 1;
	private final int RELASE = 2;
	private final int RELASEING = 3;

	private boolean mIsRemark;
	private int mStartY;
	private int mScrollState;
	private int mFirstVisibleItem;
	private boolean mIsFinalItem;

	public BaseListView(Context context) {
		super(context);
		initView(context);
	}

	public BaseListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public BaseListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);

	}

	@SuppressLint("InflateParams")
	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);

		header = (View) inflater.inflate(R.layout.header_layout, null);
		mPullToRefreshLayout = (RelativeLayout) header
				.findViewById(R.id.pull_to_refresh_layout);
		mRefreshImg = (ImageView) header.findViewById(R.id.refresh_img);
		mViewPager = (ViewPager) header.findViewById(R.id.home_view_pager);
		setViewPagerVisibility(false);
		measureView(mPullToRefreshLayout);
		mHeaderHeight = mPullToRefreshLayout.getMeasuredHeight();
		Log.i("tag", "headerHeight = " + mHeaderHeight);
		headerDisplay(-mHeaderHeight);
		this.addHeaderView(header);
		this.setOnScrollListener(this);

		footer = (View) inflater.inflate(R.layout.footer_layout, null);
		measureView(footer);
		mFooterHeight = footer.getMeasuredHeight();
		footerDisplay(mFooterHeight);
		this.addFooterView(footer);

	}

	private void measureView(View view) {
		ViewGroup.LayoutParams params = view.getLayoutParams();
		if (params == null) {
			params = new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int widthMeasureSpec = ViewGroup
				.getChildMeasureSpec(0, 0, params.width);
		int heightMeasureSpec;
		int tempHeight = params.height;
		if (tempHeight > 0) {
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(tempHeight,
					MeasureSpec.EXACTLY);
		} else {
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}

		view.measure(widthMeasureSpec, heightMeasureSpec);
	}

	public void setViewPagerVisibility(boolean visible) {
		if (visible) {
			mViewPager.setVisibility(View.VISIBLE);
		} else {
			mViewPager.setVisibility(View.GONE);
		}
	}

	@SuppressLint("NewApi")
	private void refreshAim(int state, float offSet) {
		// RotateAnimation roateAim = new RotateAnimation(mStartDegree,
		// mEndDegree, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
		// RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		// roateAim.setFillAfter(true);
		// roateAim.setDuration(500);
		// ObjectAnimator animator = ObjectAnimator.ofFloat(mRefreshImg,
		// "rotation", mStartDegree, mEndDegree);
		// animator.setDuration(500);
		switch (state) {
		case NONE:
			// mRefreshImg.clearAnimation();
			mStartDegree = 0;
			mEndDegree = 10;
		case PULL:
			// mRefreshImg.clearAnimation();
			// mRefreshImg.setAnimation(roateAim);
			ObjectAnimator
					.ofFloat(mRefreshImg, "rotation", mStartDegree, mEndDegree)
					.setDuration(500).start();
		case RELASE:
			break;
		case RELASEING:
			// mRefreshImg.clearAnimation();
			// roateAim = new RotateAnimation(0, 360,
			// RotateAnimation.RELATIVE_TO_SELF, 0.5f,
			// RotateAnimation.RELATIVE_TO_SELF, 0.5f);
			// roateAim.setDuration(Long.MAX_VALUE);
			// mRefreshImg.setAnimation(roateAim);
			ObjectAnimator.ofFloat(mRefreshImg, "rotation", 0F, 360F)
					.setDuration(Long.MAX_VALUE).start();
			break;
		}
		mStartDegree += offSet;
		mEndDegree += offSet;
		Log.i("TAG", "mStartDegree = " + mStartDegree + " mEndDegree = "
				+ mEndDegree + " offSet = " + offSet);
	}

	private void headerDisplay(int space) {
		if (header != null) {
			header.setPadding(header.getPaddingLeft(), space,
					header.getPaddingRight(), header.getPaddingBottom());
			header.invalidate();
		}
	}

	private void footerDisplay(int space) {
		if (footer != null) {
			footer.setPadding(footer.getPaddingLeft(), footer.getPaddingTop(),
					footer.getPaddingRight(), space);
			footer.invalidate();
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		mScrollState = scrollState;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		mFirstVisibleItem = firstVisibleItem;
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mStartY = (int) event.getY();
			if (mFirstVisibleItem == 0) {
				mIsRemark = true;
			}
			// Log.i("BaseListView", String.valueOf(STATE));
			break;
		case MotionEvent.ACTION_MOVE:
			if (!mIsRemark) break;
			mMoveSpace = event.getY() - mStartY;
			int headerMovingHeight = (int) (mMoveSpace - mHeaderHeight);
			if (headerMovingHeight > 80) {
				headerMovingHeight = 80;
			}

			int footerMovingHeight = (int) (mMoveSpace - mFooterHeight);

			switch (STATE) {
			case NONE:
				if (mIsRemark && mMoveSpace > 0) {
					STATE = PULL;
					refreshAim(STATE, mVelocityX);
				}
				break;
			case PULL:
				headerDisplay(headerMovingHeight);
				if (mMoveSpace > mHeaderHeight / 2) {
					STATE = RELASE;
					refreshAim(STATE, mVelocityX);
				}
				break;
			case RELASE:
				headerDisplay(headerMovingHeight);
				if (mMoveSpace < mHeaderHeight * 0.3) {
					STATE = PULL;
					refreshAim(STATE, mVelocityX);
				} else if (mMoveSpace <= 0) {
					STATE = NONE;
					mIsRemark = false;
				}
			}
			// Log.i("BaseListView", String.valueOf(STATE));

			if (mIsFinalItem) {
				footerDisplay((int) (mFooterHeight - mMoveSpace));
			}
			break;

		case MotionEvent.ACTION_UP:
			if (STATE == RELASE) {
				STATE = RELASEING;
				refreshAim(STATE, mVelocityX);
				if (mRefreshListener != null) {
					mRefreshListener.onRefresh();
				}
				STATE = NONE;
				mIsRemark = false;
				headerDisplay(-mHeaderHeight);
			} else {
				STATE = NONE;
				mIsRemark = false;
				headerDisplay(-mHeaderHeight);
			}
			// Log.i("BaseListView", String.valueOf(STATE));

			break;
		}
		return super.onTouchEvent(event);
	}

	public void setOnRefreshListener(OnRefreshListener listener) {
		mRefreshListener = listener;
	}

	interface OnRefreshListener {
		void onRefresh();
	}

}
