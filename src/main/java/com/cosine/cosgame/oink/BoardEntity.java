package com.cosine.cosgame.oink;

import java.util.List;

import com.cosine.cosgame.oink.account.entity.AccountEntity;
import com.cosine.cosgame.oink.grove.entity.GroveEntity;
import com.cosine.cosgame.oink.pope.entity.PopeEntity;
import com.cosine.cosgame.oink.startups.entity.StartupsEntity;
import com.cosine.cosgame.oink.west.entity.WestEntity;

public class BoardEntity {
	String id;
	String lord;
	int game;
	int status;
	
	List<String> playerNames;
	List<AccountEntity> accounts;

	StartupsEntity startups;
	GroveEntity grove;
	PopeEntity pope;
	WestEntity west;
	
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
	public int getGame() {
		return game;
	}
	public void setGame(int game) {
		this.game = game;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public StartupsEntity getStartups() {
		return startups;
	}
	public void setStartups(StartupsEntity startups) {
		this.startups = startups;
	}
	public List<AccountEntity> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}
	public GroveEntity getGrove() {
		return grove;
	}
	public void setGrove(GroveEntity grove) {
		this.grove = grove;
	}
	public PopeEntity getPope() {
		return pope;
	}
	public void setPope(PopeEntity pope) {
		this.pope = pope;
	}
	public WestEntity getWest() {
		return west;
	}
	public void setWest(WestEntity west) {
		this.west = west;
	}
}
