package com.cosine.cosgame.gardenwar;

import java.util.List;

import org.bson.Document;

public class CardFactory {
	public static Card makeCard(String name) {
		Card card = new Card();
		//TODO: build cards here
		return card;
	}
	
	public static Card makeCard(Document doc) {
		String name = doc.getString("name");
		List<Integer> extraBits = (List<Integer>) doc.get("extraBits");
		Card card = makeCard(name);
		card.setExtraBits(extraBits);
		return card;
	}
}
