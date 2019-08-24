package com.cosine.cosgame.dominion.oriental;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.dominion.Trash;

public class TombSweeping extends Card{
	public TombSweeping() {
		super();
		this.name = "TombSweeping";
		this.image = "/image/Dominion/cards/Oriental/TombSweeping.png";
		this.types[INDEX_ACTION] = true;
		this.action = 1;
		this.price = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		List<Pile> trashCards = board.getTrash().getTrashedCardsAsPiles();
		int total = 0;
		for (int i=0;i<trashCards.size();i++) {
			if (trashCards.get(i).getTop().getPrice() <= 2) total = total+1;
		}
		if (total == 0) {
			ask.setType(Ask.HANDCHOOSE);
			ask.setUpper(1);
			ask.setLower(1);
			ask.setMsg("Trash a card from your hand");
			ask.setResLevel(1);
			return ask;
		}
		ask.setType(Ask.OPTION);
		ask.setMsg("Choose one:");
		List<String> options = new ArrayList<>();
		options.add("Trash a card");
		options.add("Gain from trash");
		ask.setOptions(options);
		ask.setAns(-1);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getResLevel() == 0) {
			if (ask.getAns() == 0) {
				ask.setType(Ask.HANDCHOOSE);
				ask.setUpper(1);
				ask.setLower(1);
				ask.setMsg("Trash a card");
				ask.setResLevel(1);
			} else if (ask.getAns() == 1) {
				ask.setType(Ask.VIEW);
				ask.setSubType(Ask.CHOOSE);
				ask.setMsg("Gain a card from trash costing up to $2 to your hand");
				List<Pile> trashCards = board.getTrash().getTrashedCardsAsPiles();
				List<String> revealed = new ArrayList<String>();
				List<String> revealedImage = new ArrayList<String>();
				for (int i=0;i<trashCards.size();i++) {
					if (trashCards.get(i).getTop().getPrice() <= 2) {
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
					board.getTrash().gainToPlayerHand(player, revealed.get(0));
					ask = new Ask();
				}
			} else {
				ask = new Ask();
			}
		} else if (ask.getResLevel() == 1) {
			if (ask.getType() == Ask.HANDCHOOSE) {
				player.setBoard(board);
				player.trash(ask.getSelectedCards(), "hand");
				log(player.getName() + " trashes a "+ask.getSelectedCards().get(0), 1);
				CardFactory factory = new CardFactory();
				Card c = factory.createCard(ask.getSelectedCards().get(0));
				if (c.isTreasure()) {
					player.addCoin(1);
					log(player.getName() + " gets + $1", 1);
				}
				ask = new Ask();
			} else if (ask.getType() == Ask.VIEW) {
				board.getTrash().gainToPlayerHand(player, ask.getViewedCards().get(ask.getSelectedRevealed().get(0)));
				ask = new Ask();
			} else {
				ask = new Ask();
			}
		}
		return ask;
	}
}
