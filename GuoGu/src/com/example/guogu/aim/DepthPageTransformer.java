package com.example.guogu.aim;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

public class DepthPageTransformer implements ViewPager.PageTransformer {
	private static final float MIN_SCALE = 0.75f;

	@SuppressLint("NewApi")
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();

		if (position < -1) { // [-∞ ,-1)
			// 这一页已经是最左边的屏幕页
			view.setAlpha(0);

		} else if (position <= 0) { // [-1,0]
			// 向左面滑屏使用默认的过渡动画
			view.setAlpha(1);
			view.setTranslationX(0);
			view.setScaleX(1);
			view.setScaleY(1);

		} else if (position <= 1) { // (0,1]
			// 淡出页面
			view.setAlpha(1 - position);

			// 抵消默认的整页过渡
			view.setTranslationX(pageWidth * -position);

			// 根据缩放系数变化 (在 MIN_SCALE 和 1 之间变化)
			float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
					* (1 - Math.abs(position));
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);

		} else { // (1,+∞]
			// 这一页已经是最右边的屏幕页
			view.setAlpha(0);
		}
	}
}