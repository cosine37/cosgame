package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Dominion extends Expansion{
	Pile smithyPile;
	Pile villagePile;
	Pile festivalPile;
	Pile laboratoryPile;
	Pile marketPile;
	Pile woodcutterPile;
	Pile gardensPile;
	
	public Dominion() {
		super();
		
		smithyPile = new Pile(Smithy.class, 10);
		villagePile = new Pile(Village.class, 10);
		festivalPile = new Pile(Festival.class, 10);
		laboratoryPile = new Pile(Laboratory.class, 10);
		marketPile = new Pile(Market.class, 10);
		woodcutterPile = new Pile(Woodcutter.class, 10);
		gardensPile = new Pile(Gardens.class, 12);
		
		piles.add(villagePile);
		piles.add(smithyPile);
		piles.add(festivalPile);
		piles.add(laboratoryPile);
		piles.add(marketPile);
		piles.add(woodcutterPile);
		piles.add(gardensPile);
		
		numPiles = 7;
	}
}
