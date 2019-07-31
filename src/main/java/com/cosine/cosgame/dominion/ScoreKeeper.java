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
		allCards = p.getDiscard();
	}
	
	public int getScore() {
		int ans = 0;
		int i;
		for (i=0;i<allCards.size();i++) {
			if (allCards.get(i).isVictory() || allCards.get(i).isCursed()) {
				allCards.get(i).setPlayer(p);
				int score = allCards.get(i).getScore();
				ans = ans + score;
			}
		}
		return ans;
	}

}
