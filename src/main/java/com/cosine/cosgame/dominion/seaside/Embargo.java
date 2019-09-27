package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Embargo extends Card{
	public Embargo() {
		super();
		this.name = "Embargo";
		this.image = "/image/Dominion/cards/Seaside/Embargo.png";
		this.types[INDEX_ACTION] = true;
		this.coin = 2;
		this.price = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setMsg("Put an Embargo token on a Supply pile");
		ask.setType(Ask.GAIN);
		ask.setUpper(10000);
		ask.setLower(0);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		Card c = player.getPlay().remove(player.getPlay().size()-1);
		log(player.getName()+ " trashes Embargo", 1);
		board.getTrash().add(c);
		String gainedCardName = ask.getSelectedCards().get(0);
		log(player.getName() + " puts an Embargo token on " + gainedCardName + " pile", 1);
		board.getPileByTop(gainedCardName).addEmbargo(1);
		ask = new Ask();
		return ask;
	}
	
}
