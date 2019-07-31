package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class ArmyDrummer extends Card{
	public ArmyDrummer() {
		super();
		this.name = "ArmyDrummer";
		this.image = "/image/Dominion/cards/Oriental/ArmyDrummer.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
		this.action = 1;
	}
	
	public Ask play() {
		vanilla();
		if (player.getPlayed(name) == 0) {
			player.draw(2);
			player.addAction(1);
		} else if (player.getPlayed(name) == 1) {
			player.draw(1);
		}
		player.addPlayed(name);
		Ask ask = new Ask();
		return ask;
	}
	
	public Ask onGain(Player p) {
		if (p.getPhase() != Player.OFFTURN) {
			p.addCoin(1);
		}
		Ask ask = new Ask();
		return ask;
	}
}
