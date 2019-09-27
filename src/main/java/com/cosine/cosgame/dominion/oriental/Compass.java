package com.cosine.cosgame.dominion.oriental;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Buff;
import com.cosine.cosgame.dominion.Card;

public class Compass extends Card{
	public Compass() {
		super();
		this.name = "Compass";
		this.image = "/image/Dominion/cards/Oriental/Compass.png";
		this.types[INDEX_TREASURE] = true;
		this.coin = 1;
		this.price = 4;
		this.autoplay = false;
	}
	
	public Ask play() {
		Ask ask = super.play();
		player.revealTop(4);
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
		ask.setMsg("You may discard any of the revealed cards");
		ask.setUpper(4);
		ask.setLower(0);
		ask.setViewedCards(revealed);
		ask.setViewedCardsImage(revealedImage);
		ask.setResLevel(0);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		Card c;
		List<String> revealed = new ArrayList<String>();
		List<String> revealedImage = new ArrayList<String>();
		if (ask.getResLevel() == 0) {
			int n = ask.getSelectedRevealed().size();
			if (n == 1) {
				log(player.getName() + " discards a card", 1);
			} else if (n > 1) {
				log(player.getName() + " discards " + n + " cards", 1);
			}
			for (int i=0;i<ask.getSelectedRevealed().size();i++) {
				c = player.getRevealed().remove(ask.getSelectedRevealed().get(i)-i);
				c.onDiscard(player);
				player.getDiscard().add(c);
			}
			if (player.getRevealed().size() == 0) {
				ask = new Ask();
			} else {
				ask.setType(Ask.VIEW);
				ask.setSubType(Ask.REARRANGE);
				ask.setMsg("Rearrange revealed cards");
				ask.setResLevel(1);
				for (int i=0;i<player.getRevealed().size();i++) {
					revealed.add(player.getRevealed().get(i).getName());
					revealedImage.add(player.getRevealed().get(i).getImage());
				}
				ask.setViewedCards(revealed);
				ask.setViewedCardsImage(revealedImage);
			}
		} else if (ask.getResLevel() == 1) {
			player.topDeckRevealed(ask.getSelectedRevealed());
			int n = ask.getSelectedRevealed().size();
			if (n == 1) {
				log(player.getName() + " topdecks a card", 1);
			} else if (n > 1) {
				log(player.getName() + " topdecks " + n + " cards", 1);
			}
			ask = new Ask();
		} else {
			ask = new Ask();
		}
		
		return ask;
	}
}
