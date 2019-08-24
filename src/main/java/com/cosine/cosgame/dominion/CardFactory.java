package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.base.*;
import com.cosine.cosgame.dominion.dominion.*;
import com.cosine.cosgame.dominion.intrigue.*;
import com.cosine.cosgame.dominion.oriental.*;

public class CardFactory {
	public CardFactory() {
		
	}
	
	public Card createCard(String cardname) {
		if (cardname.equals("Empty")) return new Empty();
		
		if (cardname.equals("Copper")) return new Copper();
		if (cardname.equals("Silver")) return new Silver();
		if (cardname.equals("Gold")) return new Gold();
		if (cardname.equals("Estate")) return new Estate();
		if (cardname.equals("Duchy")) return new Duchy();
		if (cardname.equals("Province")) return new Province();
		if (cardname.equals("Curse")) return new Curse();
		
		if (cardname.equals("Smithy")) return new Smithy();
		if (cardname.equals("Village")) return new Village();
		if (cardname.equals("Market")) return new Market();
		if (cardname.equals("Laboratory")) return new Laboratory();
		if (cardname.equals("Festival")) return new Festival();
		if (cardname.equals("Woodcutter")) return new Woodcutter();
		if (cardname.equals("Gardens")) return new Gardens();
		if (cardname.equals("Moneylender")) return new Moneylender();
		if (cardname.equals("Vassal")) return new Vassal();
		if (cardname.equals("Cellar")) return new Cellar();
		if (cardname.equals("Chapel")) return new Chapel();
		if (cardname.equals("Poacher")) return new Poacher();
		if (cardname.equals("ThroneRoom")) return new ThroneRoom();
		if (cardname.equals("Workshop")) return new Workshop();
		if (cardname.equals("Artisan")) return new Artisan();
		if (cardname.equals("Remodel")) return new Remodel();
		if (cardname.equals("Mine")) return new Mine();
		if (cardname.equals("Library")) return new Library();
		if (cardname.equals("Merchant")) return new Merchant();
		if (cardname.equals("CouncilRoom")) return new CouncilRoom();
		if (cardname.equals("Sentry")) return new Sentry();
		if (cardname.equals("Harbinger")) return new Harbinger();
		if (cardname.equals("Witch")) return new Witch();
		if (cardname.equals("Moat")) return new Moat();
		if (cardname.equals("Bureaucrat")) return new Bureaucrat();
		if (cardname.equals("Militia")) return new Militia();
		
		if (cardname.equals("ShantyTown")) return new ShantyTown();
		if (cardname.equals("Duke")) return new Duke();
		if (cardname.equals("Harem")) return new Harem();
		if (cardname.equals("Steward")) return new Steward();
		if (cardname.equals("Nobles")) return new Nobles();
		if (cardname.equals("Pawn")) return new Pawn();
		if (cardname.equals("TradingPost")) return new TradingPost();
		if (cardname.equals("Upgrade")) return new Upgrade();
		if (cardname.equals("Baron")) return new Baron();
		if (cardname.equals("Ironworks")) return new Ironworks();
		
		if (cardname.equals("PendantForWine")) return new PendantForWine();
		if (cardname.equals("ArmyDrummer")) return new ArmyDrummer();
		if (cardname.equals("Exile")) return new Exile();
		if (cardname.equals("TempleFair")) return new TempleFair();
		if (cardname.equals("LanternExhibition")) return new LanternExhibition();
		if (cardname.equals("CorruptedOfficial")) return new CorruptedOfficial();
		if (cardname.equals("DragonBoat")) return new DragonBoat();
		
		return null;
	}
	
	public Card createCard(String cardname, Player p) {
		Card card = createCard(cardname);
		if (card != null) {
			card.setPlayer(p);
			return card;
		} else {
			return null;
		}
		
	}
	
	public List<Card> createCards(String cardname, int n) {
		List<Card> cardlist = new ArrayList<Card>();
		for (int i=0;i<n;i++) {
			cardlist.add(createCard(cardname));
		}
		return cardlist;
	}
	
}
