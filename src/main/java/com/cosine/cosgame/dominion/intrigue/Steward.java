package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Steward extends Card{
	public Steward() {
		super();
		this.name = "Steward";
		this.image = "/image/Dominion/cards/Intrigue/Steward.png";
		this.types[INDEX_ACTION] = true;
		this.price = 3;
	}
	
	public Ask play() {
		Ask ask = super.play();
		String msg = "Choose one:";
		List<String> options = new ArrayList<String>();
		options.add("+2 Cards");
		options.add("+ $2");
		options.add("Trash 2 cards");
		int ans = -1;
		ask.setType(Ask.OPTION);
		ask.setMsg(msg);
		ask.setOptions(options);
		ask.setAns(ans);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = new Ask();
		if (a.getType() == Ask.OPTION) {
			if (a.getAns() == 0) {
				player.draw(2);
				log(player.getName() + " draws 2 cards", 1);
				return ask;
			} else if (a.getAns() == 1) {
				player.addCoin(2);
				log(player.getName() + " gets +$2", 1);
				return ask;
			} else if (a.getAns() == 2) {
				ask.setCardName(name);
				ask.setType(Ask.HANDCHOOSE);
				ask.setUpper(2);
				ask.setLower(2);
				ask.setMsg("Trash 2 cards");
				return ask;
			} else {
				return ask;
			}
		} else if (a.getType() == Ask.HANDCHOOSE) {
			player.setBoard(board);
			player.trash(a.getSelectedCards(), "hand");
			log(player.getName() + " trashes 2 cards", 1);
			return ask;
		} else {
			return ask;
		}
	}
}
