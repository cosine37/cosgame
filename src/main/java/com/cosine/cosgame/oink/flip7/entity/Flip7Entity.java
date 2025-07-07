package com.cosine.cosgame.oink.flip7.entity;

import java.util.List;

public class Flip7Entity {
	List<CardEntity> deck;
	List<CardEntity> discard;
	List<Flip7PlayerEntity> players;
	int round;
	int firstPlayer;
	int curPlayer;
	
	public List<CardEntity> getDeck() {
		return deck;
	}
	public void setDeck(List<CardEntity> deck) {
		this.deck = deck;
	}
	public List<CardEntity> getDiscard() {
		return discard;
	}
	public void setDiscard(List<CardEntity> discard) {
		this.discard = discard;
	}
	public List<Flip7PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<Flip7PlayerEntity> players) {
		this.players = players;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
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
}
