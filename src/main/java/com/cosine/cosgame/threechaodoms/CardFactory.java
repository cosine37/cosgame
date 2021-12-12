package com.cosine.cosgame.threechaodoms;

import java.util.List;

import org.bson.Document;

public class CardFactory {
	public static Card makeCard(String img) {
		Card c = new Card();
		if (img.contentEquals("blankspace")) {
			c = new BlankSpaceCard();
		}
		return c;
	}
	
	public static Card makeCard(Document doc) {
		String img = doc.getString("img");
		//int where = doc.getInteger("where", -1);
		List<Integer> extraBits = (List<Integer>) doc.get("extraBits");
		Card c = makeCard(img);
		//c.setWhere(where);
		c.setExtraBits(extraBits);
		return c;
	}
}
