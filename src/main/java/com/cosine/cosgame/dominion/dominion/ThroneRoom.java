package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;

public class ThroneRoom extends Card{
	public ThroneRoom() {
		super();
		this.name = "ThroneRoom";
		this.image = "/image/Dominion/cards/Dominion/ThroneRoom.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (!player.hasType("Action")) {
			return ask;
		}
		ask.setType(Ask.HANDCHOOSE);
		ask.setMsg("You may play an action card from your hand twice");
		ask.setUpper(1);
		ask.setLower(0);
		ask.setRestriction(Ask.ACTION);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = a;
		
		if (ask.getResLevel() == 0) {
			if (ask.getSelectedCards().size() == 0) {
				ask = new Ask();
				return ask;
			}
			ask.setType(Ask.THRONE);
			ask.setThronedCard(ask.getSelectedCards().get(0));
			ask.setThronedAskType(Ask.PLAY);
			ask.addResLevel();
			player.moveToPlay(ask.getThronedCard());
		}
		String cardName = ask.getThronedCard();
		CardFactory factory = new CardFactory();
		Card card = factory.createCard(cardName);
		card.setBoard(board);
		card.setPlayer(player);
		while (ask.getResLevel()<3) {
			Ask thronedAsk;
			if (ask.getThronedAskType() == Ask.PLAY) {
				thronedAsk = card.play();
				/*
				if (ask.getResLevel() == 1) {
					log(player.getName() + " plays " + card.getName(), 1);
				} else if (ask.getResLevel() == 2) {
					log(player.getName() + " plays " + card.getName() + " again", 1);
				}
				*/
				ask.setThronedAskType(Ask.RESPONSE);
			} else {
				thronedAsk = card.response(ask.getThronedAsk());
			}
			ask.setThronedAsk(thronedAsk);
			if (thronedAsk.getType() == Ask.NONE) {
				if (thronedAsk.getSubType() == Ask.ATTACK) {
					board.attackHandle(player, cardName);
				}
				ask.addResLevel();
				ask.setThronedAskType(Ask.PLAY);
				if (ask.getResLevel() == 3) {
					ask = new Ask();
					break;
				}
			} else {
				break;
			}
		}
		return ask;
	}
}
