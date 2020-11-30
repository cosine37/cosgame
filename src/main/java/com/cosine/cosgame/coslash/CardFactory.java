package com.cosine.cosgame.coslash;

import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.coslash.basecards.*;

public class CardFactory {
	public CardFactory() {
		
	}
	
	public static Card createCard(String name, int suit, int rank, List<Integer> targets) {
		Card c = new Card();
		if (name.equals("Slash")) c = new Slash(suit, rank);
		
		c.setTargets(targets);
		
		return c;
	}
	
	public static Card createCard(Document dc) {
		String name = dc.getString("name");
		int suit = dc.getInteger("suit");
		int rank = dc.getInteger("rank");
		List<Integer> targets = (List<Integer>) dc.get("targets");
		return createCard(name, suit, rank, targets);
	}
}
