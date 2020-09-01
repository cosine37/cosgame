package com.cosine.cosgame.citadels;

import org.bson.Document;

public class Card {
	protected String name;
	protected int cost;
	protected String img;
	protected int color;
	protected Player player;
	protected Board board;
	
	public Card() {
		
	}
	
	public Card(String name) {
		this.name = name;
	}
	
	public void alterPlayerAbility() {
		
	}
	
	public boolean destroyable() {
		return true;
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
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("cost", cost);
		doc.append("color", color);
		doc.append("img", img);
		return doc;
	}
}
