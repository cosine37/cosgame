package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Buff;
import com.cosine.cosgame.dominion.Card;

public class Lighthouse extends Card{
	public Lighthouse() {
		super();
		this.name = "Lighthouse";
		this.image = "/image/Dominion/cards/Seaside/Lighthouse.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_DURATION] = true;
		this.price = 2;
		this.action = 1;
		this.coin = 1;
		this.nt = 1;
	}
	
	public Ask duration() {
		Ask ask = super.duration();
		player.addCoin(1);
		log(player.getName() + " gets + $1 (from Lighthouse)", 1);
		return ask;
	}
	
	public int inPlay() {
		return Buff.UNAFFACTEDBYATTACK;
	}
	
}
