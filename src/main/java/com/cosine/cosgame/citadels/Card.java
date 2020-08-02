package com.cosine.cosgame.citadels;

public class Card {
	String name;
	int cost;
	String img;
	int color;
	Player player;
	Board board;
	
	public Card(String name) {
		this.name = name;
	}
	
	public int getColorForScore() {
		return color;
	}
	public int getScore() {
		return cost;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
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
