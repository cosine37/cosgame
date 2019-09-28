package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class TreasureMap extends Card{
	public TreasureMap() {
		super();
		this.name = "Treasure Map";
		this.image = "/image/Dominion/cards/Seaside/TreasureMap.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		log(player.getName() + " trashes a Treasure Map", 1);
		Card c = player.getPlay().remove(player.getPlay().size() - 1);
		board.getTrash().add(c);
		if (player.has(name, "hand")) {
			log("Wow!  " + player.getName() + " trashes another Treasure Map", 1);
			player.trash(name, "hand");
			log(player.getName() + " gains 4 Golds on top of the deck.  4 Golds!  The guy is rich!", 1);
			for (int i=0;i<4;i++) {
				board.gainToPlayerFromPileToTopdeck(player, board.getPileByTop("Gold"));
			}
		}
		return ask;
	}
	
}
