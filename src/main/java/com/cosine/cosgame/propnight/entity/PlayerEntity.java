package com.cosine.cosgame.propnight.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	int role;
	int hp;
	int placeThisTurn;
	List<Integer> visitedPlaces;
	List<Integer> availablePlaces;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getPlaceThisTurn() {
		return placeThisTurn;
	}
	public void setPlaceThisTurn(int placeThisTurn) {
		this.placeThisTurn = placeThisTurn;
	}
	public List<Integer> getVisitedPlaces() {
		return visitedPlaces;
	}
	public void setVisitedPlaces(List<Integer> visitedPlaces) {
		this.visitedPlaces = visitedPlaces;
	}
	public List<Integer> getAvailablePlaces() {
		return availablePlaces;
	}
	public void setAvailablePlaces(List<Integer> availablePlaces) {
		this.availablePlaces = availablePlaces;
	}
	
	
}
