package com.cosine.cosgame.pokerworld.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String status;
	String myCards;
	String dominantRank;
	String dominantSuit;
	int myIndex;
	int numDominant;
	int curClaimedPlayer;
	
	List<Integer> sequence;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public String getMyCards() {
		return myCards;
	}
	public void setMyCards(String myCards) {
		this.myCards = myCards;
	}
	public List<Integer> getSequence() {
		return sequence;
	}
	public void setSequence(List<Integer> sequence) {
		this.sequence = sequence;
	}
	public String getDominantRank() {
		return dominantRank;
	}
	public void setDominantRank(String dominantRank) {
		this.dominantRank = dominantRank;
	}
	public String getDominantSuit() {
		return dominantSuit;
	}
	public void setDominantSuit(String dominantSuit) {
		this.dominantSuit = dominantSuit;
	}
	public int getNumDominant() {
		return numDominant;
	}
	public void setNumDominant(int numDominant) {
		this.numDominant = numDominant;
	}
	public int getCurClaimedPlayer() {
		return curClaimedPlayer;
	}
	public void setCurClaimedPlayer(int curClaimedPlayer) {
		this.curClaimedPlayer = curClaimedPlayer;
	}
	public int getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(int myIndex) {
		this.myIndex = myIndex;
	}
	
}
