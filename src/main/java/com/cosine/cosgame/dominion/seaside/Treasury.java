package com.cosine.cosgame.dominion.seaside;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Treasury extends Card{
	public Treasury() {
		super();
		this.name = "Treasury";
		this.image = "/image/Dominion/cards/Seaside/Treasury.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 1;
		this.coin = 1;
		this.price = 5;
	}
	
	public Ask cleanup() {
		Ask ask = super.cleanup();
		if (player.getGained().hasBoughtVictory()) {
			
		} else {
			ask.setCardName(name);
			ask.setType(Ask.OPTION);
			ask.setMsg("You may put Treasury back on top");
			List<String> options = new ArrayList<>();
			options.add("Top Deck Treasury");
			options.add("Don't Topdeck");
			ask.setOptions(options);
			ask.setAns(-1);
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getAns() == 0) {
			for (int i=player.getPlay().size()-1;i>=0;i--) {
				if (player.getPlay().get(i).getName().equals(name)) {
					Card c = player.getPlay().remove(i);
					log(player.getName() + " puts a Treasury back on top", 1);
					player.getDeck().add(0,c);
				}
			}
		} else if (ask.getAns() == 1) {
			
		} else {
			
		}
		ask = new Ask();
		return ask;
	}
	
}
