package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Mine extends Card{
	public Mine() {
		super();
		this.name = "Mine";
		this.image = "/image/Dominion/cards/Dominion/Mine.png";
		this.types[INDEX_ACTION] = true;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() == 0) {
			return ask;
		}
		ask.setType(Ask.HANDCHOOSE);
		ask.setMsg("Trash a Treasure card from your hand");
		ask.setRestriction(Ask.TREASURE);
		ask.setUpper(1);
		ask.setLower(1);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getResLevel() == 0) {
			String cardName = ask.getSelectedCards().get(0);
			int price = 0;
			for (int i=player.getHand().size();i>=0;i--) {
				if (player.getHand().get(i).getName().equals(cardName)) {
					price = player.getHand().get(i).getPrice();
					player.setBoard(board);
					player.trash(i);
				}
			}
			ask = new Ask();
			ask.setCardName(name);
			ask.setType(Ask.GAIN);
			ask.setResLevel(1);
			ask.setLower(0);
			ask.setRestriction(Ask.TREASURE);
			ask.setUpper(price + 3);
			ask.setMsg("Gain a Treasure card costing up to "+Integer.toString(ask.getUpper())+" to your hand");
		} else if (ask.getResLevel() == 1) {
			String gainedCardName = ask.getSelectedCards().get(0);
			board.gainToPlayerFromPileToHand(player, board.getPileByTop(gainedCardName));
			ask = new Ask();
			ask.setCardName(name);
			ask.setResLevel(2);
		} else {
			ask = new Ask();
		}
		return ask;
	}
}
