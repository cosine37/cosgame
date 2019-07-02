package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.base.Copper;
import com.cosine.cosgame.dominion.base.Curse;
import com.cosine.cosgame.dominion.base.Duchy;
import com.cosine.cosgame.dominion.base.Estate;
import com.cosine.cosgame.dominion.base.Gold;
import com.cosine.cosgame.dominion.base.Province;
import com.cosine.cosgame.dominion.base.Silver;

public class Expansion {
	protected List<Pile> piles;
	protected int numPiles;
	
	public Expansion() {
		numPiles = 0;
		piles = new ArrayList<Pile>();
		
	}

	public List<Pile> getPiles() {
		return piles;
	}
	
	public void sort(int x) {
		
	}

}
