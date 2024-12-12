package com.cosine.cosgame.oink.grove.entity;

import java.util.List;

public class GroveEntity {
	int phase;
	int status;
	int round;
	int curPlayer;
	int firstPlayer;
	int murIndex;
	boolean confirmed;
	
	List<GrovePlayerEntity> players;
	List<RoleEntity> suspects;
	List<RoleEntity> victims;
	
	List<RoleEntity> myOutsiders;
	
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public List<GrovePlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<GrovePlayerEntity> players) {
		this.players = players;
	}
	public List<RoleEntity> getSuspects() {
		return suspects;
	}
	public void setSuspects(List<RoleEntity> suspects) {
		this.suspects = suspects;
	}
	public List<RoleEntity> getVictims() {
		return victims;
	}
	public void setVictims(List<RoleEntity> victims) {
		this.victims = victims;
	}
	public List<RoleEntity> getMyOutsiders() {
		return myOutsiders;
	}
	public void setMyOutsiders(List<RoleEntity> myOutsiders) {
		this.myOutsiders = myOutsiders;
	}
	public int getMurIndex() {
		return murIndex;
	}
	public void setMurIndex(int murIndex) {
		this.murIndex = murIndex;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
}
