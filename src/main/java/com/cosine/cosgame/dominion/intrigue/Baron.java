package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Baron extends Card{
	public Baron() {
		super();
		this.name = "Baron";
		this.image = "/image/Dominion/cards/Intrigue/Baron.png";
		this.types[INDEX_ACTION] = true;
		this.buy = 1;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		int estateCount = 0;
		for (int i=0;i<player.getHand().size();i++) {
			if (player.getHand().get(i).getName().equals("Estate")) {
				estateCount = estateCount + 1;
			}
		}
		if (estateCount == 0) {
			board.gainToPlayerFromPile(player, board.getPileByTop("Estate"));
		} else {
			ask.setType(Ask.OPTION);
			ask.setMsg("You may discard an Estate for + $4");
			List<String> options = new ArrayList<>();
			options.add("Discard an Estate");
			options.add("Don't Discard");
			ask.setOptions(options);
			ask.setAns(-1);
		}
		
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = new Ask();
		if (a.getAns() == 0) {
			player.discard("Estate");
			player.addCoin(4);
			log(player.getName() + " discards an Estate for + $4", 1);
		} else if (a.getAns() == 1) {
			board.gainToPlayerFromPile(player, board.getPileByTop("Estate"));
			log(player.getName() + " gains an Estate", 1);
		}
		return ask;
	}
}
