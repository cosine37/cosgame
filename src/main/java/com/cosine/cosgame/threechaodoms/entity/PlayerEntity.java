package com.cosine.cosgame.threechaodoms.entity;

import java.util.List;

public class PlayerEntity {
	int index;
	String name;
	/*
	List<CardEntity> hand;
	*/
	List<CardEntity> jail;
	List<CardEntity> play;
	List<Integer> id;
	List<String> receives;
	
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
	public List<CardEntity> getJail() {
		return jail;
	}
	public void setJail(List<CardEntity> jail) {
		this.jail = jail;
	}
	public List<Integer> getId() {
		return id;
	}
	public void setId(List<Integer> id) {
		this.id = id;
	}
	public List<String> getReceives() {
		return receives;
	}
	public void setReceives(List<String> receives) {
		this.receives = receives;
	}
	
}
