package com.cosine.cosgame.coslash;

import org.bson.Document;

import com.cosine.cosgame.coslash.basecards.*;

public class CardFactory {
	public CardFactory() {
		
	}
	
	public static Card createCard(String name, int suit, int rank) {
		if (name.equals("Slash")) return new Slash(suit, rank);
		
		return null;
	}
	
	public static Card createCard(Document dc) {
		String name = dc.getString("name");
		int suit = dc.getInteger("suit");
		int rank = dc.getInteger("rank");
		return createCard(name, suit, rank);
	}
}
