package com.cosine.cosgame.dominion;

import java.util.List;

public class Trash {
	
	List<Card> trashedCards;
	
	public Trash() {}
	
	public void add(Card card) {
		trashedCards.add(card);
	}
}
