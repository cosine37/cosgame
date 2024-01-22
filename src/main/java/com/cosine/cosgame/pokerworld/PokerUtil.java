package com.cosine.cosgame.pokerworld;

import java.util.ArrayList;
import java.util.List;

public class PokerUtil {
	public static List<PokerCard> stringToCardList(String s){
		List<PokerCard> cards = new ArrayList<>();
		int n = s.length();
		int i;
		for (i=0;i<n/2;i++) {
			String cs = "" + s.charAt(i*2) + s.charAt(i*2+1);
			PokerCard card = new PokerCard(cs);
			cards.add(card);
		}
		return cards;
	}
	
	public static String cardListToString(List<PokerCard> cards) {
		String s = "";
		int i;
		for (i=0;i<cards.size();i++) {
			s = s+cards.get(i).toString();
		}
		return s;
	}
	
	public static List<String> getStandardSuits(){
		List<String> suits = new ArrayList<>();
		suits.add("s");
		suits.add("h");
		suits.add("c");
		suits.add("d");
		return suits;
	}
	
	public static List<PokerCard> getStandardDeck(){
		List<PokerCard> cards = new ArrayList<>();
		List<String> suits = getStandardSuits();
		int i,j;
		for (i=1;i<=13;i++) {
			for (j=0;j<suits.size();j++) {
				PokerCard card = new PokerCard(i,suits.get(j));
				cards.add(card);
			}
		}
		return cards;
	}
	public static List<PokerCard> getWizardDeck(){
		List<PokerCard> cards = getStandardDeck();
		int i;
		for (i=0;i<4;i++) {
			PokerCard card = new PokerCard("WZ",0);
			cards.add(card);
			card = new PokerCard("JE",0);
			cards.add(card);
		}
		return cards;
	}
}
