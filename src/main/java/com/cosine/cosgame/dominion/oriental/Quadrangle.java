package com.cosine.cosgame.dominion.oriental;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Quadrangle extends Card{
	public Quadrangle() {
		super();
		this.name = "Quadrangle";
		this.image = "/image/Dominion/cards/Oriental/Quadrangle.png";
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
}
