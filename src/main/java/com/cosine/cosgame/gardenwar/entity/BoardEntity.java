package com.cosine.cosgame.gardenwar.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	int status;
	int phase;
	int curPlayer;
	int myIndex;
	List<PlayerEntity> players;
	List<CardEntity> myHand;
	boolean canAutoPlay;
	
	int curPlayerSun;
	int curPlayerPea;
	List<CardEntity> curPlayerPlay;
	List<CardEntity> baseCards;
	List<CardEntity> supply;
	List<Boolean> curPlayerCanBuy;
	
	int askType;
	int askSubType;
	String askMsg;

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
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public List<CardEntity> getMyHand() {
		return myHand;
	}
	public void setMyHand(List<CardEntity> myHand) {
		this.myHand = myHand;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCurPlayerSun() {
		return curPlayerSun;
	}
	public void setCurPlayerSun(int curPlayerSun) {
		this.curPlayerSun = curPlayerSun;
	}
	public int getCurPlayerPea() {
		return curPlayerPea;
	}
	public void setCurPlayerPea(int curPlayerPea) {
		this.curPlayerPea = curPlayerPea;
	}
	public List<CardEntity> getCurPlayerPlay() {
		return curPlayerPlay;
	}
	public void setCurPlayerPlay(List<CardEntity> curPlayerPlay) {
		this.curPlayerPlay = curPlayerPlay;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(int myIndex) {
		this.myIndex = myIndex;
	}
	public boolean isCanAutoPlay() {
		return canAutoPlay;
	}
	public void setCanAutoPlay(boolean canAutoPlay) {
		this.canAutoPlay = canAutoPlay;
	}
	public List<Boolean> getCurPlayerCanBuy() {
		return curPlayerCanBuy;
	}
	public void setCurPlayerCanBuy(List<Boolean> curPlayerCanBuy) {
		this.curPlayerCanBuy = curPlayerCanBuy;
	}
	public List<CardEntity> getBaseCards() {
		return baseCards;
	}
	public void setBaseCards(List<CardEntity> baseCards) {
		this.baseCards = baseCards;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<CardEntity> getSupply() {
		return supply;
	}
	public void setSupply(List<CardEntity> supply) {
		this.supply = supply;
	}
	public int getAskType() {
		return askType;
	}
	public void setAskType(int askType) {
		this.askType = askType;
	}
	public int getAskSubType() {
		return askSubType;
	}
	public void setAskSubType(int askSubType) {
		this.askSubType = askSubType;
	}
	public String getAskMsg() {
		return askMsg;
	}
	public void setAskMsg(String askMsg) {
		this.askMsg = askMsg;
	}
	
}
