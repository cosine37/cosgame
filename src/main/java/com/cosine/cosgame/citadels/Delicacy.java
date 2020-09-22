package com.cosine.cosgame.citadels;

public class Delicacy {
	protected String name;
	protected String img;
	protected int cost;
	protected Board board;
	
	public Delicacy() {
		
	}
	
	public Ask onBuy(Player p) {
		Ask ask = new Ask();
		return ask;
	}
	
	public Ask afterEffect(Player p) {
		Ask ask = new Ask();
		return ask;
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

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
