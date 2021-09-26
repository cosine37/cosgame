package com.cosine.cosgame.architect.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	String phase;
	String num1vp;
	String num3vp;
	String numBuildings;
	String handSize;
	String score;
	List<String> warehouse;
	List<CardEntity> play;
	List<BuildingEntity> buildings;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(List<String> warehouse) {
		this.warehouse = warehouse;
	}
	public List<CardEntity> getPlay() {
		return play;
	}
	public void setPlay(List<CardEntity> play) {
		this.play = play;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getNum1vp() {
		return num1vp;
	}
	public void setNum1vp(String num1vp) {
		this.num1vp = num1vp;
	}
	public String getNum3vp() {
		return num3vp;
	}
	public void setNum3vp(String num3vp) {
		this.num3vp = num3vp;
	}
	public String getNumBuildings() {
		return numBuildings;
	}
	public void setNumBuildings(String numBuildings) {
		this.numBuildings = numBuildings;
	}
	public List<BuildingEntity> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<BuildingEntity> buildings) {
		this.buildings = buildings;
	}
	public String getHandSize() {
		return handSize;
	}
	public void setHandSize(String handSize) {
		this.handSize = handSize;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}
