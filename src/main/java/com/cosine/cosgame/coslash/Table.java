package com.cosine.cosgame.coslash;

import java.util.ArrayList;
import java.util.List;

public class Table {
	List<Player> players;
	List<Card> deck;
	
	int flag;
	public static final int CHOOSEGENERAL = 1;
	public static final int INGAME = 2;
	public static final int ENDGAME = 3;
	
	public Table() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
	}
	
	public boolean checkGameEnd() {
		boolean ans = false;
		return ans;
	}
	
}
