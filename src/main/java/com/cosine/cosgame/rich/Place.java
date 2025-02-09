package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

public abstract class Place {
	protected int id;
	protected int type;
	
	protected String name;
	protected Place prev;
	protected Place next;
	
	protected Board board;
	
	protected List<Player> playersOn;
	
	public Place(int id, String name, int type) {
		this.id = id;
		this.name = name;
		this.type = type;
		
		playersOn = new ArrayList<>();
	}
	
	public void stepOn(Player p) {
		addPlayerOn(p);
	}
	
	public void bypass(Player p) {}

	public boolean hasPlayer(int index) {
		boolean f = false;
		for (int i=0;i<playersOn.size();i++) {
			if (playersOn.get(i).getIndex() == index) {
				f = true;
				break;
			}
		}
		return f;
	}
	
	public boolean hasPlayer(Player p) {
		return hasPlayer(p.getIndex());
	}
	
	public void removePlayer(Player p) {
		for (int i=0;i<playersOn.size();i++) {
			if (playersOn.get(i).getIndex() == p.getIndex()) {
				playersOn.remove(i);
				break;
			}
		}
	}
	
	public void addPlayerOn(Player p) {
		if (hasPlayer(p) == false) {
			playersOn.add(p);
			p.setPlace(this);
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Place getPrev() {
		return prev;
	}
	public void setPrev(Place prev) {
		this.prev = prev;
	}
	public Place getNext() {
		return next;
	}
	public void setNext(Place next) {
		this.next = next;
	}
	public List<Player> getPlayersOn() {
		return playersOn;
	}
	public void setPlayersOn(List<Player> playersOn) {
		this.playersOn = playersOn;
	}
	

}
