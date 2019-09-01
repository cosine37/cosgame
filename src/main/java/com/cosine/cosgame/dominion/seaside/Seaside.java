package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Seaside extends Expansion{
	Pile caravanPile;
	Pile fishingVillagePile;
	Pile merchantShipPile;
	Pile wharfPile;
	Pile tacticianPile;
	Pile islandPile;
	Pile warehousePile;
	Pile treasureMapPile;
	Pile explorerPile;
	Pile nativeVillagePile;
	Pile pearlDiverPile;
	Pile bazaarPile;
	Pile lighthousePile;
	
	public Seaside() {
		super();
		
		name = "Seaside";
		
		caravanPile = new Pile(Caravan.class, 10);
		fishingVillagePile = new Pile(FishingVillage.class, 10);
		merchantShipPile = new Pile(MerchantShip.class, 10);
		wharfPile = new Pile(Wharf.class, 10);
		tacticianPile = new Pile(Tactician.class, 10);
		islandPile = new Pile(Island.class, 12);
		warehousePile = new Pile(Warehouse.class, 10);
		treasureMapPile = new Pile(TreasureMap.class, 10);
		explorerPile = new Pile(Explorer.class, 10);
		nativeVillagePile = new Pile(NativeVillage.class, 10);
		pearlDiverPile = new Pile(PearlDiver.class, 10);
		bazaarPile = new Pile(Bazaar.class, 10);
		lighthousePile = new Pile(Lighthouse.class, 10);
		
		piles.add(caravanPile);
		piles.add(fishingVillagePile);
		piles.add(merchantShipPile);
		piles.add(wharfPile);
		piles.add(tacticianPile);
		piles.add(islandPile);
		piles.add(warehousePile);
		piles.add(treasureMapPile);
		piles.add(explorerPile);
		piles.add(nativeVillagePile);
		piles.add(pearlDiverPile);
		piles.add(bazaarPile);
		piles.add(lighthousePile);
	}
}
