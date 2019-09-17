package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;

public class Salvager extends Card{
	public Salvager() {
		super();
		this.name = "Salvager";
		this.image = "/image/Dominion/cards/Seaside/Salvager.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
		this.buy = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() == 0) {
			return ask;
		}
		ask.setType(Ask.HANDCHOOSE);
		ask.setMsg("Trash a card from your hand");
		ask.setUpper(1);
		ask.setLower(1);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		String cardName = ask.getSelectedCards().get(0);
		CardFactory factory = new CardFactory();
		int price = factory.createCard(cardName).getPrice(player.getPriceReduce());
		log(player.getName()+" trashes a " + cardName + " for + $" + price, 1);
		player.trash(ask.getSelectedCards(), "hand");
		player.addCoin(price);
		ask = new Ask();
		return ask;
	}
	
}
