package com.cosine.cosgame.citadels;

import org.bson.Document;

import com.cosine.cosgame.citadels.specialcards.*;

public class CardFactory {
	public static Card createCard(String name, int color, int cost, String img) {
		Card card;
		if (img.contentEquals("p601")) {
			card = new DinosaurPark();
		} else if (img.contentEquals("p602")) {
			card = new SECenter();
		}
		
		else {
			card = new Card(name);
			card.setCost(cost);
			card.setColor(color);
			card.setImg(img);
		}
		
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
