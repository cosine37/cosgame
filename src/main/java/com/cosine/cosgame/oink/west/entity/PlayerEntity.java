package com.cosine.cosgame.oink.west.entity;

import java.util.List;

import com.cosine.cosgame.oink.account.entity.AccountEntity;

public class PlayerEntity {
	String name;
	int index;
	int phase;
	int coins;
	int rank;
	boolean stillIn;
	boolean alive;
	boolean confirmed;
	List<CardEntity> discard;
	List<CardEntity> revealed;
	
	AccountEntity account;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public boolean isStillIn() {
		return stillIn;
	}
	public void setStillIn(boolean stillIn) {
		this.stillIn = stillIn;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public List<CardEntity> getDiscard() {
		return discard;
	}
	public void setDiscard(List<CardEntity> discard) {
		this.discard = discard;
	}
	public List<CardEntity> getRevealed() {
		return revealed;
	}
	public void setRevealed(List<CardEntity> revealed) {
		this.revealed = revealed;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public AccountEntity getAccount() {
		return account;
	}
	public void setAccount(AccountEntity account) {
		this.account = account;
	}
}
