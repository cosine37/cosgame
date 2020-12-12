package com.cosine.cosgame.onenight;

import java.util.List;

public class BoardEntity {
	String id;
	String status;
	String round;
	String totalRounds;
	String initialRole;
	String lastSeenRole;
	
	List<String> playerNames;
	List<String> playerDisplayNames;
	List<String> playerMarks;
	List<String> centerMarks;
	List<String> rolesThisGame;
	
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
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getTotalRounds() {
		return totalRounds;
	}
	public void setTotalRounds(String totalRounds) {
		this.totalRounds = totalRounds;
	}
	public String getInitialRole() {
		return initialRole;
	}
	public void setInitialRole(String initialRole) {
		this.initialRole = initialRole;
	}
	public String getLastSeenRole() {
		return lastSeenRole;
	}
	public void setLastSeenRole(String lastSeenRole) {
		this.lastSeenRole = lastSeenRole;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<String> getPlayerMarks() {
		return playerMarks;
	}
	public void setPlayerMarks(List<String> playerMarks) {
		this.playerMarks = playerMarks;
	}
	public List<String> getCenterMarks() {
		return centerMarks;
	}
	public void setCenterMarks(List<String> centerMarks) {
		this.centerMarks = centerMarks;
	}
	public List<String> getRolesThisGame() {
		return rolesThisGame;
	}
	public void setRolesThisGame(List<String> rolesThisGame) {
		this.rolesThisGame = rolesThisGame;
	}
	public List<String> getPlayerDisplayNames() {
		return playerDisplayNames;
	}
	public void setPlayerDisplayNames(List<String> playerDisplayNames) {
		this.playerDisplayNames = playerDisplayNames;
	}
}
