package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Oriental extends Expansion {
	Pile pendantForWinePile;
	Pile armyDrummerPile;
	
	public Oriental() {
		super();
		
		pendantForWinePile = new Pile(PendantForWine.class, 10);
		armyDrummerPile = new Pile(ArmyDrummer.class, 10);
		
		piles.add(pendantForWinePile);
		piles.add(armyDrummerPile);
		
	}

}
