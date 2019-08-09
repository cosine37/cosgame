package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Library extends Card{
	public Library() {
		super();
		this.name = "Library";
		this.image = "/image/Dominion/cards/Dominion/Library.png";
		this.types[INDEX_ACTION] = true;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		while (player.getHand().size()<7) {
			if (player.getDeck().size() == 0) {
				player.shuffle();
			}
			if (player.getDeck().size() == 0) {
				break;
			}
			Card card = player.getDeck().remove(0);
			player.getHand().add(card);
			if (card.isActionType()) {
				String msg = "You may skip a " + card.getName();
				List<String> options = new ArrayList<String>();
				options.add("skip " + card.getName());
				options.add("put in hand");
				ask.setMsg(msg);
				ask.setType(Ask.OPTION);
				ask.setOptions(options);
				return ask;
			}
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getAns() == 0) {
			int n = player.getHand().size();
			Card card = player.getHand().remove(n-1);
			player.getRevealed().add(card);
		} else if (ask.getAns() == 1) {
			
		}
		while (player.getHand().size()<7) {
			if (player.getDeck().size() == 0) {
				player.shuffle();
			}
			if (player.getDeck().size() == 0) {
				break;
			}
			Card card = player.getDeck().remove(0);
			player.getHand().add(card);
			if (card.isActionType()) {
				String msg = "You may skip a " + card.getName();
				List<String> options = new ArrayList<String>();
				options.add("skip " + card.getName());
				options.add("put in hand");
				ask.setMsg(msg);
				ask.setOptions(options);
				if (player.getRevealed().size() == 0) {
					ask.setType(Ask.OPTION);
				} else {
					ask.setType(Ask.VIEW);
					ask.setSubType(Ask.OPTION);
					List<String> revealed = new ArrayList<String>();
					List<String> revealedImage = new ArrayList<String>();
					for (int i=0;i<player.getRevealed().size();i++) {
						revealed.add(player.getRevealed().get(i).getName());
						revealedImage.add(player.getRevealed().get(i).getImage());
					}
					ask.setViewedCards(revealed);
					ask.setViewedCardsImage(revealedImage);
				}
				return ask;
			}
		}
		while(player.getRevealed().size()>0) {
			player.getDiscard().add(player.getRevealed().remove(0));
		}
		ask = new Ask();
		return ask;
	}
}
