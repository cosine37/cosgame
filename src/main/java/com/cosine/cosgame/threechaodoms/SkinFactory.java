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
			s.setTitle("絕世的舞姬");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 2) {
			s.setOriginalImg("SunCe");
			s.setNewImg("SunCe_01");
			s.setSkinName("孙策");
			s.setTitle("五四三二零");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 3) {
			s.setOriginalImg("ZhuGeLiang");
			s.setNewImg("ZhuGeLiang_01");
			s.setSkinName("諸葛亮");
			s.setTitle("臥龍");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 4) {
			s.setOriginalImg("XiaHouDun");
			s.setNewImg("XiaHouDun_01");
			s.setSkinName("夏侯惇");
			s.setTitle("獨眼的羅剎");
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
