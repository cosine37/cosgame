package com.cosine.cosgame.threechaodoms.shop;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Skin;
import com.cosine.cosgame.threechaodoms.SkinFactory;
import com.cosine.cosgame.threechaodoms.entity.ShopEntity;

public class Shop {
	public Shop() {
		
	}
	
	public List<Transaction> dailyReward() {
		List<Transaction> ts = new ArrayList<>();
		Transaction t = new Transaction(Transaction.MONEY, 100, "每日奖励");
		ts.add(t);
		return ts;
	}
	
	public Skin genSkin(int id) {
		Skin s = SkinFactory.makeSkin(id);
		return s;
	}
	
	public ShopEntity toShopEntity() {
		ShopEntity entity = new ShopEntity();
		return entity;
	}
}
