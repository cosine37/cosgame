package com.cosine.cosgame.oink.flip7.entity;

import java.util.List;

import com.cosine.cosgame.oink.flip7.Card;

public class Flip7PlayerEntity {
	int index;
	int score;
	int phase;
	
	String name;
	List<CardEntity> numCards;
	List<CardEntity> addonCards;
	List<CardEntity> specialCards;
	boolean active;
	boolean confirmed;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CardEntity> getNumCards() {
		return numCards;
	}
	public void setNumCards(List<CardEntity> numCards) {
		this.numCards = numCards;
	}
	public List<CardEntity> getAddonCards() {
		return addonCards;
	}
	public void setAddonCards(List<CardEntity> addonCards) {
		this.addonCards = addonCards;
	}
	public List<CardEntity> getSpecialCards() {
		return specialCards;
	}
	public void setSpecialCards(List<CardEntity> specialCards) {
		this.specialCards = specialCards;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
}
