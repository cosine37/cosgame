package com.cosine.cosgame.oink.pope.entity;

import java.util.List;

import com.cosine.cosgame.oink.account.entity.AccountEntity;

public class PopePlayerEntity {
	int index;
	int phase;
	int numKey;
	int ranking;
	int target;
	boolean protect;
	boolean active;
	boolean playedThief;
	boolean confirmed;
	String name;
	
	List<CardEntity> play;
	List<CardEntity> handRevealed;
	
	AccountEntity account;
	
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
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public AccountEntity getAccount() {
		return account;
	}
	public void setAccount(AccountEntity account) {
		this.account = account;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
}
