package com.cosine.cosgame.threechaodoms;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.shop.Transaction;

public class SkinFactory {
	public static Skin makeSkin(int id) {
		Skin s = new Skin();
		s.setId(id);
		if (id == 1) {
			s.setOriginalImg("DiaoChan");
			s.setNewImg("DiaoChan_01");
			s.setSkinName("貂蝉");
			s.setTitle("薄幸的美人");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		}
		
		else {
			s = null;
		}
		return s;
	}
	
	public static Skin makeSkin(Document doc) {
		int id = doc.getInteger("id", 0);
		boolean inUse = doc.getBoolean("inUse", false);
		Skin s = makeSkin(id);
		s.setInUse(inUse);
		return s;
	}
}
