package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Mill extends Card{
	public Mill() {
		super();
		this.name = "Mill";
		this.image = "/image/Dominion/cards/Intrigue/Mill.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_VICTORY] = true;
		this.card = 1;
		this.action = 1;
		this.score = 1;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() >= 2) {
			ask.setType(Ask.SINGLEOPTION);
			List<String> options = new ArrayList<>();
			options.add("May discard 2 cards for $2");
			ask.setOptions(options);
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getResLevel() == 0) {
			if (ask.getAns() == 0) {
				ask.setMsg("Discard 2 cards");
				ask.setResLevel(1);
				ask.setType(Ask.HANDCHOOSE);
				ask.setUpper(2);
				ask.setLower(2);
			} else {
				ask = new Ask();
			}
		} else if (ask.getResLevel() == 1) {
			player.setBoard(board);
			log(player.getName() + " discards 2 cards for $2", 1);
			player.discard(ask.getSelectedCards().get(0));
			player.discard(ask.getSelectedCards().get(1));
			player.addCoin(2);
			ask = new Ask();
		}
		
		return ask;
	}
}
