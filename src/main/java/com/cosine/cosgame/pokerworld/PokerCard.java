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
	
	public int getRealRank(int biggestRank) {
		int ans = rank;
		if (ans<100) ans = ans*100;
		if (ans<=biggestRank*100) {
			ans = ans+1300;
		}
		return ans;
	}
	
	public int getSuitValue() {
		if (suit.contentEquals("u")) {
			return 9;
		} else if (suit.contentEquals("s")) {
			return 4;
		} else if (suit.contentEquals("h")) {
			return 3;
		} else if (suit.contentEquals("c")) {
			return 2;
		} else if (suit.contentEquals("d")) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public void handleOption(int option) {
		if (option == -1) return;
		if (suit.contentEquals("ME")) {
			if (option == 0) {
				suit = "Me";
			} else if (option == 1) {
				suit = "mE";
			}
		} else if (suit.contentEquals("u")) {
			if (option == 0) {
				suit = "s";
			} else if (option == 1) {
				suit = "h";
			} else if (option == 2) {
				suit = "c";
			} else if (option == 3) {
				suit = "d";
			}
		}
	}
	
	public boolean contentEquals(PokerCard c) {
		if (c.getRank() == this.rank && c.getSuitValue() == this.getSuitValue()) {
			return true;
		}
		return false;
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
		if (rank == 10) return "" + Consts.TEN;
		if (rank == 11) return "J";
		if (rank == 12) return "Q";
		if (rank == 13) return "K";
		if (rank == 975) return "" + Consts.NINEMORE;
		if (rank == 750) return "" + Consts.SEVENMORE;
		return Integer.toString(rank);
	}
	
	public String toString() {
		return getRankStr() + suit;
	}
	
	public void setFromStr(String s) {
		if (s.length() == 0) {
			suit = "";
			rank = 0;
			return;
		}
		char ch = s.charAt(1);
		if (ch == 's' || ch == 'h' || ch =='c' || ch == 'd' || ch == 'u') {
			suit = "" + ch;
			ch = s.charAt(0);
			rank = 0;
			if (ch > '1' && ch <= '9') {
				rank = Character.getNumericValue(ch);
			} else if (ch == 'A') {
				rank = 1;
			} else if (ch == Consts.TEN) {
				rank = 10;
			} else if (ch == 'J') {
				rank = 11;
			} else if (ch == 'Q') {
				rank = 12;
			} else if (ch == 'K') {
				rank = 13;
			} else if (ch == Consts.NINEMORE) {
				rank = 975;
			} else if (ch == Consts.SEVENMORE) {
				rank = 750;
			}
		} else {
			suit = s;
			rank = 0;
		}
	}
	
}
