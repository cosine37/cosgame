package com.cosine.cosgame.nothanks;

import java.util.ArrayList;
import java.util.List;

public class AllRes {
	List<Card> cards;
	
	public AllRes() {
		cards = new ArrayList<>();
		genAllCards();
	}
	
	public void genAllCards(){
		for (int i=0;i<=12;i++) {
			Card c = CardFactory.createCard(i);
			cards.add(c);
		}
	}
	
	public List<Card> genDeck(int n, int x) {
		List<Card> deck = cards;
		int numGold;
		numGold = 12%n + n*x;
		
		for (int i=0;i<numGold;i++) {
			Card c = CardFactory.createCard(-3);
			deck.add(c);
		}
		return deck;
	}
	
}
