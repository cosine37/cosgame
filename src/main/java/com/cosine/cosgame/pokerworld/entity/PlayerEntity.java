package com.cosine.cosgame.pokerworld.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	String playedCards;
	boolean confirmedNextTurn;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlayedCards() {
		return playedCards;
	}
	public void setPlayedCards(String playedCards) {
		this.playedCards = playedCards;
	}
	public boolean isConfirmedNextTurn() {
		return confirmedNextTurn;
	}
	public void setConfirmedNextTurn(boolean confirmedNextTurn) {
		this.confirmedNextTurn = confirmedNextTurn;
	}
}
