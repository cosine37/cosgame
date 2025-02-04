package com.cosine.cosgame.rich;

public class Place {
	protected int id;
	protected int type;
	
	protected String name;
	protected Place left;
	protected Place right;
	
	protected Board board;
	
	public Place(int id, String name, int type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	public void stepOn(Player p) {}
	
	public void bypass(Player p) {}

}
