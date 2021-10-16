package com.cosine.cosgame.gardenwar;

import java.util.ArrayList;
import java.util.List;

public class Board {
	String id;
	String lord;
	int firstPlayer;
	int curPlayer;
	int status;
	int roundCount;
	List<Player> players;
	List<Card> supplyDeck;
	List<Card> supply;
	List<List<Card>> basicPiles;
	
	public Board() {
		players = new ArrayList<>();
		supplyDeck = new ArrayList<>();
		supply = new ArrayList<>();
		basicPiles = new ArrayList<>();
	}
	
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
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Card> getSupplyDeck() {
		return supplyDeck;
	}
	public void setSupplyDeck(List<Card> supplyDeck) {
		this.supplyDeck = supplyDeck;
	}
	public List<Card> getSupply() {
		return supply;
	}
	public void setSupply(List<Card> supply) {
		this.supply = supply;
	}
	public List<List<Card>> getBasicPiles() {
		return basicPiles;
	}
	public void setBasicPiles(List<List<Card>> basicPiles) {
		this.basicPiles = basicPiles;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRoundCount() {
		return roundCount;
	}
	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}
}
