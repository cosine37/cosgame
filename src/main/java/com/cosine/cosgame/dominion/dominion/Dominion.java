package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Dominion extends Expansion{
	Pile smithyPile;
	Pile villagePile;
	
	public Dominion() {
		super();
		
		smithyPile = new Pile(Smithy.class, 10);
		villagePile = new Pile(Village.class, 10);
		
		piles.add(smithyPile);
		piles.add(villagePile);
		
		numPiles = 2;
	}
}
