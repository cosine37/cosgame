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
	Pile havenPile;
	Pile smugglersPile;
	Pile treasuryPile;
	Pile salvagerPile;
	Pile embargoPile;
	
	public Seaside() {
		super();
		
		expCardImage = "/image/Dominion/cards/Expansions/Seaside.png";
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
		havenPile = new Pile(Haven.class, 10);
		smugglersPile = new Pile(Smugglers.class, 10);
		treasuryPile = new Pile(Treasury.class, 10);
		salvagerPile = new Pile(Salvager.class, 10);
		embargoPile = new Pile(Embargo.class, 10);
		
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
		piles.add(havenPile);
		piles.add(smugglersPile);
		piles.add(treasuryPile);
		piles.add(salvagerPile);
		piles.add(embargoPile);
	}
}
