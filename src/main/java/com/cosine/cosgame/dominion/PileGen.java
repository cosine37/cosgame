package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

public class PileGen {
	List<Pile> piles;
	
	public PileGen() {
		piles = new ArrayList<Pile>();
	}
	
	public List<Pile> getPiles(){
		return piles;
	}
	
	public void add(List<Card> cards) {
		int i,j;
		boolean flag;
		for (i=0;i<cards.size();i++) {
			flag = true;
			for (j=0;j<piles.size();j++) {
				if (cards.get(i).getName().equals(piles.get(j).getName())) {
					flag = false;
					piles.get(j).add(cards.get(i));
					break;
				}
			}
			if (flag) {
				Pile pile = new Pile(cards.get(i).getName());
				pile.setImage(cards.get(i).getImage());
				pile.add(cards.get(i));
				piles.add(pile);
			}
		}
	}
	
}
