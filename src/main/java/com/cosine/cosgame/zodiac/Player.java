package com.cosine.cosgame.zodiac;

import java.util.List;

public class Player {
	String name;
	Role role;
	int votes;
	List<Integer> voteHistory;
	List<Integer> checkHistory;
	
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
}
