package com.cosine.cosgame.oink.pope;

import com.cosine.cosgame.oink.pope.cards.*;

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
		} else if (x == 2) {
			c = new Seer();
		} else if (x == 7) {
			c = new Mage();
		} else if (x == 5) {
			c = new Witch();
		} else if (x == 3) {
			c = new Knight();
		} else if (x == 1) {
			c = new Werewolf();
		} else if (x == 0) {
			c = new Thief();
		} else if (x == 6) {
			c = new Scholar();
		}
		return c;
	}
	
	public static String getCardName(int x) {
		Card tc = makeCard(x);
		return tc.getName();
	}
}
