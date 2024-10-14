package com.cosine.cosgame.rich;

public abstract class Place {
	int id;
	int type;
	
	String name;
	Place left;
	Place right;
	
	public Place(int id, String name, int type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	public abstract void stepOn(Player p);

}
