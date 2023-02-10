package com.cosine.cosgame.propnightold;

import java.util.List;

import org.bson.Document;

public class CardFactory {
	public static Card makeCard(int id) {
		Card c = new Card();
		return c;
	}
	
	public static Card makeCard(Document doc) {
		int id = doc.getInteger("id", 0);
		List<Integer> extraBits = (List<Integer>) doc.get("extraBits");
		Card c = makeCard(id);
		c.setExtraBits(extraBits);
		return c;
	}
}
