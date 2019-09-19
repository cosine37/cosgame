package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

public class ScoreKeeper {
	Player p;
	List<Card> allCards;
	
	public ScoreKeeper() {
		
	}
	
	public ScoreKeeper(Player p) {
		super();
		this.p = p;
		allCards = p.getAllCardsAsCards();
	}
	
	public int getScore() {
		int ans = p.getVp();
		int i;
		for (i=0;i<allCards.size();i++) {
			if (allCards.get(i).isVictory() || allCards.get(i).isCursed()) {
				int score = allCards.get(i).getScore(p);
				ans = ans + score;
			}
		}
		return ans;
	}

}
