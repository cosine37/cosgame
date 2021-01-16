package com.cosine.cosgame.towermaker;

import java.util.List;

public class Board {
	String id;
	int status;
	int curPlayerIndex;
	List<Player> players;
	List<Monster> deck;
	List<Monster> tower;
	Hero hero;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCurPlayerIndex() {
		return curPlayerIndex;
	}
	public void setCurPlayerIndex(int curPlayerIndex) {
		this.curPlayerIndex = curPlayerIndex;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Monster> getDeck() {
		return deck;
	}
	public void setDeck(List<Monster> deck) {
		this.deck = deck;
	}
	public List<Monster> getTower() {
		return tower;
	}
	public void setTower(List<Monster> tower) {
		this.tower = tower;
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
}
