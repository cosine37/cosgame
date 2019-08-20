package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Sentry extends Card{
	public Sentry() {
		super();
		this.name = "Sentry";
		this.image = "/image/Dominion/cards/Dominion/Sentry.png";
		this.types[INDEX_ACTION] = true;
		this.price = 5;
		this.card = 1;
		this.action = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		player.revealTop(2);
		if (player.getRevealed().size() == 0) {
			return ask;
		}
		List<String> revealed = new ArrayList<String>();
		List<String> revealedImage = new ArrayList<String>();
		for (int i=0;i<player.getRevealed().size();i++) {
			revealed.add(player.getRevealed().get(i).getName());
			revealedImage.add(player.getRevealed().get(i).getImage());
		}
		ask.setViewedCards(revealed);
		ask.setViewedCardsImage(revealedImage);
		ask.setType(Ask.VIEW);
		ask.setSubType(Ask.CHOOSE);
		ask.setMsg("You may trash any of the revealed cards");
		ask.setUpper(2);
		ask.setLower(0);
		ask.setViewedCards(revealed);
		ask.setViewedCardsImage(revealedImage);
		ask.setResLevel(0);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		Card card;
		List<String> revealed = new ArrayList<String>();
		List<String> revealedImage = new ArrayList<String>();
		
		if (ask.getResLevel() == 0) {
			for (int i=0;i<ask.getSelectedRevealed().size();i++) {
				card = player.getRevealed().remove(ask.getSelectedRevealed().get(i)-i);
				card.onTrash(player);
				board.getTrash().add(card);
			}
			int n = ask.getSelectedRevealed().size();
			if (n == 1) {
				log(player.getName() + " trashes a card", 1);
			} else if (n > 1) {
				log(player.getName() + " trashes " + n + " cards", 1);
			}
			
			if (player.getRevealed().size() == 0) {
				ask = new Ask();
			} else {
				ask.setType(Ask.VIEW);
				ask.setSubType(Ask.CHOOSE);
				ask.setUpper(2);
				ask.setLower(0);
				ask.setResLevel(1);
				ask.setMsg("You may discard any of the revealed cards");
				for (int i=0;i<player.getRevealed().size();i++) {
					revealed.add(player.getRevealed().get(i).getName());
					revealedImage.add(player.getRevealed().get(i).getImage());
				}
				ask.setViewedCards(revealed);
				ask.setViewedCardsImage(revealedImage);
			}
		} else if (ask.getResLevel() == 1) {
			for (int i=0;i<ask.getSelectedRevealed().size();i++) {
				card = player.getRevealed().remove(ask.getSelectedRevealed().get(i)-i);
				card.onDiscard(player);
				player.getDiscard().add(card);
			}
			int n = ask.getSelectedRevealed().size();
			if (n == 1) {
				log(player.getName() + " discards a card", 1);
			} else if (n > 1) {
				log(player.getName() + " discards " + n + " cards", 1);
			}
			if (player.getRevealed().size() == 0) {
				ask = new Ask();
			} else {
				ask.setType(Ask.VIEW);
				ask.setSubType(Ask.REARRANGE);
				ask.setMsg("Rearrange revealed cards");
				ask.setResLevel(2);
				for (int i=0;i<player.getRevealed().size();i++) {
					revealed.add(player.getRevealed().get(i).getName());
					revealedImage.add(player.getRevealed().get(i).getImage());
				}
				ask.setViewedCards(revealed);
				ask.setViewedCardsImage(revealedImage);
			}
		} else if (ask.getResLevel() == 2) {
			player.topDeckRevealed(ask.getSelectedRevealed());
			int n = ask.getSelectedRevealed().size();
			if (n == 1) {
				log(player.getName() + " topdecks a card", 1);
			} else if (n > 1) {
				log(player.getName() + " topdecks " + n + " cards", 1);
			}
			ask = new Ask();
		}
		return ask;
	}
}
