package com.cosine.cosgame.hotpot;

public class Chip {
	String name;
	String img;
	int id;
	int level;
	int price;
	int potIndex;
	
	Player player;
	Board board;
	
	public Chip() {
		
	}
	
	public void prePut() {
		
	}
	
	public void afterPut() {
		
	}
	
	public void afterPutChip(Chip c) {
		
	}
	
	public void afterDrawing() {
		
	}
	
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPotIndex() {
		return potIndex;
	}
	public void setPotIndex(int potIndex) {
		this.potIndex = potIndex;
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
