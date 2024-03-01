package com.cosine.cosgame.pokerworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	public static List<PokerCard> getWizardDeck(int extraCards){
		List<PokerCard> cards = getWizardDeck();
		
		int x = extraCards;
		int bomb = x%10;
		x = x/10;
		int df = x%10;
		
		if (bomb == 1) {
			cards.add(new PokerCard("BM", 0));
		}
		if (df == 1) {
			cards.add(new PokerCard("DR", 0));
			cards.add(new PokerCard("FR", 0));
		}
		
		return cards;
	}
	public static List<PokerCard> shuffle(List<PokerCard> cards){
		List<PokerCard> newCards = new ArrayList<>();
		Random rand = new Random();
		while (cards.size()>0) {
			int x = rand.nextInt(cards.size());
			newCards.add(cards.remove(x));
		}
		return newCards;
	}
	
	public static boolean bigger(PokerCard c1, PokerCard c2, String dominantSuit, String currentSuit, int biggestRank, boolean sortHand) {
		if (dominantSuit == null) dominantSuit = "";
		if (currentSuit == null) currentSuit = "";
		System.out.println("dominantSuit = " + dominantSuit);
		System.out.println("currentSuit = " + currentSuit);
		System.out.println(c1.toString() + " " + c2.toString());
		
		// Bomb, Dragon and Fairy handles
		if (c2.getSuit().toUpperCase().contentEquals("BM")) {
			return true;
		} else if (c1.getSuit().toUpperCase().contentEquals("BM")) {
			return false;
		} else if (c1.getSuit().toUpperCase().contentEquals("DR")) {
			return true;
		} else if (c2.getSuit().toUpperCase().contentEquals("DR")) {
			return false;
		} else if (c2.getSuit().toUpperCase().contentEquals("FR")) {
			return true;
		} else if (c1.getSuit().toUpperCase().contentEquals("FR")) {
			return false;
		} else
		// end of Bomb, Dragon and Fairy handles
			
		if (c1.getSuit().toUpperCase().contentEquals("WZ")) {
			return true;
		} else if (c2.getSuit().toUpperCase().contentEquals("WZ")) {
			return false;
		} else if (c2.getSuit().toUpperCase().contentEquals("JE")) {
			return true;
		} else if (c1.getSuit().toUpperCase().contentEquals("JE")) {
			return false;
		} else if (c1.getSuit().contentEquals(dominantSuit)) {
			if (c2.getSuit().contentEquals(dominantSuit)) {
				if (c2.getRealRank(biggestRank) > c1.getRealRank(biggestRank)) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else if (c2.getSuit().contentEquals(dominantSuit)){
			return false;
		} else if (c1.getSuit().contentEquals(currentSuit)) {
			if (c2.getSuit().contentEquals(currentSuit)) {
				if (c2.getRealRank(biggestRank) > c1.getRealRank(biggestRank)) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else if (c2.getSuit().contentEquals(currentSuit)) {
			return false;
		} else if (sortHand){
			if (c1.getSuitValue() > c2.getSuitValue()) {
				return true;
			} else if (c1.getSuitValue() < c2.getSuitValue()) {
				return false;
			} else {
				if (c2.getRealRank(biggestRank) > c1.getRealRank(biggestRank)) {
					return false;
				} else {
					return true;
				}
			}
		} else {
			return false;
		}
	}
	
	public static boolean bigger(PokerCard c1, PokerCard c2, String dominantSuit, String currentSuit, int biggestRank) {
		return bigger(c1, c2, dominantSuit, currentSuit, biggestRank, false);
	}
	public static boolean bigger(PokerCard c1, PokerCard c2, Board board, boolean sortHand) {
		return bigger(c1, c2, board.getDominantSuit(), board.getCurrentSuit(), board.getBiggestRank(), sortHand);
	}
	public static boolean bigger(PokerCard c1, PokerCard c2, Board board) {
		boolean sortHand = false;
		if (board.getStatus() == Consts.DISTRIBUTECARDS) sortHand = true;
		return bigger(c1, c2, board.getDominantSuit(), board.getCurrentSuit(), board.getBiggestRank(), sortHand);
	}
}
