package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

public class Board {
	protected String id;
	protected String lotd;
	
	protected int status;
	protected int curCardId;
	protected int curPlayer;
	protected int firstPlayer;
	protected int round;
	protected int targetMoney;
	
	protected int height;
	protected int width;
	
	protected Map map;
	protected List<Integer> settings;
	protected List<String> playerNames;
	protected List<Player> players;

	public Board() {
		map = new Map();
		players = new ArrayList<>();
		playerNames = new ArrayList<>();
	}
	
	public void startGame() {
		
	}
	
	public void putPlayerOnPlace(Player player, Place place) {
		place.addPlayerOn(player);
	}
	
	public void putPlayerOnPlace(Player p, int x) {
		Place place = map.getPlace(x);
		place.addPlayerOn(p);
	}
	
	public int getSalary() {
		return 0;
	}
	
	public void addPlayer(String name) {
		playerNames.add(name);
		Player p = new Player();
		p.setName(name);
		p.setBoard(this);
		players.add(p);
	}
	
}
