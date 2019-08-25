package com.cosine.cosgame.dominion.intrigue;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.dominion.intrigue.*;

public class Intrigue extends Expansion {
	Pile shantyTownPile;
	Pile dukePile;
	Pile haremPile;
	Pile stewardPile;
	Pile noblesPile;
	Pile pawnPile;
	Pile tradingPostPile;
	Pile upgradePile;
	Pile baronPile;
	Pile ironworksPile;
	Pile courtyardPile;
	Pile patrolPile;
	Pile conspiratorPile;
	Pile courtierPile;
	Pile lurkerPile;
	
	public Intrigue() {
		super();
		
		name = "Intrigue";
		
		shantyTownPile = new Pile(ShantyTown.class, 10);
		dukePile = new Pile(Duke.class, 12);
		haremPile = new Pile(Harem.class, 12);
		stewardPile = new Pile(Steward.class, 10);
		noblesPile = new Pile(Nobles.class, 12);
		pawnPile = new Pile(Pawn.class, 10);
		tradingPostPile = new Pile(TradingPost.class, 10);
		upgradePile = new Pile(Upgrade.class, 10);
		baronPile = new Pile(Baron.class, 10);
		ironworksPile = new Pile(Ironworks.class, 10);
		courtyardPile = new Pile(Courtyard.class, 10);
		patrolPile = new Pile(Patrol.class, 10);
		conspiratorPile = new Pile(Conspirator.class, 10);
		courtierPile = new Pile(Courtier.class, 10);
		lurkerPile = new Pile(Lurker.class, 10);
		
		piles.add(shantyTownPile);
		piles.add(dukePile);
		piles.add(haremPile);
		piles.add(stewardPile);
		piles.add(noblesPile);
		piles.add(pawnPile);
		piles.add(tradingPostPile);
		piles.add(upgradePile);
		piles.add(baronPile);
		piles.add(ironworksPile);
		piles.add(courtyardPile);
		piles.add(patrolPile);
		piles.add(conspiratorPile);
		piles.add(courtierPile);
		piles.add(lurkerPile);
		
	}

}
