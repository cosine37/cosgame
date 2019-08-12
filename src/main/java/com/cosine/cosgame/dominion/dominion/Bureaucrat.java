package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Bureaucrat extends Card{
	public Bureaucrat() {
		super();
		this.name = "Bureaucrat";
		this.image = "/image/Dominion/cards/Dominion/Bureaucrat.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_ATTACK] = true;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (board.getPileByTop("Silver") == null) {
			
		} else {
			board.gainToPlayerFromPileToTopdeck(player, board.getPileByTop("Silver"));
		}
		return ask;
	}
	
	public Ask attack() {
		Ask ask = super.attack();
		int count = 0;
		for (int i=0;i<player.getHand().size();i++) {
			if (player.getHand().get(i).isVictory()) {
				count++;
			}
		}
		if (count == 0) {
			return ask;
		} else if (count == 1) {
			String cardName = "";
			for (int i=0;i<player.getHand().size();i++) {
				if (player.getHand().get(i).isVictory()) {
					cardName = player.getHand().get(i).getName();
					break;
				}
			}
			player.topDeck(cardName);
			return ask;
		}
		ask.setCardName(name);
		ask.setType(Ask.HANDCHOOSE);
		ask.setSubType(Ask.ATTACK);
		ask.setRestriction(Ask.VICTORY);
		ask.setUpper(1);
		ask.setLower(1);
		return ask;
	}
	
	public Ask response(Ask a) {
		if (a.getSubType() == Ask.ATTACK) {
			player.topDeck(a.getSelectedCards().get(0));
		}
		Ask ask = new Ask();
		return ask;
	}
	
}
