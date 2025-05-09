package com.cosine.cosgame.oink.flip7;

import java.util.ArrayList;
import java.util.List;

public class AllRes {
	public static List<Card> genDeck(){
		List<Card> deck = new ArrayList<>();
		int i,j;
		for (i=1;i<12;i++) {
			for (j=0;j<i;j++) {
				deck.add(Factory.genCard(i));
			}
		}
		deck.add(Factory.genCard(0));
		return deck;
	}
}
