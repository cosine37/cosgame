package com.cosine.cosgame.citadels;

import org.bson.Document;

import com.cosine.cosgame.citadels.specialcards.*;

public class CardFactory {
	public static Card createCard(String name, int color, int cost, String img, int builtRound) {
		Card card;
		if (img.contentEquals("p601")) {
			card = new DinosaurPark();
		} else if (img.contentEquals("p602")) {
			card = new SECenter();
		} else if (img.contentEquals("p401")) {
			card = new PlanningHall();
		} else if (img.contentEquals("p301")) {
			card = new FormerResidence();
		} else if (img.contentEquals("p603")) {
			card = new Library();
		} else if (img.contentEquals("p604")) {
			card = new GlobalHarbor();
		} else if (img.contentEquals("p201")) {
			card = new Inn();
		} else if (img.contentEquals("p605")) {
			card = new GreatWall();
		} else if (img.contentEquals("p501")) {
			card = new Subway();
		} else if (img.contentEquals("p502")) {
			card = new SouthStreet();
		}
		
		else {
			card = new Card(name);
			card.setCost(cost);
			card.setColor(color);
			card.setImg(img);
		}
		
		card.setBuiltRound(builtRound);
		return card;
	}
	
	public static Card createCard(Document doc) {
		String name = doc.getString("name");
		int color = doc.getInteger("color", 0);
		int cost = doc.getInteger("cost", 0);
		String img = doc.getString("img");
		int builtRound = doc.getInteger("builtRound", -1);
		return createCard(name, color, cost, img, builtRound);
	}

}
