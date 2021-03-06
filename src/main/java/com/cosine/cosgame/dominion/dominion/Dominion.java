package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Dominion extends Expansion{
	Pile smithyPile;
	Pile villagePile;
	Pile festivalPile;
	Pile laboratoryPile;
	Pile marketPile;
	Pile woodcutterPile;
	Pile gardensPile;
	Pile moneylenderPile;
	Pile vassalPile;
	Pile cellarPile;
	Pile chapelPile;
	Pile poacherPile;
	Pile throneRoomPile;
	Pile workshopPile;
	Pile artisanPile;
	Pile remodelPile;
	Pile minePile;
	Pile libraryPile;
	Pile merchantPile;
	Pile councilRoomPile;
	Pile sentryPile;
	Pile harbingerPile;
	Pile witchPile;
	Pile moatPile;
	Pile bureaucratPile;
	Pile militiaPile;
	
	public Dominion() {
		super();
		
		expCardImage = "/image/Dominion/cards/Expansions/Dominion.png";
		name = "Dominion";
		
		smithyPile = new Pile(Smithy.class, 10);
		villagePile = new Pile(Village.class, 10);
		festivalPile = new Pile(Festival.class, 10);
		laboratoryPile = new Pile(Laboratory.class, 10);
		marketPile = new Pile(Market.class, 10);
		woodcutterPile = new Pile(Woodcutter.class, 10);
		gardensPile = new Pile(Gardens.class, 12);
		moneylenderPile = new Pile(Moneylender.class, 10);
		vassalPile = new Pile(Vassal.class, 10);
		cellarPile = new Pile(Cellar.class, 10);
		chapelPile = new Pile(Chapel.class, 10);
		poacherPile = new Pile(Poacher.class, 10);
		throneRoomPile = new Pile(ThroneRoom.class, 10);
		workshopPile = new Pile(Workshop.class, 10);
		artisanPile = new Pile(Artisan.class, 10);
		remodelPile = new Pile(Remodel.class, 10);
		minePile = new Pile(Mine.class, 10);
		libraryPile = new Pile(Library.class, 10);
		merchantPile = new Pile(Merchant.class, 10);
		councilRoomPile = new Pile(CouncilRoom.class, 10);
		sentryPile = new Pile(Sentry.class, 10);
		harbingerPile = new Pile(Harbinger.class, 10);
		witchPile = new Pile(Witch.class, 10);
		moatPile = new Pile(Moat.class, 10);
		bureaucratPile = new Pile(Bureaucrat.class, 10);
		militiaPile = new Pile(Militia.class, 10);
		
		piles.add(villagePile);
		piles.add(smithyPile);
		piles.add(festivalPile);
		piles.add(laboratoryPile);
		piles.add(marketPile);
		piles.add(woodcutterPile);
		piles.add(gardensPile);
		piles.add(moneylenderPile);
		piles.add(vassalPile);
		piles.add(cellarPile);
		piles.add(chapelPile);
		piles.add(poacherPile);
		piles.add(throneRoomPile);
		piles.add(workshopPile);
		piles.add(artisanPile);
		piles.add(remodelPile);
		piles.add(minePile);
		piles.add(libraryPile);
		piles.add(merchantPile);
		piles.add(councilRoomPile);
		piles.add(sentryPile);
		piles.add(harbingerPile);
		piles.add(witchPile);
		piles.add(moatPile);
		piles.add(bureaucratPile);
		piles.add(militiaPile);
	}
	
}
