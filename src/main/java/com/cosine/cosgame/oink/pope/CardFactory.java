package com.cosine.cosgame.oink.pope;

import com.cosine.cosgame.oink.pope.cards.Guard;
import com.cosine.cosgame.oink.pope.cards.Mayor;
import com.cosine.cosgame.oink.pope.cards.Pope;

public class CardFactory {
	public static Card makeCard(int x) {
		Card c = new Card();
		if (x == -1) {
			return null;
		} else if (x == 4) {
			c = new Guard();
		} else if (x == 8) {
			c = new Mayor();
		} else if (x == 9) {
			c = new Pope();
		}
		return c;
	}
}
