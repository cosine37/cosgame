package com.cosine.cosgame.citadels;

public class CardFactory {
	public static Card createCard(String name, int color, int cost, String img) {
		Card card = new Card(name);
		card.setCost(cost);
		card.setColor(color);
		card.setImg(img);
		return card;
	}

}
