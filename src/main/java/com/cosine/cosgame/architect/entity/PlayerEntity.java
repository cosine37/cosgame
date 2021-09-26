package com.cosine.cosgame.architect.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	String phase;
	List<String> warehouse;
	List<CardEntity> hand;
	List<CardEntity> play;
	
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
	public List<CardEntity> getHand() {
		return hand;
	}
	public void setHand(List<CardEntity> hand) {
		this.hand = hand;
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
}
