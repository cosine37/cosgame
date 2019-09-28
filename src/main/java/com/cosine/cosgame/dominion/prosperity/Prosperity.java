package com.cosine.cosgame.dominion.prosperity;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Prosperity extends Expansion {
	Pile monumentPile;
	Pile forgePile;
	Pile workersVillagePile;
	Pile cityPile;
	Pile expandPile;
	
	public Prosperity(){
		super();
		
		expCardImage = "/image/Dominion/cards/Expansions/Prosperity.png";
		name = "Prosperity";
		
		monumentPile = new Pile(Monument.class, 10);
		forgePile = new Pile(Forge.class, 10);
		workersVillagePile = new Pile(WorkersVillage.class, 10);
		cityPile = new Pile(City.class, 10);
		expandPile = new Pile(Expand.class, 10);
		
		piles.add(monumentPile);
		piles.add(forgePile);
		piles.add(workersVillagePile);
		piles.add(cityPile);
		piles.add(expandPile);
	}
}
