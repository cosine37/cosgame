package com.cosine.cosgame.threechaodoms;

import org.bson.Document;

public class SkinFactory {
	public static Skin makeSkin(int id) {
		Skin s = new Skin();
		
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
