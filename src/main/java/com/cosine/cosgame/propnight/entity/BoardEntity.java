package com.cosine.cosgame.propnight.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	int humanMark;
	int ghostMark;
	int status;
	int phase;
	
	int myRole;
	int myIndex;
	List<Integer> myPlaceThisTurn;
	int myNumPlaceNextTurn;
	
	List<PlayerEntity> players;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public int getHumanMark() {
		return humanMark;
	}
	public void setHumanMark(int humanMark) {
		this.humanMark = humanMark;
	}
	public int getGhostMark() {
		return ghostMark;
	}
	public void setGhostMark(int ghostMark) {
		this.ghostMark = ghostMark;
	}
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getMyRole() {
		return myRole;
	}
	public void setMyRole(int myRole) {
		this.myRole = myRole;
	}
	public int getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(int myIndex) {
		this.myIndex = myIndex;
	}
	public List<Integer> getMyPlaceThisTurn() {
		return myPlaceThisTurn;
	}
	public void setMyPlaceThisTurn(List<Integer> myPlaceThisTurn) {
		this.myPlaceThisTurn = myPlaceThisTurn;
	}
	public int getMyNumPlaceNextTurn() {
		return myNumPlaceNextTurn;
	}
	public void setMyNumPlaceNextTurn(int myNumPlaceNextTurn) {
		this.myNumPlaceNextTurn = myNumPlaceNextTurn;
	}
	
}
