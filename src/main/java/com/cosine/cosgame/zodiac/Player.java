package com.cosine.cosgame.zodiac;

import java.util.List;

public class Player {
	String name;
	Role role;
	int index;
	int votes;
	int disabledTurn;
	int option;
	List<Integer> voteHistory;
	List<Integer> checkHistory;
	List<Integer> sideHistory;
	List<Integer> zodiacTargets;
	List<Integer> playerTargets;
	boolean disabled;
	
	public Player() {
		votes = 0;
		disabledTurn = -1;
	}
	
	public void initialize() {
		votes = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public List<Integer> getVoteHistory() {
		return voteHistory;
	}
	public void setVoteHistory(List<Integer> voteHistory) {
		this.voteHistory = voteHistory;
	}
	public List<Integer> getCheckHistory() {
		return checkHistory;
	}
	public void setCheckHistory(List<Integer> checkHistory) {
		this.checkHistory = checkHistory;
	}
	public int getDisabledTurn() {
		return disabledTurn;
	}
	public void setDisabledTurn(int disabledTurn) {
		this.disabledTurn = disabledTurn;
	}
	public List<Integer> getZodiacTargets() {
		return zodiacTargets;
	}
	public void setZodiacTargets(List<Integer> zodiacTargets) {
		this.zodiacTargets = zodiacTargets;
	}
	public List<Integer> getPlayerTargets() {
		return playerTargets;
	}
	public void setPlayerTargets(List<Integer> playerTargets) {
		this.playerTargets = playerTargets;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Integer> getSideHistory() {
		return sideHistory;
	}
	public void setSideHistory(List<Integer> sideHistory) {
		this.sideHistory = sideHistory;
	}
	public int getOption() {
		return option;
	}
	public void setOption(int option) {
		this.option = option;
	}
}
