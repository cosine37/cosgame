package com.cosine.cosgame.onenight;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String status;
	String round;
	String totalRounds;
	String initialRole;
	String lastSeenRole;
	String canNight;
	String initialRoleName;
	String choosePlayerNum;
	String chooseCenterNum;
	String canChooseBoth;
	String myIndex;
	String mandatory;
	String hasSkill;
	String updatedRole;
	String showUpdatedRole;
	String confirmed;
	String voted;
	String detectiveIndex;
	String detectiveRoleImg;
	
	List<String> playerNames;
	List<String> playerDisplayNames;
	List<String> playerMarks;
	List<String> centerMarks;
	List<String> rolesThisGame;
	List<String> centerMsg;
	List<String> rolesChoose;
	List<String> winPlayers;
	List<String> losePlayers;
	List<String> numVotes;
	List<String> playerVotes;
	List<String> votedOut;
	List<String> finalRoles;
	
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
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public String getCanNight() {
		return canNight;
	}
	public void setCanNight(String canNight) {
		this.canNight = canNight;
	}
	public String getInitialRoleName() {
		return initialRoleName;
	}
	public void setInitialRoleName(String initialRoleName) {
		this.initialRoleName = initialRoleName;
	}
	public String getChoosePlayerNum() {
		return choosePlayerNum;
	}
	public void setChoosePlayerNum(String choosePlayerNum) {
		this.choosePlayerNum = choosePlayerNum;
	}
	public String getChooseCenterNum() {
		return chooseCenterNum;
	}
	public void setChooseCenterNum(String chooseCenterNum) {
		this.chooseCenterNum = chooseCenterNum;
	}
	public String getCanChooseBoth() {
		return canChooseBoth;
	}
	public void setCanChooseBoth(String canChooseBoth) {
		this.canChooseBoth = canChooseBoth;
	}
	public String getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(String myIndex) {
		this.myIndex = myIndex;
	}
	public String getMandatory() {
		return mandatory;
	}
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
	public String getHasSkill() {
		return hasSkill;
	}
	public void setHasSkill(String hasSkill) {
		this.hasSkill = hasSkill;
	}
	public String getUpdatedRole() {
		return updatedRole;
	}
	public void setUpdatedRole(String updatedRole) {
		this.updatedRole = updatedRole;
	}
	public String getShowUpdatedRole() {
		return showUpdatedRole;
	}
	public void setShowUpdatedRole(String showUpdatedRole) {
		this.showUpdatedRole = showUpdatedRole;
	}
	public List<String> getCenterMsg() {
		return centerMsg;
	}
	public void setCenterMsg(List<String> centerMsg) {
		this.centerMsg = centerMsg;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
}
	public List<String> getRolesChoose() {
		return rolesChoose;
	}
	public void setRolesChoose(List<String> rolesChoose) {
		this.rolesChoose = rolesChoose;
	}
	public List<String> getWinPlayers() {
		return winPlayers;
	}
	public void setWinPlayers(List<String> winPlayers) {
		this.winPlayers = winPlayers;
	}
	public List<String> getLosePlayers() {
		return losePlayers;
	}
	public void setLosePlayers(List<String> losePlayers) {
		this.losePlayers = losePlayers;
	}
	public List<String> getNumVotes() {
		return numVotes;
	}
	public void setNumVotes(List<String> numVotes) {
		this.numVotes = numVotes;
	}
	public String getVoted() {
		return voted;
	}
	public void setVoted(String voted) {
		this.voted = voted;
	}
	public List<String> getPlayerVotes() {
		return playerVotes;
	}
	public void setPlayerVotes(List<String> playerVotes) {
		this.playerVotes = playerVotes;
	}
	public List<String> getVotedOut() {
		return votedOut;
	}
	public void setVotedOut(List<String> votedOut) {
		this.votedOut = votedOut;
	}
	public List<String> getFinalRoles() {
		return finalRoles;
	}
	public void setFinalRoles(List<String> finalRoles) {
		this.finalRoles = finalRoles;
	}
	public String getDetectiveIndex() {
		return detectiveIndex;
	}
	public void setDetectiveIndex(String detectiveIndex) {
		this.detectiveIndex = detectiveIndex;
	}
	public String getDetectiveRoleImg() {
		return detectiveRoleImg;
	}
	public void setDetectiveRoleImg(String detectiveRoleImg) {
		this.detectiveRoleImg = detectiveRoleImg;
	}
}
