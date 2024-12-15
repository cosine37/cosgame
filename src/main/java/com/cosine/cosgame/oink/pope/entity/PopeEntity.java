package com.cosine.cosgame.oink.pope.entity;

import java.util.List;

public class PopeEntity {
	int phase;
	int status;
	int round;
	int curPlayer;
	int firstPlayer;
	int deckSize;

	List<PopePlayerEntity> players;
	List<CardEntity> hand;
	List<String> logs;
	
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
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
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public int getDeckSize() {
		return deckSize;
	}
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	public List<PopePlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PopePlayerEntity> players) {
		this.players = players;
	}
	public List<CardEntity> getHand() {
		return hand;
	}
	public void setHand(List<CardEntity> hand) {
		this.hand = hand;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
}
