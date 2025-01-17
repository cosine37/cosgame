package com.cosine.cosgame.oink.west.entity;

import java.util.List;

public class WestEntity {
	List<PlayerEntity> players;
	int status;
	int round;
	int pool;
	int winner;
	int firstPlayer;
	int curPlayer;
	List<CardEntity> assistEntity;
	List<CardEntity> myHand;
	
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
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getPool() {
		return pool;
	}
	public void setPool(int pool) {
		this.pool = pool;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public List<CardEntity> getAssistEntity() {
		return assistEntity;
	}
	public void setAssistEntity(List<CardEntity> assistEntity) {
		this.assistEntity = assistEntity;
	}
	public List<CardEntity> getMyHand() {
		return myHand;
	}
	public void setMyHand(List<CardEntity> myHand) {
		this.myHand = myHand;
	}
}
