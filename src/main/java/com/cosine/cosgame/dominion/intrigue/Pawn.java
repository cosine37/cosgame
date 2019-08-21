package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Pawn extends Card{
	public Pawn() {
		super();
		this.name = "Pawn";
		this.image = "/image/Dominion/cards/Intrigue/Pawn.png";
		this.types[INDEX_ACTION] = true;
		this.price = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		String msg = "Choose one:";
		List<String> options = new ArrayList<String>();
		options.add("+1 Card, +1 Action");
		options.add("+1 Card, +1 Buy");
		options.add("+1 Card, + $1");
		options.add("+1 Action, +1 Buy");
		options.add("+1 Action, + $1");
		options.add("+1 Buy, + $1");
		int ans = -1;
		ask.setType(Ask.OPTION);
		ask.setMsg(msg);
		ask.setOptions(options);
		ask.setAns(ans);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = new Ask();
		if (a.getAns() == 0) {
			player.draw(1);
			player.addAction(1);
			log(player.getName() + " draws 1 Card and gets 1 Action", 1);
		} else if (a.getAns() == 1) {
			player.draw(1);
			player.addBuy(1);
			log(player.getName() + " draws 1 Card and gets 1 Buy", 1);
		} else if (a.getAns() == 2) {
			player.draw(1);
			player.addCoin(1);
			log(player.getName() + " draws 1 Card and gets $1", 1);
		} else if (a.getAns() == 3) {
			player.addAction(1);
			player.addBuy(1);
			log(player.getName() + " gets 1 Action and gets 1 Buy", 1);
		} else if (a.getAns() == 4) {
			player.addAction(1);
			player.addCoin(1);
			log(player.getName() + " gets 1 Action and gets $1", 1);
		} else if (a.getAns() == 5) {
			player.addBuy(1);
			player.addCoin(1);
			log(player.getName() + " gets 1 Buy and gets $1", 1);
		}
		return ask;
	}
	
}
