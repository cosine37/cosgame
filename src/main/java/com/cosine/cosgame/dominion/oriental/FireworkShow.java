package com.cosine.cosgame.dominion.oriental;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class FireworkShow extends Card{
	public FireworkShow() {
		super();
		this.name = "Firework Show";
		this.image = "/image/Dominion/cards/Oriental/FireworkShow.png";
		this.types[INDEX_ACTION] = true;
		this.coin = 1;
		this.action = 2;
		this.price = 3;
	}
	
	public Ask play() {
		Ask ask = super.play();
		player.revealTop(3);
		if (player.getRevealed().size() == 0) {
			ask.setType(Ask.SINGLEOPTION);
			ask.setAns(-1);
			List<String> options = new ArrayList<>();
			options.add("Trash this for +2 Cards");
			ask.setOptions(options);
			ask.setResLevel(2);
		} else {
			int total = 0;
			List<Card> rev = player.getRevealed();
			if (rev.size() == 1) {
				log(player.getName() + " reveals a card", 1);
			} else {
				log(player.getName() + " reveals " + rev.size() + " cards", 1);
			}
			for (int i=0;i<rev.size();i++) {
				if (rev.get(i).getName().equals("Copper")) total++;
				if (rev.get(i).isCursed()) total++;
			}
			if (total == 0){
				if (rev.size() == 1) {
					Card c = player.getRevealed().remove(0);
					player.topDeck(c);
					ask.setType(Ask.SINGLEOPTION);
					List<String> options = new ArrayList<>();
					options.add("Trash this for +2 Cards");
					ask.setOptions(options);
					ask.setResLevel(2);
				} else {
					ask.setType(Ask.VIEW);
					ask.setSubType(Ask.REARRANGE);
					ask.setMsg("Rearrange revealed cards");
					List<String> revealed = new ArrayList<String>();
					List<String> revealedImage = new ArrayList<String>();
					for (int i=0;i<player.getRevealed().size();i++) {
						revealed.add(player.getRevealed().get(i).getName());
						revealedImage.add(player.getRevealed().get(i).getImage());
					}
					ask.setViewedCards(revealed);
					ask.setViewedCardsImage(revealedImage);
					ask.setResLevel(1);
				}
			} else {
				ask.setType(Ask.VIEW);
				ask.setSubType(Ask.CHOOSE);
				ask.setRestriction(Ask.COPPERCURSED);
				ask.setUpper(1);
				ask.setLower(0);
				ask.setMsg("You may put a Copper or a Cursed card to your hand");
				List<String> revealed = new ArrayList<String>();
				List<String> revealedImage = new ArrayList<String>();
				List<String> chooseable = new ArrayList<String>();
				for (int i=0;i<player.getRevealed().size();i++) {
					revealed.add(player.getRevealed().get(i).getName());
					revealedImage.add(player.getRevealed().get(i).getImage());
					if (player.getRevealed().get(i).getName().equals("Copper")
							|| player.getRevealed().get(i).isCursed()) {
						chooseable.add("yes");
					} else {
						chooseable.add("no");
					}
				}
				ask.setViewedCards(revealed);
				ask.setViewedCardsImage(revealedImage);
				ask.setViewedCardsChooseable(chooseable);
			}
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getResLevel() == 0) {
			int n = ask.getSelectedRevealed().size();
			Card c;
			for (int i=0;i<n;i++) {
				c = player.getRevealed().remove(ask.getSelectedRevealed().get(i)-i);
				player.getHand().add(c);
				log(player.getName() + " puts a " + c.getName() + " in hand", 1);
			}
			List<Card> rev = player.getRevealed();
			if (rev.size() < 2) {
				if (rev.size() > 0) {
					c = player.getRevealed().remove(0);
					player.topDeck(c);
					log(player.getName() + " puts the revealed card on top of the deck", 1);
				}
				ask = new Ask();
				ask.setCardName(name);
				ask.setType(Ask.SINGLEOPTION);
				List<String> options = new ArrayList<>();
				options.add("Trash this for +2 Cards");
				ask.setOptions(options);
				ask.setResLevel(2);
			} else {
				ask = new Ask();
				ask.setCardName(name);
				ask.setType(Ask.VIEW);
				ask.setSubType(Ask.REARRANGE);
				ask.setMsg("Rearrange revealed cards");
				List<String> revealed = new ArrayList<String>();
				List<String> revealedImage = new ArrayList<String>();
				for (int i=0;i<player.getRevealed().size();i++) {
					revealed.add(player.getRevealed().get(i).getName());
					revealedImage.add(player.getRevealed().get(i).getImage());
				}
				ask.setViewedCards(revealed);
				ask.setViewedCardsImage(revealedImage);
				ask.setResLevel(1);
			}
		} else if (ask.getResLevel() == 1) {
			player.topDeckRevealed(ask.getSelectedRevealed());
			log(player.getName() + " puts the revealed cards back", 1);
			ask = new Ask();
			ask.setCardName(name);
			ask.setType(Ask.SINGLEOPTION);
			ask.setAns(-1);
			List<String> options = new ArrayList<>();
			options.add("Trash this for +2 Cards");
			ask.setOptions(options);
			ask.setResLevel(2);
		} else if (ask.getResLevel() == 2) {
			if (ask.getAns() == 0) {
				Card card = player.getPlay().remove(player.getPlay().size()-1);
				log(player.getName()+ " trashes Firework Show for +2 Cards", 1);
				board.getTrash().add(card);
				player.draw(2);
			}
			ask = new Ask();
		}
		return ask;
	}
}
