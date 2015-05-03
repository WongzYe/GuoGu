package com.example.guogu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Goods {

	public UUID mGoodsId;
	private int mName;
	private int mDrawable;
	private float mPrice;
	public static boolean mFavorite;
	public int mFavoriteNums = 0;
	private Date mPostTime;
	private List<Comment> mCommentList = new ArrayList<Comment>();

	public Goods(int name, int drawable, float price) {
		mGoodsId = UUID.randomUUID();
		mName = name;
		mDrawable = drawable;
		mPrice = price;
		mPostTime = new Date();
	}

	public int getName() {
		return mName;
	}

	public void setName(int name) {
		mName = name;
	}

	public int getDrawable() {
		return mDrawable;
	}

	public void setDrawable(int drawable) {
		mDrawable = drawable;
	}

	public float getPrice() {
		return mPrice;
	}

	public void setPrice(float pricie) {
		mPrice = pricie;
	}

	public Date getPostDate() {
		return mPostTime;
	}

	public void tapFavorite() {
		if (mFavorite) {
			if (mFavoriteNums > 0) {
				mFavoriteNums--;
			}
			mFavorite = false;
		} else {
			mFavoriteNums++;
			mFavorite = true;
		}
	}

	public void getComment(Comment comment) {
		mCommentList.add(comment);
	}

	public UUID getId() {
		return mGoodsId;
	}

	public int commentSize() {
		return mCommentList.size();
	}
}
