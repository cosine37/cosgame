package com.cosine.cosgame.dominion.entertainment;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Entertainments extends Expansion{
	Pile catanIslandPile;
	
	public Entertainments() {
		super();
		
		expCardImage = "/image/Dominion/cards/Expansions/Entertainments.png";
		name = "Entertainment";
		
		catanIslandPile = new Pile(CatanIsland.class, 12);
		
		piles.add(catanIslandPile);
	}

}
