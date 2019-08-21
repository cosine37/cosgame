package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Nobles extends Card{
	public Nobles() {
		super();
		this.name = "Nobles";
		this.image = "/image/Dominion/cards/Intrigue/Nobles.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_VICTORY] = true;
		this.score = 2;
		this.price = 6;
	}
	
	public Ask play() {
		Ask ask = super.play();
		String msg = "Choose one:";
		List<String> options = new ArrayList<String>();
		options.add("+3 Cards");
		options.add("+2 Actions");
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
			player.draw(3);
			log(player.getName() + " draws 3 Cards", 1);
			return ask;
		} else if (a.getAns() == 1) {
			player.addAction(2);
			log(player.getName() + " gets 2 Actions", 1);
			return ask;
		}
		return ask;
	}
	
}
