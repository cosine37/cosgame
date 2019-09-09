package com.cosine.cosgame.dominion.seaside;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;
import com.cosine.cosgame.dominion.Player;

public class Smugglers extends Card{
	public Smugglers() {
		super();
		this.name = "Smugglers";
		this.image = "/image/Dominion/cards/Seaside/Smugglers.png";
		this.types[INDEX_ACTION] = true;
		this.price = 3;
	}
	
	public Ask play() {
		Ask ask = super.play();
		Player pp = board.prevPlayer(player);
		List<String> ls = pp.getGainedList();
		List<String> options = new ArrayList<>();
		CardFactory factory = new CardFactory();
		for (int i=0;i<ls.size();i++) {
			int price = factory.createCard(ls.get(i)).getPrice(player.getPriceReduce());
			if (price <= 6) {
				if (board.getPileByTop(ls.get(i)) != null) {
					options.add(ls.get(i));
				}
			}
		}
		if (options.size() == 1) {
			if (board.getPileByTop(options.get(0)) != null) {
				log(player.getName() + " gains a "+options.get(0), 1);
				board.gainToPlayerFromPile(player, board.getPileByTop(options.get(0)));
			}
		} else if (options.size() > 1) {
			ask.setType(Ask.OPTION);
			ask.setMsg("Gain one of the following cards");
			ask.setOptions(options);
			ask.setAns(-1);
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		String cardname = ask.getOptions().get(ask.getAns());
		if (board.getPileByTop(cardname) != null) {
			log(player.getName() + " gains a "+cardname, 1);
			board.gainToPlayerFromPile(player, board.getPileByTop(cardname));
		}
		ask = new Ask();
		return ask;
	}
	
}
