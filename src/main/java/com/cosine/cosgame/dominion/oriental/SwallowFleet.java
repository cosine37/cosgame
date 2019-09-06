package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Buff;
import com.cosine.cosgame.dominion.Card;

public class SwallowFleet extends Card{
	public SwallowFleet() {
		super();
		this.name = "SwallowFleet";
		this.image = "/image/Dominion/cards/Oriental/SwallowFleet.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_DURATION] = true;
		this.price = 5;
		this.nt = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setCardName(name);
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(2);
		ask.setLower(0);
		ask.setMsg("Trash up to 2 cards");
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getType() == Ask.HANDCHOOSE) {
			player.setBoard(board);
			player.trash(ask.getSelectedCards(), "hand");
			if (ask.getSelectedCards().size() == 1) {
				log(player.getName() + " trashes a card", 1);
			} else if (ask.getSelectedCards().size() == 2) {
				log(player.getName() + " trashes 2 cards", 1);
			}
		} else if (ask.getType() == Ask.GAIN) {
			String gainedCardName = ask.getSelectedCards().get(0);
			log(player.getName() + " gains a " + gainedCardName + " to hand (from Swallow Fleet)", 1);
			board.gainToPlayerFromPileToHand(player, board.getPileByTop(gainedCardName));
		}
		ask = new Ask();
		return ask;
	}
	
	public Ask duration() {
		Ask ask = super.duration();
		ask.setMsg("Gain a card costing up to $6 to your hand");
		ask.setType(Ask.GAIN);
		ask.setUpper(6);
		ask.setLower(0);
		return ask;
	}
	
	public int inPlayOther() {
		return Buff.PLUSONEOTHERSTART;
	}
}
