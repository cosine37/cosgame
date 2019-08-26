package com.cosine.cosgame.dominion.oriental;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Executioner extends Card{
	public Executioner() {
		super();
		this.name = "Executioner";
		this.image = "/image/Dominion/cards/Oriental/Executioner.png";
		this.types[INDEX_ACTION] = true;
		this.coin = 2;
		this.price = 3;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (!player.hasType("Action")) {
			return ask;
		} else {
			ask.setType(Ask.HANDCHOOSE);
			ask.setMsg("You may trash an Action or a General card for +2 Memorials");
			ask.setUpper(1);
			ask.setLower(0);
			ask.setRestriction(Ask.ACTIONGENERAL);
			return ask;
		}
		
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getSelectedCards().size() == 0) {
			
		} else {
			player.trash(ask.getSelectedCards(), "hand");
			log(player.getName() + " trashes a "+ask.getSelectedCards().get(0),1);
			player.addMemorial(2);
			log(player.getName() + " receives 2 Memorial tokens",1);
		}
		ask = new Ask();
		return ask;
	}
}
