package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.dominion.Player;

public class Lurker extends Card{
	public Lurker() {
		super();
		this.name = "Lurker";
		this.image = "/image/Dominion/cards/Intrigue/Lurker.png";
		this.types[INDEX_ACTION] = true;
		this.action = 1;
		this.price = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		int total = 0;
		List<Pile> trashCards = board.getTrash().getTrashedCardsAsPiles();
		for (int i=0;i<trashCards.size();i++) {
			if (trashCards.get(i).getTop().isActionType()) total = total+1;
		}
		if (total == 0) {
			ask.setCardName(name);
			ask.setType(Ask.GAIN);
			ask.setMsg("Trash an Action card from supply");
			ask.setUpper(1000);
			ask.setLower(0);
			ask.setRestriction(Ask.ACTION);
			ask.setResLevel(1);
		} else {
			String msg = "Choose one:";
			List<String> options = new ArrayList<String>();
			options.add("Trash an Action from supply");
			options.add("Gain an Action from trash");
			int ans = -1;
			ask.setType(Ask.OPTION);
			ask.setMsg(msg);
			ask.setOptions(options);
			ask.setAns(ans);
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = new Ask();
		if (a.getResLevel() == 0) {
			if (a.getAns() == 0) {
				ask.setCardName(name);
				ask.setType(Ask.GAIN);
				ask.setMsg("Trash an Action card from supply");
				ask.setUpper(1000);
				ask.setLower(0);
				ask.setRestriction(Ask.ACTION);
				ask.setResLevel(1);
			} else if (a.getAns() == 1) {
				ask.setCardName(name);
				ask.setType(Ask.VIEW);
				ask.setSubType(Ask.CHOOSE);
				ask.setMsg("Gain an Action card from trash");
				List<Pile> trashCards = board.getTrash().getTrashedCardsAsPiles();
				List<String> revealed = new ArrayList<String>();
				List<String> revealedImage = new ArrayList<String>();
				for (int i=0;i<trashCards.size();i++) {
					if (trashCards.get(i).getTop().isActionType()) {
						revealed.add(trashCards.get(i).getTop().getName());
						revealedImage.add(trashCards.get(i).getTop().getImage());
					}
				}
				ask.setViewedCards(revealed);
				ask.setViewedCardsImage(revealedImage);
				ask.setUpper(1);
				ask.setLower(1);
				ask.setResLevel(1);
				
				if (revealed.size() == 1) {
					board.getTrash().gainToPlayer(player, revealed.get(0));
					log(player.getName() + " gains a "+ revealed.get(0) + " from trash", 1);
					ask = new Ask();
				}
			} else {
				
			}
		} else if (a.getResLevel() == 1) {
			if (a.getType() == Ask.GAIN) {
				String gainedCardName = a.getSelectedCards().get(0);
				log(player.getName() + " trashes a " + gainedCardName + " from supply", 1);
				board.trashFromPile(player, board.getPileByTop(gainedCardName));
			} else if (a.getType() == Ask.VIEW) {
				String cardname = a.getViewedCards().get(a.getSelectedRevealed().get(0));
				log(player.getName() + " gains a "+ cardname + " from trash", 1);
				board.getTrash().gainToPlayer(player, a.getViewedCards().get(a.getSelectedRevealed().get(0)));
			} else {
				
			}
		} else {
			
		}
		
		return ask;
	}
	
}
