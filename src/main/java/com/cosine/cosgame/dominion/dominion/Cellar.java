package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Cellar extends Card{
	public Cellar() {
		super();
		this.name = "Cellar";
		this.image = "/image/Dominion/cards/Dominion/Cellar.png";
		this.types[INDEX_ACTION] = true;
		this.price = 2;
		this.action = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() == 0) {
			return ask;
		}
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(10000);
		ask.setLower(0);
		ask.setMsg("You may discard any number of cards and draw that many");
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		int i,j;
		Card card;
		int n = ask.getSelectedCards().size();
		log(player.getName() + " discards " + n + " cards", 1);
		for (i=0;i<ask.getSelectedCards().size();i++) {
			for (j=player.getHand().size()-1;j>=0;j--) {
				if (player.getHand().get(j).getName().equals(ask.getSelectedCards().get(i))) {
					player.discard(j);
					break;
				}
			}
		}
		player.draw(n);
		log(player.getName() + " draws " + n + " cards", 1);
		ask = new Ask();
		return ask;
	}
}
