package com.example.guogu.model;

import java.util.ArrayList;
import java.util.UUID;

import com.example.guogu.R;

import android.content.Context;

public class GoodsStoage {

	private static GoodsStoage sGoodsStoage;
	private Context mAppContext;
	private ArrayList<Goods> mGoodsList;

	private GoodsStoage(Context appContext) {
		mAppContext = appContext;
		mGoodsList = new ArrayList<Goods>();
		mGoodsList.add(null);
		for (int i = 0; i < 10; i++) {
			Goods goods1 = new Goods(R.string.goods_name, R.drawable.demo1,
					362.00f);
			Goods goods2 = new Goods(R.string.goods_name, R.drawable.demo2,
					1180.00f);
			Goods goods3 = new Goods(R.string.goods_name, R.drawable.demo3,
					238.00f);
			Goods goods4 = new Goods(R.string.goods_name, R.drawable.demo4,
					310.00f);
			Goods goods5 = new Goods(R.string.goods_name, R.drawable.demo5,
					980.00f);
			Goods goods6 = new Goods(R.string.goods_name, R.drawable.demo6,
					65.00f);
			mGoodsList.add(goods1);
			mGoodsList.add(goods2);
			mGoodsList.add(goods3);
			mGoodsList.add(goods4);
			mGoodsList.add(goods5);
			mGoodsList.add(goods6);
		}
	}

	public static GoodsStoage get(Context c) {
		if (sGoodsStoage == null) {
			sGoodsStoage = new GoodsStoage(c.getApplicationContext());
		}
		return sGoodsStoage;
	}

	public ArrayList getGoodsList() {
		return mGoodsList;
	}

	public Goods getGoods(UUID id) {
		for (Goods g : mGoodsList) {
			if (g != null && g.getId().equals(id)) {
				return g;
			}
		}
		return null;
	}
}
