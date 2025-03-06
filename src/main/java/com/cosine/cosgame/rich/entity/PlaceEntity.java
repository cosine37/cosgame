package com.cosine.cosgame.rich.entity;

import java.util.List;

public class PlaceEntity {
	String name;
	int type;
	List<Integer> playersOn;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Integer> getPlayersOn() {
		return playersOn;
	}
	public void setPlayersOn(List<Integer> playersOn) {
		this.playersOn = playersOn;
	}
	
}
