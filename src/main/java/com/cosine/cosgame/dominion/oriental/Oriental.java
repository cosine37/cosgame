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
	Pile executionerPile;
	Pile paperMakerPile;
	Pile quadranglePile;
	Pile fieldReaperPile;
	Pile fireworkShowPile;
	Pile imperialExamPile;
	
	public Oriental() {
		super();
		
		expCardImage = "/image/Dominion/cards/Expansions/Oriental.png";
		name = "Oriental";
		
		pendantForWinePile = new Pile(PendantForWine.class, 10);
		armyDrummerPile = new Pile(ArmyDrummer.class, 10);
		exilePile = new Pile(Exile.class, 10);
		templeFairPile = new Pile(TempleFair.class, 10);
		lanternExhibitionPile = new Pile(LanternExhibition.class, 10);
		corruptedOfficialPile = new Pile(CorruptedOfficial.class, 20);
		dragonBoatPile = new Pile(DragonBoat.class, 10);
		tombSweepingPile = new Pile(TombSweeping.class, 10);
		executionerPile = new Pile(Executioner.class, 10);
		paperMakerPile = new Pile(PaperMaker.class, 10);
		quadranglePile = new Pile(Quadrangle.class, 12);
		fieldReaperPile = new Pile(FieldReaper.class, 10);
		fireworkShowPile = new Pile(FireworkShow.class, 10);
		imperialExamPile = new Pile(ImperialExam.class, 10);
		
		piles.add(pendantForWinePile);
		piles.add(armyDrummerPile);
		piles.add(exilePile);
		piles.add(templeFairPile);
		piles.add(lanternExhibitionPile);
		piles.add(corruptedOfficialPile);
		piles.add(dragonBoatPile);
		piles.add(tombSweepingPile);
		piles.add(executionerPile);
		piles.add(paperMakerPile);
		piles.add(quadranglePile);
		piles.add(fieldReaperPile);
		piles.add(fireworkShowPile);
		piles.add(imperialExamPile);
		
	}

}
