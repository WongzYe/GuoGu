package com.example.guogu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.graphics.Bitmap;

public class User {
	
	public UUID mUserId;
	private Bitmap mAvator;
	private String mName;
	private boolean mMale;
	private List<UUID> mFollowedList = new ArrayList<UUID>();
	private List<UUID> mFollowerList = new ArrayList<UUID>();
	private List<UUID> mLikedList = new ArrayList<UUID>();
	private List<Comment> mHistoryComment = new ArrayList<Comment>();
	private List<Tag> mTags = new ArrayList<Tag>();
	
	public User(String name, boolean isMale) {
		mUserId = UUID.randomUUID();
		mName = name;
		mMale = isMale;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setAvator(Bitmap avator) {
		mAvator = avator;
	}
	
	public Bitmap getAvator() {
		return mAvator;
	}
	
	public boolean getSex() {
		return mMale;
	}
	
	public void follow(User user) {
		mFollowedList.add(user.mUserId);
	}
	
	public void beFollow(User user) {
		mFollowerList.add(user.mUserId);
	}
	
	public void like(Goods goods) {
		mLikedList.add(goods.mGoodsId);
	}
	
	public void WriteComment(Goods goods, String content) {
		Comment comment = new Comment(this, content);
		goods.getComment(comment);
		mHistoryComment.add(comment);
	}
}
