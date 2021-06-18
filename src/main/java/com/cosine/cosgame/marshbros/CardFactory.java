package com.cosine.cosgame.marshbros;

import com.cosine.cosgame.marshbros.official.*;

public class CardFactory {
	public static Card createCard(String img) {
		Card c = new Card();
		
		if (img.contentEquals("XieZhen")) {
			c = new XieZhen();
		}
		
		return c;
	}
}
