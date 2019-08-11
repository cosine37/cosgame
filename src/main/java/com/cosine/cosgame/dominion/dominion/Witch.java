package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Witch extends Card{
	public Witch() {
		super();
		this.name = "Witch";
		this.image = "/image/Dominion/cards/Dominion/Witch.png";
		this.types[INDEX_ACTION] = true;
		this.card = 2;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		Ask attackAsk = new Ask();
		int i;
		Player p;
		for (i=0;i<board.getPlayers().size();i++) {
			p = board.getPlayers().get(i);
			if (p.getName().equals(player.getName())) {
				
			} else {
				if (p.hasAttackBlock()) {
					if (p.getIsBot()) {
						
					} else {
						
					}
				} else {
					board.gainToPlayerFromPile(p, board.getPileByTop("Curse"));
				}
			}
		}
		return ask;
	}

	public Ask response(Ask a) {
		Ask ask = a;
		return ask;
	}
}
