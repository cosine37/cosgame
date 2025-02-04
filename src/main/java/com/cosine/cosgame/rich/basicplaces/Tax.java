package com.cosine.cosgame.rich.basicplaces;

import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class Tax extends Place{
	int rate;
	
	public Tax(int id, String name, int type) {
		super(id, name, type);
	}
	
	public void stepOn(Player p) {
		p.loseMoney(rate);
	}

	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}

}
