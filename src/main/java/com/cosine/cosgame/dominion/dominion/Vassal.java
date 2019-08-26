package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Vassal extends Card{
	public Vassal() {
		super();
		this.name = "Vassal";
		this.image = "/image/Dominion/cards/Dominion/Vassal.png";
		this.types[INDEX_ACTION] = true;
		this.price = 3;
		this.coin = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		Card card = player.getTop();
		log(player.getName() + " discards a " + card.getName(), 1);
		if (card == null) return ask;
		player.discardTop();
		
		if (card.isActionType()) {
			ask.setType(Ask.OPTION);
			String msg = "You may play " + card.getName();
			List<String> options = new ArrayList<String>();
			options.add("Play "+card.getName());
			options.add("Don't play");
			int ans = -1;
			ask.setMsg(msg);
			ask.setOptions(options);
			ask.setAns(ans);
		} else {
			
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (a.getAns() == 0) {
			Card card = player.getDiscard().get(player.getDiscard().size() - 1);
			if (card.isActionType()) {
				card.setPlayer(player);
				card.setBoard(player.getBoard());
				ask = card.play();
				log(player.getName() + " plays a " + card.getName(), 1);
			}
		} else {
			ask.setType(Ask.NONE);
		}
		return ask;
	}
}
