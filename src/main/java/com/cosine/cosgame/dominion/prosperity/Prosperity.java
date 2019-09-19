package com.cosine.cosgame.dominion.prosperity;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Prosperity extends Expansion {
	Pile monumentPile;
	
	public Prosperity(){
		super();
		
		expCardImage = "/image/Dominion/cards/Expansions/Prosperity.png";
		name = "Prosperity";
		
		monumentPile = new Pile(Monument.class, 10);
		
		piles.add(monumentPile);
		
	}
}
