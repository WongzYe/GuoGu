package com.example.guogu.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guogu.R;
import com.example.guogu.model.Comment;

public class CommentAdapter extends BaseAdapter {

	private List<Comment> mCommentList;
	private Context mContext;
	private Comment mComment;

	public CommentAdapter(Context context, List<Comment> commentList) {
		mContext = context;
		mCommentList = commentList;
	}

	@Override
	public int getCount() {
		return mCommentList.size();
	}

	@Override
	public Object getItem(int position) {
		return mCommentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder mHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_view_comment, null);
			mHolder = new ViewHolder();
			mHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
			mHolder.likes = (ImageView) convertView.findViewById(R.id.likes);
			mHolder.replay = (ImageView) convertView
					.findViewById(R.id.replay_img);
			mHolder.editorTag = (ImageView) convertView
					.findViewById(R.id.editor_tag);
			mHolder.userName = (TextView) convertView
					.findViewById(R.id.user_name);
			mHolder.comment = (TextView) convertView.findViewById(R.id.comment);
			mHolder.pokeNums = (TextView) convertView
					.findViewById(R.id.poke_nums);
			mHolder.replayNums = (TextView) convertView
					.findViewById(R.id.replay_nums);
			mHolder.postDate = (TextView) convertView
					.findViewById(R.id.post_date);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mComment = mCommentList.get(position);
		mHolder.avatar.setImageBitmap(mComment.getUser().getAvator());
		// refreshView(mComment.mLikes);
		mHolder.likes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.likes:
					mComment.tapLikes();
					mHolder.refreshView(mComment.mLikes);
					break;

				default:
					break;
				}
			}
		});
		mHolder.replay.setImageResource(R.drawable.icon_comment);
		mHolder.userName.setText(mComment.getUser().getName());
		mHolder.comment.setText(mComment.getComment());
		mHolder.postDate.setText(new SimpleDateFormat("MM-dd HH:mm")
				.format(mComment.getPostDate()));
		if (position == 0) {
			mHolder.editorTag.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView avatar, likes, replay, editorTag;
		private TextView userName, comment, pokeNums, replayNums, postDate;

		private void refreshView(boolean isLikes) {
			if (isLikes) {
				likes.setImageResource(R.drawable.icon_poke_press);
			} else {
				likes.setImageResource(R.drawable.icon_poke);
			}
			pokeNums.setText(String.valueOf(mComment.mLikesNum));
		}
	}

	// private OnClickListener mListener = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.likes:
	// mComment.tapLikes();
	// refreshView(mComment.mLikes);
	// break;
	// }
	// }
	// };

}
