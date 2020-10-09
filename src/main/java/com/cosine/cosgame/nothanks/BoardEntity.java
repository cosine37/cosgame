package com.cosine.cosgame.nothanks;

import java.util.List;

public class BoardEntity {
	String status;
	String phase;
	String deckSize;
	String packCardImg;
	String packMoney;
	String id;
	String intialRevealedMoney;
	String trueMoney;
	String curPlayer;
	String lord;
	
	List<String> playerNames;
	List<String> hasPack;
	List<String> handSizes;
	List<String> revealedMoney;
	List<String> hand;
	List<String> scores;
	
	List<List<String>> allHands;
	
	public BoardEntity() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getDeckSize() {
		return deckSize;
	}
	public void setDeckSize(String deckSize) {
		this.deckSize = deckSize;
	}
	public String getPackCardImg() {
		return packCardImg;
	}
	public void setPackCardImg(String packCardImg) {
		this.packCardImg = packCardImg;
	}
	public String getPackMoney() {
		return packMoney;
	}
	public void setPackMoney(String packMoney) {
		this.packMoney = packMoney;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<String> getHasPack() {
		return hasPack;
	}
	public void setHasPack(List<String> hasPack) {
		this.hasPack = hasPack;
	}
	public List<String> getHandSizes() {
		return handSizes;
	}
	public void setHandSizes(List<String> handSizes) {
		this.handSizes = handSizes;
	}
	public List<String> getRevealedMoney() {
		return revealedMoney;
	}
	public void setRevealedMoney(List<String> revealedMoney) {
		this.revealedMoney = revealedMoney;
	}
	public String getIntialRevealedMoney() {
		return intialRevealedMoney;
	}
	public void setIntialRevealedMoney(String intialRevealedMoney) {
		this.intialRevealedMoney = intialRevealedMoney;
	}
	public String getTrueMoney() {
		return trueMoney;
	}
	public void setTrueMoney(String trueMoney) {
		this.trueMoney = trueMoney;
	}
	public List<String> getHand() {
		return hand;
	}
	public void setHand(List<String> hand) {
		this.hand = hand;
	}
	public String getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(String curPlayer) {
		this.curPlayer = curPlayer;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public List<List<String>> getAllHands() {
		return allHands;
	}
	public void setAllHands(List<List<String>> allHands) {
		this.allHands = allHands;
	}
	public List<String> getScores() {
		return scores;
	}
	public void setScores(List<String> scores) {
		this.scores = scores;
	}
	
}
