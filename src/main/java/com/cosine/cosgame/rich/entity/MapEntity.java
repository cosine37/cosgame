package com.cosine.cosgame.rich.entity;

import java.util.List;

public class MapEntity {
	int width;
	int height;
	List<PlaceEntity> places;
	
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
	
}
