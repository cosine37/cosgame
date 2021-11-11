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
		} else if (img.contentEquals("peaShooter")) {
			card = new PeaShooter();
		} else if (img.contentEquals("sunflower")) {
			card = new Sunflower();
		} else if (img.contentEquals("repeater")) {
			card = new Repeater();
		} else if (img.contentEquals("twinSunflower")) {
			card = new TwinSunflower();
		} else if (img.contentEquals("magnetShroom")) {
			card = new MagnetShroom();
		} else if (img.contentEquals("cactus")) {
			card = new Cactus();
		} else if (img.contentEquals("starfruit")) {
			card = new Starfruit();
		} else if (img.contentEquals("fumeShroom")) {
			card = new FumeShroom();
		} else if (img.contentEquals("gloomShroom")) {
			card = new GloomShroom();
		} else if (img.contentEquals("goldMagnet")) {
			card = new GoldMagnet();
		} else if (img.contentEquals("cabbagePult")) {
			card = new CabbagePult();
		} else if (img.contentEquals("melonPult")) {
			card = new MelonPult();
		} else if (img.contentEquals("gravebuster")) {
			card = new Gravebuster();
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
