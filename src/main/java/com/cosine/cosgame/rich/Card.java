package com.cosine.cosgame.rich;

import java.util.List;

public class Card {
	int id;
	String name;
	List<Boolean> types;
	Player player;
	Board board;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Boolean> getTypes() {
		return types;
	}
	public void setTypes(List<Boolean> types) {
		this.types = types;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
