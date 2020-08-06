package com.cosine.cosgame.citadels;

import org.bson.Document;

public class CardFactory {
	public static Card createCard(String name, int color, int cost, String img) {
		Card card = new Card(name);
		card.setCost(cost);
		card.setColor(color);
		card.setImg(img);
		return card;
	}
	
	public static Card createCard(Document doc) {
		String name = doc.getString("name");
		int color = doc.getInteger("color", 0);
		int cost = doc.getInteger("cost", 0);
		String img = doc.getString("img");
		return createCard(name, color, cost, img);
	}

}
