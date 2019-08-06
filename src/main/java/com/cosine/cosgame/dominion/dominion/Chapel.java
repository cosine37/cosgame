package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Chapel extends Card{
	public Chapel() {
		super();
		this.name = "Chapel";
		this.image = "/image/Dominion/cards/Dominion/Chapel.png";
		this.types[INDEX_ACTION] = true;
		this.price = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() == 0) {
			return ask;
		}
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(4);
		ask.setLower(0);
		ask.setMsg("You may trash up to 4 cards");
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		int i,j;
		Card card;
		int n = ask.getSelectedCards().size();
		for (i=0;i<ask.getSelectedCards().size();i++) {
			for (j=0;j<player.getHand().size();j++) {
				if (player.getHand().get(j).getName().equals(ask.getSelectedCards().get(i))) {
					player.setBoard(board);
					player.trash(j);
					break;
				}
			}
		}
		ask = new Ask();
		return ask;
	}
}
