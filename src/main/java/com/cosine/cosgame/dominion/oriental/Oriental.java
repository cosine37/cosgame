package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Oriental extends Expansion {
	Pile pendantForWinePile;
	
	public Oriental() {
		super();
		
		pendantForWinePile = new Pile(PendantForWine.class, 10);
		
		piles.add(pendantForWinePile);
		
		numPiles = 1;
	}

}
