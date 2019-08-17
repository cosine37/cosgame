package com.cosine.cosgame.dominion.intrigue;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.dominion.intrigue.*;

public class Intrigue extends Expansion {
	Pile shantyTownPile;
	Pile dukePile;
	Pile haremPile;
	Pile stewardPile;
	
	public Intrigue() {
		super();
		
		shantyTownPile = new Pile(ShantyTown.class, 10);
		dukePile = new Pile(Duke.class, 12);
		haremPile = new Pile(Harem.class, 12);
		stewardPile = new Pile(Steward.class, 10);
		
		piles.add(shantyTownPile);
		piles.add(dukePile);
		piles.add(haremPile);
		piles.add(stewardPile);
		
		
	}

}
