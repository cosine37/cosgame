package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Seaside extends Expansion{
	Pile caravanPile;
	Pile fishingVillagePile;
	Pile merchantShipPile;
	Pile wharfPile;
	Pile tacticianPile;
	
	public Seaside() {
		super();
		
		caravanPile = new Pile(Caravan.class, 10);
		fishingVillagePile = new Pile(FishingVillage.class, 10);
		merchantShipPile = new Pile(MerchantShip.class, 10);
		wharfPile = new Pile(Wharf.class, 10);
		tacticianPile = new Pile(Tactician.class, 10);
		
		piles.add(caravanPile);
		piles.add(fishingVillagePile);
		piles.add(merchantShipPile);
		piles.add(wharfPile);
		piles.add(tacticianPile);
	}
}
