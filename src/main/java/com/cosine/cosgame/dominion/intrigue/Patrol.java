package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Patrol extends Card{
	public Patrol() {
		super();
		this.name = "Patrol";
		this.image = "/image/Dominion/cards/Intrigue/Patrol.png";
		this.types[INDEX_ACTION] = true;
		this.price = 5;
		this.card = 3;
	}
	
	public Ask play() {
		Ask ask = super.play();
		player.revealTop(4);
		int i;
		String s = player.getName() + " reveals ";
		for (i=0;i<player.getRevealed().size();i++) {
			if (i == player.getRevealed().size()-1) {
				s = s + "and a " + player.getRevealed().get(i).getName();
			} else {
				s = s+ "a " + player.getRevealed().get(i).getName() + ", ";
			}
		}
		log(s, 1);
		i=0;
		List<String> ls = new ArrayList<>();
		while (i<player.getRevealed().size()) {
			Card card = player.getRevealed().get(i);
			if (card.getName().equals("Curse") || card.isVictory()) {
				ls.add(card.getName());
				player.getHand().add(card);
				player.getRevealed().remove(i);
			} else {
				i = i+1;
			}
		}
		if (ls.size() == 0) {
			
		} else if (ls.size() == 1) {
			log(player.getName() + " puts a "+ls.get(0)+" in hand", 1);
		} else {
			s = player.getName() + " puts ";
			for (i=0;i<ls.size();i++) {
				if (i == ls.size()-1) {
					s = s + "and a " + ls.get(i);
				} else {
					s = s+ "a " + ls.get(i) + ", ";
				}
			}
			s = s+" in hand";
			log(s, 1);
		}
		if (player.getRevealed().size()>1) {
			List<String> revealed = new ArrayList<String>();
			List<String> revealedImage = new ArrayList<String>();
			ask.setType(Ask.VIEW);
			ask.setSubType(Ask.REARRANGE);
			ask.setMsg("Rearrange revealed cards");
			ask.setResLevel(2);
			for (i=0;i<player.getRevealed().size();i++) {
				revealed.add(player.getRevealed().get(i).getName());
				revealedImage.add(player.getRevealed().get(i).getImage());
			}
			ask.setViewedCards(revealed);
			ask.setViewedCardsImage(revealedImage);
		} else if (player.getRevealed().size() == 1) {
			List<Integer> lsi = new ArrayList<>();
			lsi.add(0);
			player.topDeckRevealed(lsi);
			log(player.getName() + " topdecks a card", 1);
		}
		return ask;
		
	}
	
	public Ask response(Ask a) {
		Ask ask = a;
		player.topDeckRevealed(ask.getSelectedRevealed());
		int n = ask.getSelectedRevealed().size();
		if (n == 1) {
			log(player.getName() + " topdecks a card", 1);
		} else if (n > 1) {
			log(player.getName() + " topdecks " + n + " cards", 1);
		}
		ask = new Ask();
		return ask;
	}
}
