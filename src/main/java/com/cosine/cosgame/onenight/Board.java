package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<Player> players;
	List<Role> rolesThisGame;
	
	int status;
	int round;
	
	List<String> confirmed;
	
	public Board() {
		players = new ArrayList<>();
		rolesThisGame = new ArrayList<>();
	}
	
	public void startGame() {
		int n = players.size();
		int i;
		for (i=0;i<n;i++) {
			players.get(i).initializeMarks(n);
		}
		
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Role> getRolesThisGame() {
		return rolesThisGame;
	}
	public void setRolesThisGame(List<Role> rolesThisGame) {
		this.rolesThisGame = rolesThisGame;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	
	
}
