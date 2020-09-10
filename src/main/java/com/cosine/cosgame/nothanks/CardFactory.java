package com.cosine.cosgame.nothanks;

import com.cosine.cosgame.nothanks.Card;

public class CardFactory {
	
	public static Card createCard(int num) {
		Card card = new Card();
		card.setNum(num);
		card.setImg(Integer.toString(num));
		card.setName(Integer.toString(num));
		if (num==0) {
			card.setName("?");
		} else if (num == -3) {
			card.setName("Gold");
		}
		return card;
	}
	
}
