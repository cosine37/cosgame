package com.cosine.cosgame.architect.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String status;
	String myIndex;
	String num3vp;
	String num1vp;
	String myScore;
	String myNum1vp;
	String myNum3vp;
	String curPlayerIndex;
	String numBuildingFinish;
	List<String> playerNames;
	List<PlayerEntity> players;
	List<CardEntity> revealedCards;
	List<BuildingEntity> revealedBuildings;
	List<BuildingEntity> myBuildings;
	List<CardEntity> myHand;

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
	public String getNum3vp() {
		return num3vp;
	}
	public void setNum3vp(String num3vp) {
		this.num3vp = num3vp;
	}
	public String getNum1vp() {
		return num1vp;
	}
	public void setNum1vp(String num1vp) {
		this.num1vp = num1vp;
	}
	public List<BuildingEntity> getMyBuildings() {
		return myBuildings;
	}
	public void setMyBuildings(List<BuildingEntity> myBuildings) {
		this.myBuildings = myBuildings;
	}
	public List<CardEntity> getMyHand() {
		return myHand;
	}
	public void setMyHand(List<CardEntity> myHand) {
		this.myHand = myHand;
	}
	public String getMyScore() {
		return myScore;
	}
	public void setMyScore(String myScore) {
		this.myScore = myScore;
	}
	public String getMyNum1vp() {
		return myNum1vp;
	}
	public void setMyNum1vp(String myNum1vp) {
		this.myNum1vp = myNum1vp;
	}
	public String getMyNum3vp() {
		return myNum3vp;
	}
	public void setMyNum3vp(String myNum3vp) {
		this.myNum3vp = myNum3vp;
	}
	public String getCurPlayerIndex() {
		return curPlayerIndex;
	}
	public void setCurPlayerIndex(String curPlayerIndex) {
		this.curPlayerIndex = curPlayerIndex;
	}
	public String getNumBuildingFinish() {
		return numBuildingFinish;
	}
	public void setNumBuildingFinish(String numBuildingFinish) {
		this.numBuildingFinish = numBuildingFinish;
	}
	
}
