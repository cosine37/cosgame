package com.cosine.cosgame.pokewhat;

import java.util.List;

public class BoardEntity {
	List<List<String>> allCards;
	List<List<String>> playedCards;
	List<String> playerNames;
	List<String> pm;
	List<String> hp;
	
	String status;
	String round;
	String turn;
	String id;
	String lord;
	
	public List<List<String>> getAllCards() {
		return allCards;
	}
	public void setAllCards(List<List<String>> allCards) {
		this.allCards = allCards;
	}
	public List<List<String>> getPlayedCards() {
		return playedCards;
	}
	public void setPlayedCards(List<List<String>> playedCards) {
		this.playedCards = playedCards;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<String> getPm() {
		return pm;
	}
	public void setPm(List<String> pm) {
		this.pm = pm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getTurn() {
		return turn;
	}
	public void setTurn(String turn) {
		this.turn = turn;
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
	public List<String> getHp() {
		return hp;
	}
	public void setHp(List<String> hp) {
		this.hp = hp;
	}
}
