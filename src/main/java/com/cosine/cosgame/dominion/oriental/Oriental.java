package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Oriental extends Expansion {
	Pile pendantForWinePile;
	Pile armyDrummerPile;
	Pile exilePile;
	Pile templeFairPile;
	Pile lanternExhibitionPile;
	Pile corruptedOfficialPile;
	Pile dragonBoatPile;
	Pile tombSweepingPile;
	
	public Oriental() {
		super();
		
		pendantForWinePile = new Pile(PendantForWine.class, 10);
		armyDrummerPile = new Pile(ArmyDrummer.class, 10);
		exilePile = new Pile(Exile.class, 10);
		templeFairPile = new Pile(TempleFair.class, 10);
		lanternExhibitionPile = new Pile(LanternExhibition.class, 10);
		corruptedOfficialPile = new Pile(CorruptedOfficial.class, 20);
		dragonBoatPile = new Pile(DragonBoat.class, 10);
		tombSweepingPile = new Pile(TombSweeping.class, 10);
		
		piles.add(pendantForWinePile);
		piles.add(armyDrummerPile);
		piles.add(exilePile);
		piles.add(templeFairPile);
		piles.add(lanternExhibitionPile);
		piles.add(corruptedOfficialPile);
		piles.add(dragonBoatPile);
		piles.add(tombSweepingPile);
		
	}

}
