package com.cosine.cosgame.belltower.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String morningMsg;
	String myDisplayName;
	int status;
	int phase;
	int numDay;
	int sequence;
	int firstNominator;
	int curNominator;
	int curVoter;
	int nominated;
	int winFaction;
	
	int myIndex;
	RoleEntity myRole;
	String myLastNightMsg;
	List<String> myLogs;
	List<String> myAvailableCharacters;
	String executionMsg;
	List<Integer> voteResults;
	List<RoleEntity> allRoles;
	
	boolean confirmedDay;
	boolean confirmedNight;
	
	List<PlayerEntity> players;

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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getNumDay() {
		return numDay;
	}
	public void setNumDay(int numDay) {
		this.numDay = numDay;
	}
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public RoleEntity getMyRole() {
		return myRole;
	}
	public void setMyRole(RoleEntity myRole) {
		this.myRole = myRole;
	}
	public String getMyLastNightMsg() {
		return myLastNightMsg;
	}
	public void setMyLastNightMsg(String myLastNightMsg) {
		this.myLastNightMsg = myLastNightMsg;
	}
	public List<String> getMyLogs() {
		return myLogs;
	}
	public void setMyLogs(List<String> myLogs) {
		this.myLogs = myLogs;
	}
	public String getMorningMsg() {
		return morningMsg;
	}
	public void setMorningMsg(String morningMsg) {
		this.morningMsg = morningMsg;
	}
	public String getMyDisplayName() {
		return myDisplayName;
	}
	public void setMyDisplayName(String myDisplayName) {
		this.myDisplayName = myDisplayName;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getFirstNominator() {
		return firstNominator;
	}
	public void setFirstNominator(int firstNominator) {
		this.firstNominator = firstNominator;
	}
	public int getCurNominator() {
		return curNominator;
	}
	public void setCurNominator(int curNominator) {
		this.curNominator = curNominator;
	}
	public int getCurVoter() {
		return curVoter;
	}
	public void setCurVoter(int curVoter) {
		this.curVoter = curVoter;
	}
	public int getNominated() {
		return nominated;
	}
	public void setNominated(int nominated) {
		this.nominated = nominated;
	}
	public int getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(int myIndex) {
		this.myIndex = myIndex;
	}
	public List<String> getMyAvailableCharacters() {
		return myAvailableCharacters;
	}
	public void setMyAvailableCharacters(List<String> myAvailableCharacters) {
		this.myAvailableCharacters = myAvailableCharacters;
	}
	public String getExecutionMsg() {
		return executionMsg;
	}
	public void setExecutionMsg(String executionMsg) {
		this.executionMsg = executionMsg;
	}
	public List<Integer> getVoteResults() {
		return voteResults;
	}
	public void setVoteResults(List<Integer> voteResults) {
		this.voteResults = voteResults;
	}
	public boolean isConfirmedDay() {
		return confirmedDay;
	}
	public void setConfirmedDay(boolean confirmedDay) {
		this.confirmedDay = confirmedDay;
	}
	public boolean isConfirmedNight() {
		return confirmedNight;
	}
	public void setConfirmedNight(boolean confirmedNight) {
		this.confirmedNight = confirmedNight;
	}
	public int getWinFaction() {
		return winFaction;
	}
	public void setWinFaction(int winFaction) {
		this.winFaction = winFaction;
	}
	public List<RoleEntity> getAllRoles() {
		return allRoles;
	}
	public void setAllRoles(List<RoleEntity> allRoles) {
		this.allRoles = allRoles;
	}
	
}
