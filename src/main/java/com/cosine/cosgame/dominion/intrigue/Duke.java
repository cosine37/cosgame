package com.cosine.cosgame.dominion.intrigue;

import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Duke extends Card{
	public Duke() {
		super();
		this.name = "Duke";
		this.image = "/image/Dominion/cards/Intrigue/Duke.png";
		this.types[INDEX_VICTORY] = true;
		this.price = 5;
	}
	
	public int getScore(Player p) {
		int score = 0;
		for (int i=0;i<p.getAllCardsAsCards().size();i++) {
			if (p.getAllCardsAsCards().get(i).getName().equals("Duchy")) {
				score = score + 1;
			}
		}
		return score;
	}
}
