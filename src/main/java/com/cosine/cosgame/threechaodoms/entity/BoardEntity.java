package com.cosine.cosgame.threechaodoms.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	int status;
	int phase;
	int weiPos;
	int hanPos;
	int curPlayer;
	int numExile;
	int numDeck;
	int numTomb;
	LoggerEntity logger;
	CardEntity topTomb;
	List<PlayerEntity> players;
	List<CardEntity> tavern;
	List<CardEntity> myHand;
	List<CardEntity> myJail;
	List<Integer> myID;
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
	public int getWeiPos() {
		return weiPos;
	}
	public void setWeiPos(int weiPos) {
		this.weiPos = weiPos;
	}
	public int getHanPos() {
		return hanPos;
	}
	public void setHanPos(int hanPos) {
		this.hanPos = hanPos;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public List<CardEntity> getTavern() {
		return tavern;
	}
	public void setTavern(List<CardEntity> tavern) {
		this.tavern = tavern;
	}
	public List<CardEntity> getMyHand() {
		return myHand;
	}
	public void setMyHand(List<CardEntity> myHand) {
		this.myHand = myHand;
	}
	public List<CardEntity> getMyJail() {
		return myJail;
	}
	public void setMyJail(List<CardEntity> myJail) {
		this.myJail = myJail;
	}
	public List<Integer> getMyID() {
		return myID;
	}
	public void setMyID(List<Integer> myID) {
		this.myID = myID;
	}
	public int getNumExile() {
		return numExile;
	}
	public void setNumExile(int numExile) {
		this.numExile = numExile;
	}
	public int getNumDeck() {
		return numDeck;
	}
	public void setNumDeck(int numDeck) {
		this.numDeck = numDeck;
	}
	public int getNumTomb() {
		return numTomb;
	}
	public void setNumTomb(int numTomb) {
		this.numTomb = numTomb;
	}
	public CardEntity getTopTomb() {
		return topTomb;
	}
	public void setTopTomb(CardEntity topTomb) {
		this.topTomb = topTomb;
	}
	public LoggerEntity getLogger() {
		return logger;
	}
	public void setLogger(LoggerEntity logger) {
		this.logger = logger;
	}
}
