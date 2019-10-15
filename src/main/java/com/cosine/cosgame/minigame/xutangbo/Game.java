package com.cosine.cosgame.minigame.xutangbo;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	public static final int INACTIVE = 0;
	public static final int INGAME = 1;
	public static final int ENDGAME = 2;
	
	int status;
	List<Player> players;
	
	int round;
	int step;
	
	public Game() {
		players = new ArrayList<>();
		status = INACTIVE;
	}
	
	public Game(int numPlayers) {
		
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public void judge() {
		if (status == INGAME) {
			int i;
			int maxPower = 0;
			for (i=0;i<players.size();i++) {
				if (players.get(i).getCurMove().getPower() > maxPower) {
					maxPower = players.get(i).getCurMove().getPower();
				}
			}
			for (i=0;i<players.size();i++) {
				if (players.get(i).getCurMove().getDefence() < maxPower) {
					players.get(i).die();
				}
			}
		}
	}
	
}
