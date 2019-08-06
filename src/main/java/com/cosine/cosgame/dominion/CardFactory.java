package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.base.*;
import com.cosine.cosgame.dominion.dominion.*;
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
		
		if (cardname.equals("PendantForWine")) return new PendantForWine();
		if (cardname.equals("ArmyDrummer")) return new ArmyDrummer();
		
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
