package com.cosine.cosgame.threechaodoms;

public class Card {
	String name;
	String img;
	String desc;
	int han;
	int wei;
	boolean blankSpace;
	
	Player player;
	Board board;
	
	public Card() {
		han = 0;
		wei = 0;
		blankSpace = false;
	}
	
	public void play() {
		
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getHan() {
		return han;
	}
	public void setHan(int han) {
		this.han = han;
	}
	public int getWei() {
		return wei;
	}
	public void setWei(int wei) {
		this.wei = wei;
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
	public boolean isBlankSpace() {
		return blankSpace;
	}
	public void setBlankSpace(boolean blankSpace) {
		this.blankSpace = blankSpace;
	}
	
	
}
