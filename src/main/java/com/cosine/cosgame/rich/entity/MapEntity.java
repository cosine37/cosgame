package com.cosine.cosgame.rich.entity;

import java.util.List;

public class MapEntity {
	int width;
	int height;
	List<PlaceEntity> places;
	List<Integer> jailPlayersIndex;
	int jailZone;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<PlaceEntity> getPlaces() {
		return places;
	}
	public void setPlaces(List<PlaceEntity> places) {
		this.places = places;
	}
	public List<Integer> getJailPlayersIndex() {
		return jailPlayersIndex;
	}
	public void setJailPlayersIndex(List<Integer> jailPlayersIndex) {
		this.jailPlayersIndex = jailPlayersIndex;
	}
	public int getJailZone() {
		return jailZone;
	}
	public void setJailZone(int jailZone) {
		this.jailZone = jailZone;
	}
	
}
