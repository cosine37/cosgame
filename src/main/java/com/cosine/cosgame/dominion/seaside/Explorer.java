package com.cosine.cosgame.dominion.seaside;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Explorer extends Card{
	public Explorer() {
		super();
		this.name = "Explorer";
		this.image = "/image/Dominion/cards/Seaside/Explorer.png";
		this.types[INDEX_ACTION] = true;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.has("Province","hand")) {
			ask.setType(Ask.OPTION);
			ask.setMsg("You may reveal Province to gain a Gold to your hand");
			List<String> options = new ArrayList<>();
			options.add("Reveal Province");
			options.add("Don't reveal");
			ask.setOptions(options);
			ask.setAns(-1);
		} else {
			log(player.getName() + " gains a Silver to hand",1);
			board.gainToPlayerFromPileToHand(player, board.getPileByTop("Silver"));
			
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getAns() == 0) {
			log(player.getName() + " reveals a Province",1);
			log(player.getName() + " gains a Gold to hand",1);
			board.gainToPlayerFromPileToHand(player, board.getPileByTop("Gold"));
		} else {
			log(player.getName() + " gains a Silver to hand",1);
			board.gainToPlayerFromPileToHand(player, board.getPileByTop("Silver"));
		}
		ask = new Ask();
		return ask;
	}
	
}
