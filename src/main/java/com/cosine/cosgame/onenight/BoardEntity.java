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
}
