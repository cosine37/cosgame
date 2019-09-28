package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class CorruptedOfficial extends Card{
	public CorruptedOfficial() {
		super();
		this.name = "Corrupted Official";
		this.image = "/image/Dominion/cards/Oriental/CorruptedOfficial.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_CURSED] = true;
		this.score = -2;
		this.price = 4;
		
		this.safe = false;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (board.getPileByTop(name) != null) {
			if (player.getDeck().size() == 0) {
				player.shuffle();
			}
			if (player.getDeck().size() == 0) {
				if (player.getDiscard().size() > 0) {
					player.shuffle();
				}
			}
			if (player.getDeck().size() == 0) {
				
			} else {
				Card card = player.getDeck().remove(0);
				player.getSeclusion().add(card);
				log(player.getName() + " puts " + card.getName() + " to Seclusion mat", 1);
			}
			player.addAction(1);
			player.addBuy(1);
			player.addCoin(4);
			log(player.getName() + " gets +1 Action, +1 Buy, + $4", 1);
			log(player.getName() + " gains a CorruptedOfficial", 1);
			board.gainToPlayerFromPile(player, board.getPileByTop(name));
		}
		return ask;
	}
}
