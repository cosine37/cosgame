package com.cosine.cosgame.dominion.prosperity;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Expand extends Card{
	public Expand() {
		super();
		this.name = "Expand";
		this.image = "/image/Dominion/cards/Prosperity/Expand.png";
		this.types[INDEX_ACTION] = true;
		this.price = 7;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() == 0) {
			return ask;
		}
		ask.setType(Ask.HANDCHOOSE);
		ask.setMsg("Trash a card from your hand");
		ask.setUpper(1);
		ask.setLower(1);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getResLevel() == 0) {
			String cardName = ask.getSelectedCards().get(0);
			int price = 0;
			for (int i=player.getHand().size()-1;i>=0;i--) {
				if (player.getHand().get(i).getName().equals(cardName)) {
					price = player.getHand().get(i).getPrice(player.getPriceReduce());
					player.setBoard(board);
					player.trash(i);
					log(player.getName() + " trashes a " + cardName, 1);
					break;
				}
			}
			ask = new Ask();
			ask.setCardName(name);
			ask.setType(Ask.GAIN);
			ask.setResLevel(1);
			ask.setLower(0);
			ask.setUpper(price + 3);
			ask.setMsg("Gain a card costing up to "+Integer.toString(ask.getUpper()));
		} else if (ask.getResLevel() == 1) {
			String gainedCardName = ask.getSelectedCards().get(0);
			log(player.getName() + " gains a " + gainedCardName, 1);
			board.gainToPlayerFromPile(player, board.getPileByTop(gainedCardName));
			ask = new Ask();
			ask.setCardName(name);
			ask.setResLevel(2);
		} else {
			ask = new Ask();
		}
		return ask;
	}
}
