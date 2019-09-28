package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class MiningVillage extends Card{
	public MiningVillage() {
		super();
		this.name = "Mining Village";
		this.image = "/image/Dominion/cards/Intrigue/MiningVillage.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 2;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setType(Ask.SINGLEOPTION);
		List<String> options = new ArrayList<>();
		options.add("Trash this for $2");
		ask.setOptions(options);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getAns() == 0) {
			Card card = player.getPlay().remove(player.getPlay().size()-1);
			log(player.getName()+ " trashes Mining Village for $2", 1);
			board.getTrash().add(card);
			player.addCoin(2);
		}
		ask = new Ask();
		return ask;
	}
}
