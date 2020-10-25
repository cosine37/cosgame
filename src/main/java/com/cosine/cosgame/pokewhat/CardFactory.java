package com.cosine.cosgame.pokewhat;

public class CardFactory {
	public static Card createCard(String img) {
		Card card = new Card();
		card.setImg(img);
		return card;
	}
	
	public static Card createCard(int x) {
		String img = Integer.toString(x);
		Card card = new Card();
		card.setImg(img);
		card.setNum(1);
		return card;
	}
}
