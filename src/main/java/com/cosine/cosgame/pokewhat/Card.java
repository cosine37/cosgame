package com.cosine.cosgame.pokewhat;

public class Card {
	protected int num;
	protected String name;
	protected String img;
	protected Player player;
	protected Board board;
	
	public Card() {
		
	}
	
	public void cardEffect() {
		
	}
	
	public void penalty() {
		player.hurt(1);
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
