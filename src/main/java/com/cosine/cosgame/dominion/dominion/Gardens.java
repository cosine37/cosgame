package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Gardens extends Card{
	public Gardens() {
		super();
		this.name = "Gardens";
		this.image = "/image/Dominion/cards/Dominion/Gardens.png";
		this.types[INDEX_VICTORY] = true;
		this.price = 4;
	}
	
	public int getScore(Player p) {
		int score = p.getAllCardsAsCards().size() / 10;
		//System.out.println("score="+score);
		return score;
	}
}
