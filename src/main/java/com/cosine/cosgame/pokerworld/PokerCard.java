package com.cosine.cosgame.pokerworld;

public class PokerCard {
	String suit;
	int rank;
	
	public PokerCard() {
		suit = "";
		rank = 0;
	}
	
	public PokerCard(String str) {
		this();
		setFromStr(str);
	}
	
	public PokerCard(String suit, int rank) {
		this();
		this.suit = suit;
		this.rank = rank;
	}
	
	public PokerCard(int rank, String suit) {
		this(suit, rank);
	}
	
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getRankStr() {
		if (rank == 0) return "";
		if (rank == 1) return "A";
		if (rank == 10) return "T";
		if (rank == 11) return "J";
		if (rank == 12) return "Q";
		if (rank == 13) return "K";
		return Integer.toString(rank);
	}
	
	public String toString() {
		return suit + getRankStr();
	}
	
	public void setFromStr(String s) {
		char ch = s.charAt(0);
		if (ch == 's' || ch == 'h' || ch =='c' || ch == 'd') {
			suit = "" + ch;
			ch = s.charAt(1);
			rank = 0;
			if (ch > '1' && ch <= '9') {
				rank = Character.getNumericValue(ch);
			} else if (ch == 'A') {
				rank = 1;
			} else if (ch == 'T') {
				rank = 10;
			} else if (ch == 'J') {
				rank = 11;
			} else if (ch == 'Q') {
				rank = 12;
			} else if (ch == 'K') {
				rank = 13;
			}
		} else {
			suit = s;
			rank = 0;
		}
	}
}
