package com.example.guogu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {

	private User mUser;
	private String mComment;
	private Date mPostDate;
	public static boolean mLikes;
	public int mLikesNum;
	private List<Comment> mReplay = new ArrayList<Comment>();

	public Comment(User user, String comment) {
		mUser = user;
		mComment = comment;
		mPostDate = new Date();
	}

	public User getUser() {
		return mUser;
	}

	public String getComment() {
		return mComment;
	}

	public Date getPostDate() {
		return mPostDate;
	}

	public void like() {
		mLikesNum++;
	}

	public void replay(Comment comment) {
		mReplay.add(comment);
	}

	public int replayNums() {
		return mReplay.size();
	}

	public void tapLikes() {
		if(mLikes) {
			mLikes = false;
			if(mLikesNum > 0) {
				mLikesNum --;
			}
		} else {
			mLikes = true;
			mLikesNum ++;
		}
	}
}
