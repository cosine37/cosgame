package com.cosine.cosgame.oink.pope;

public class CardFactory {
	public static Card makeCard(int x) {
		if (x == -1) {
			return null;
		}
		return new Card();
	}
}
