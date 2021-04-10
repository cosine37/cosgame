package com.cosine.cosgame.onenight;

public class Status {
	protected String name;
	protected String img;
	protected int num;
	protected int nameColor;
	
	protected Board board;
	protected Player player;
	
	public Status() {
		num = Consts.NOSTATUS;
		name = "";
		img = "";				
	}
	
	public void vision() {
		
	}
	
	public void votedOutHandle() {
		
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
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getNameColor() {
		return nameColor;
	}
	public void setNameColor(int nameColor) {
		this.nameColor = nameColor;
	}
	
	
}
