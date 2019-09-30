package com.cosine.cosgame.dominion.oriental;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;
import com.cosine.cosgame.dominion.Player;

public class ImperialExam extends Card{
	public ImperialExam() {
		super();
		this.name = "Imperial Exam";
		this.image = "/image/Dominion/cards/Oriental/ImperialExam.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 1;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setType(Ask.OPTION);
		List<String> options = new ArrayList<>();
		options.add("$4 card on your deck");
		options.add("$4 card on your Seclusion mat");
		options.add("Nothing");
		ask.setMsg("Choose one:");
		ask.setOptions(options);
		ask.setAns(-1);
		return ask;
		
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getResLevel() == 0) {
			if (ask.getAns() == 0) {
				ask = new Ask();
				ask.setCardName(name);
				ask.setType(Ask.GAIN);
				ask.setMsg("Gain a card costing up to $4 on your deck");
				ask.setUpper(4);
				ask.setLower(0);
				ask.setResLevel(1);
			} else if (ask.getAns() == 1) {
				ask = new Ask();
				ask.setCardName(name);
				ask.setType(Ask.GAIN);
				ask.setMsg("Gain a card costing up to $4 on your Seclusion mat");
				ask.setUpper(4);
				ask.setLower(0);
				ask.setResLevel(2);
			} else if (ask.getAns() == 2) {
				ask = new Ask();
			} else {
				ask = new Ask();
			}
		} else if (ask.getResLevel() == 1) {
			String gainedCardName = ask.getSelectedCards().get(0);
			log(player.getName() + " gains a " + gainedCardName, 1);
			board.gainToPlayerFromPileToTopdeck(player, board.getPileByTop(gainedCardName));
			ask = new Ask();
		} else if (ask.getResLevel() == 2) {
			String gainedCardName = ask.getSelectedCards().get(0);
			log(player.getName() + " gains a " + gainedCardName, 1);
			board.gainToPlayerFromPileToSeclusion(player, board.getPileByTop(gainedCardName));
			ask = new Ask();
		}
		return ask;
	}
	
	public Ask onGain(Player p) {
		int n = p.getDiscard().size() - 1;
		if (p.getDiscard().get(n).getName().equals(name)) {
			Card card = p.getDiscard().remove(n);
			p.getDeck().add(card);
			log("Imperial Exam is gained to " + p.getName() + "'s deck bottom", 0);
		}
		Ask ask = new Ask();
		return ask;
	}
}
