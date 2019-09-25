package com.cosine.cosgame.dominion.prosperity;

import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;

public class Forge extends Card{
	public Forge() {
		super();
		this.name = "Forge";
		this.image = "/image/Dominion/cards/Prosperity/Forge.png";
		this.types[INDEX_ACTION] = true;
		this.price = 7;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() == 0) {
			List<String> cns = board.getCardnamesWithPrice(0, player.getPriceReduce());
			if (cns.size() == 0) {
				
			} else if (cns.size() == 1) {
				log(player.getName()+" gains a "+cns.get(0), 1);
				board.gainToPlayerFromPile(player, board.getPileByTop(cns.get(0)));
				
			} else {
				ask.setType(Ask.GAIN);
				ask.setMsg("Gain a card costing exactly 0");
				ask.setUpper(0);
				ask.setLower(0);
				ask.setResLevel(1);
			}
			
			
		} else {
			ask.setType(Ask.HANDCHOOSE);
			ask.setUpper(10000);
			ask.setLower(0);
		}
		
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getResLevel() == 0) {
			int total = 0;
			List<String> cards = ask.getSelectedCards();
			player.trash(a.getSelectedCards(), "hand");
			if (cards.size() == 1) {
				log(player.getName() + " trashes a card", 1);
			} else if (cards.size() == 0) {
				
			} else {
				log(player.getName() + " trashes " + cards.size() + " cards", 1);
			}
			
			CardFactory factory = new CardFactory();
			for (int i=0;i<cards.size();i++) {
				Card c = factory.createCard(cards.get(i));
				total = total + c.getPrice(player.getPriceReduce());
			}
			List<String> cns = board.getCardnamesWithPrice(total, player.getPriceReduce());
			if (cns.size() == 0) {
				ask = new Ask();
			} else if (cns.size() == 1) {
				log(player.getName()+" gains a "+cns.get(0), 1);
				board.gainToPlayerFromPile(player, board.getPileByTop(cns.get(0)));
				ask = new Ask();
			} else {
				ask.setType(Ask.GAIN);
				ask.setMsg("Gain a card costing exactly " + total);
				ask.setUpper(total);
				ask.setLower(total);
				ask.setResLevel(1);
			}
		} else if (ask.getResLevel() == 1) {
			String gainedCardName = a.getSelectedCards().get(0);
			log(player.getName() + " gains a " + gainedCardName, 1);
			board.gainToPlayerFromPile(player, board.getPileByTop(gainedCardName));
			ask = new Ask();
		} else {
			ask = new Ask();
		}
		return ask;
	}
	
}
