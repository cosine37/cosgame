package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;

public class Upgrade extends Card{
	public Upgrade() {
		super();
		this.name = "Upgrade";
		this.image = "/image/Dominion/cards/Intrigue/Upgrade.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 1;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(1);
		ask.setLower(1);
		ask.setMsg("Trash a card");
		return ask;
	}
	
	public Ask response(Ask a) {
		if (a.getResLevel() == 0) {
			String cardname = a.getSelectedCards().get(0);
			CardFactory factory = new CardFactory();
			int price = factory.createCard(cardname).getPrice() + 1;
			player.setBoard(board);
			player.trash(a.getSelectedCards(), "hand");
			log(player.getName() + " trashes a card", 1);
			List<String> cns = board.getCardnamesWithPrice(price);
			if (cns.size() == 0) {
				Ask ask = new Ask();
				return ask;
			} else if (cns.size() == 1) {
				board.gainToPlayerFromPile(player, board.getPileByTop(cns.get(0)));
				Ask ask = new Ask();
				return ask;
			} else {
				Ask ask = new Ask();
				ask.setCardName(name);
				ask.setMsg("Gain a card costing exactly $" + price);
				ask.setType(Ask.GAIN);
				ask.setUpper(price);
				ask.setLower(price);
				ask.setResLevel(1);
				return ask;
			}
		} else if (a.getResLevel() == 1) {
			String gainedCardName = a.getSelectedCards().get(0);
			log(player.getName() + " gains a " + gainedCardName, 1);
			board.gainToPlayerFromPile(player, board.getPileByTop(gainedCardName));
			Ask ask = new Ask();
			return ask;
		} else {
			Ask ask = new Ask();
			return ask;
		}
		
		
	}
}
