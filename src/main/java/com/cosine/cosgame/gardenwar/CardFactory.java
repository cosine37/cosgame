package com.cosine.cosgame.gardenwar;

import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.gardenwar.base.*;

public class CardFactory {
	public static Card makeCard(String img) {
		Card card = new Card();
		//TODO: build cards here
		if (img.contentEquals("puffShroom")) {
			card = new PuffShroom();
		} else if (img.contentEquals("sunShroom")) {
			card = new SunShroom();
		}
		return card;
	}
	
	public static Card makeCard(Document doc) {
		String img = doc.getString("img");
		int id = doc.getInteger("id", -1);
		List<Integer> extraBits = (List<Integer>) doc.get("extraBits");
		Card card = makeCard(img);
		card.setId(id);
		card.setExtraBits(extraBits);
		return card;
	}
}
