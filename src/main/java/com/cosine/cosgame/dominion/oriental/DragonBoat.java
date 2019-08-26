package com.cosine.cosgame.dominion.oriental;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class DragonBoat extends Card{
	public DragonBoat() {
		super();
		this.name = "DragonBoat";
		this.image = "/image/Dominion/cards/Oriental/DragonBoat.png";
		this.types[INDEX_ACTION] = true;
		this.coin = 1;
		this.price = 3;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() > 1) {
			ask.setType(Ask.HANDCHOOSE);
			ask.setUpper(1);
			ask.setLower(1);
			ask.setMsg("Discard a card");
			return ask;
		} else {
			player.setBoard(board);
			player.discardHand();
			player.revealTop(2);
			if (player.getRevealed().size() == 0) {
				ask = new Ask();
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
			ask.setMsg("You may trash one of the revealed cards");
			ask.setUpper(1);
			ask.setLower(0);
			ask.setResLevel(1);
			return ask;
		}
		
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getResLevel() == 0) {
			player.setBoard(board);
			log(player.getName() + " discards a card", 1);
			player.discard(ask.getSelectedCards().get(0));
			player.revealTop(2);
			if (player.getRevealed().size() == 0) {
				ask = new Ask();
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
			ask.setMsg("You may trash one of the revealed cards");
			ask.setUpper(1);
			ask.setLower(0);
			ask.setResLevel(1);
			return ask;
		} else if (ask.getResLevel() == 1) {
			Card card;
			int n = ask.getSelectedRevealed().size();
			for (int i=0;i<n;i++) {
				card = player.getRevealed().remove(ask.getSelectedRevealed().get(i)-i);
				card.onTrash(player);
				board.getTrash().add(card);
				log(player.getName() + " trashes a card", 1);
			}
			
			if (n == 1) {
				List<Integer> lsi= new ArrayList<>();
				lsi.add(0);
				player.topDeckRevealed(lsi);
				ask = new Ask();
				return ask;
			} else {
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
				ask.setMsg("You may put one of the revealed cards to your hand");
				ask.setUpper(1);
				ask.setLower(0);
				ask.setResLevel(2);
				return ask;
			}
			
		} else if (ask.getResLevel() == 2) {
			Card card;
			int n = ask.getSelectedRevealed().size();
			for (int i=0;i<n;i++) {
				card = player.getRevealed().remove(ask.getSelectedRevealed().get(i)-i);
				player.getHand().add(card);
				log(player.getName() + " puts a revealed card in hand", 1);
			}
			if (n == 1) {
				List<Integer> lsi= new ArrayList<>();
				lsi.add(0);
				player.topDeckRevealed(lsi);
				ask = new Ask();
				return ask;
			} else {
				ask.setType(Ask.VIEW);
				ask.setSubType(Ask.REARRANGE);
				ask.setMsg("Rearrange revealed cards");
				ask.setResLevel(3);
				List<String> revealed = new ArrayList<String>();
				List<String> revealedImage = new ArrayList<String>();
				for (int i=0;i<player.getRevealed().size();i++) {
					revealed.add(player.getRevealed().get(i).getName());
					revealedImage.add(player.getRevealed().get(i).getImage());
				}
				ask.setViewedCards(revealed);
				ask.setViewedCardsImage(revealedImage);
				return ask;
			}
		} else if (ask.getResLevel() == 3) {
			player.topDeckRevealed(ask.getSelectedRevealed());
			log(player.getName() + " puts the revealed cards back", 1);
		}
		
		ask = new Ask();
		return ask;
	}
}
