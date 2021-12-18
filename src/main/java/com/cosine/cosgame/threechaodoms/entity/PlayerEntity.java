package com.cosine.cosgame.threechaodoms.entity;

import java.util.List;

public class PlayerEntity {
	int index;
	String name;
	/*
	List<CardEntity> hand;
	List<CardEntity> jail;
	*/
	List<CardEntity> play;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CardEntity> getPlay() {
		return play;
	}
	public void setPlay(List<CardEntity> play) {
		this.play = play;
	}
	
}
