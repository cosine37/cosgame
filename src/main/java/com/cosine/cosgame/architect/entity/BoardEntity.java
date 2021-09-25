package com.cosine.cosgame.architect.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String status;
	String myIndex;
	List<String> playerNames;
	List<PlayerEntity> players;
	List<CardEntity> revealedCards;
	List<BuildingEntity> revealedBuildings;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
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
	public String getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(String myIndex) {
		this.myIndex = myIndex;
	}
	public List<CardEntity> getRevealedCards() {
		return revealedCards;
	}
	public void setRevealedCards(List<CardEntity> revealedCards) {
		this.revealedCards = revealedCards;
	}
	public List<BuildingEntity> getRevealedBuildings() {
		return revealedBuildings;
	}
	public void setRevealedBuildings(List<BuildingEntity> revealedBuildings) {
		this.revealedBuildings = revealedBuildings;
	}
	
}
