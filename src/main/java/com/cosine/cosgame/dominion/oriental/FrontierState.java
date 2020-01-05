package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class FrontierState extends Card {
	public FrontierState() {
		super();
		this.name = "Frontier State";
		this.image = "/image/Dominion/cards/Oriental/FrontierState.png";
		this.types[INDEX_VICTORY] = true;
		this.types[INDEX_REACTION] = true;
		this.price = 4;
		this.score = 2;
	}
	
	public Ask onDiscard(Player p) {
		Ask ask = super.onDiscard(p);
		p.addMemorial(2);
		setBoard(p.getBoard());
		log(p.getName() + " reveals the discarded "+name, 0);
		log(p.getName() + " receives 2 Memorial tokens", 0);
		return ask;
	}
	
	public Ask onGain(Player p) {
		if (p.getPhase() != Player.OFFTURN) {
			p.addMemorial(1);
			log(p.getName() + " receives a Memorial token", 1);
		}
		Ask ask = new Ask();
		return ask;
	}
}
