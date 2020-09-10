package com.cosine.cosgame.nothanks;

import java.util.List;

public class BoardEntity {
	String status;
	String phase;
	String deckSize;
	String packCardImg;
	String packMoney;
	String id;
	
	List<String> playerNames;
	List<String> hasPack;
	List<String> handSizes;
	List<String> revealedMoney;
	
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
	
}
