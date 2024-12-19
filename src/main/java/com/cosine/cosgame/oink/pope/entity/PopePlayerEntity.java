package com.cosine.cosgame.oink.pope.entity;

import java.util.List;

public class PopePlayerEntity {
	int index;
	int phase;
	int numKey;
	boolean protect;
	boolean active;
	boolean playedThief;
	boolean confirmed;
	String name;
	
	List<CardEntity> play;
	List<CardEntity> handRevealed;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getNumKey() {
		return numKey;
	}
	public void setNumKey(int numKey) {
		this.numKey = numKey;
	}
	public boolean isProtect() {
		return protect;
	}
	public void setProtect(boolean protect) {
		this.protect = protect;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isPlayedThief() {
		return playedThief;
	}
	public void setPlayedThief(boolean playedThief) {
		this.playedThief = playedThief;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CardEntity> getPlay() {
		return play;
	}
	public void setPlay(List<CardEntity> play) {
		this.play = play;
	}
	public List<CardEntity> getHandRevealed() {
		return handRevealed;
	}
	public void setHandRevealed(List<CardEntity> handRevealed) {
		this.handRevealed = handRevealed;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
}
