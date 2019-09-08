package com.cosine.cosgame.dominion.intrigue;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class SecretPassage extends Card{
	public SecretPassage() {
		super();
		this.name = "SecretPassage";
		this.image = "/image/Dominion/cards/Intrigue/SecretPassage.png";
		this.types[INDEX_ACTION] = true;
		this.card = 2;
		this.action = 1;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() == 0) {
			
		} else if (player.getDeck().size() == 0){
			if (player.getHand().size() == 1) {
				Card c = player.getHand().remove(0);
				player.getDeck().add(c);
				log(player.getName() + " puts a card back in deck",1);
			} else {
				ask.setType(Ask.HANDCHOOSE);
				ask.setMsg("Put a card back in deck");
				ask.setUpper(1);
				ask.setLower(1);
				ask.setAns(0);
			}
		} else {
			ask.setType(Ask.HANDCHOOSE);
			ask.setSubType(Ask.RANGEBAR);
			ask.setMsg("Put a card back in deck");
			ask.setUpper(1);
			ask.setLower(1);
			ask.setRange(player.getDeck().size());
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		String cardname = ask.getSelectedCards().get(0);
		Card c = new Card();
		for (int i=player.getHand().size()-1;i>=0;i--) {
			if (player.getHand().get(i).getName().equals(cardname)) {
				c = player.getHand().remove(i);
				break;
			}
		}
		log(player.getName() + " puts a card back in deck",1);
		player.getDeck().add(ask.getAns(), c);
		ask = new Ask();
		return ask;
	}
}
