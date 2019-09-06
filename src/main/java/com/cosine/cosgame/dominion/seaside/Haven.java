package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Haven extends Card{
	public Haven() {
		super();
		this.name = "Haven";
		this.image = "/image/Dominion/cards/Seaside/Haven.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_DURATION] = true;
		this.price = 2;
		this.card = 1;
		this.action = 1;
		this.nt = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() == 0) {
			numTurns = 0;
			return ask;
		}
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(1);
		ask.setLower(1);
		ask.setMsg("Set aside a card under this");
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		String cardname = ask.getSelectedCards().get(0);
		System.out.println("cardname="+cardname);
		for (int i=player.getHand().size()-1;i>=0;i--) {
			if (player.getHand().get(i).getName().equals(cardname)) {
				System.out.println("finds the card!");
				Card c = player.getHand().remove(i);
				player.getHaven().add(c);
				break;
			}
		}
		System.out.println("Haven size in haven = " + player.getHaven().size());
		log(player.getName()+" sets aside a card", 1);
		ask = new Ask();
		return ask;
	}
	
	public Ask duration() {
		Ask ask = super.duration();
		
		return ask;
	}
	
}
