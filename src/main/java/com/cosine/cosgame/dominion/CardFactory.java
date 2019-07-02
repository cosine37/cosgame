package com.cosine.cosgame.dominion;

import com.cosine.cosgame.dominion.base.*;
import com.cosine.cosgame.dominion.dominion.*;

public class CardFactory {
	public CardFactory() {
		
	}
	
	public Card createCard(String cardname) {
		if (cardname.equals("Copper")) return new Copper();
		if (cardname.equals("Silver")) return new Silver();
		if (cardname.equals("Gold")) return new Gold();
		if (cardname.equals("Estate")) return new Estate();
		if (cardname.equals("Duchy")) return new Duchy();
		if (cardname.equals("Province")) return new Province();
		if (cardname.equals("Curse")) return new Curse();
		
		if (cardname.equals("Smithy")) return new Smithy();
		if (cardname.equals("Village")) return new Village();
		return null;
	}
}
