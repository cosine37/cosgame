package com.cosine.cosgame.oink.pope.entity;

import java.util.List;

public class PopeEntity {
	int phase;
	int status;
	int round;
	int curPlayer;
	int firstPlayer;
	int deckSize;
	int myIndex;
	int gameEndKeys;
	String endRoundMsg;
	String targetedMsg;
	
	List<Integer> rankIndex;
	List<String> endGameRewards;

	List<PopePlayerEntity> players;
	List<CardEntity> hand;
	List<String> logs;
	List<CardEntity> resolveCards;
	List<String> resolveMsgs;
	
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
	public int getDeckSize() {
		return deckSize;
	}
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	public List<PopePlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PopePlayerEntity> players) {
		this.players = players;
	}
	public List<CardEntity> getHand() {
		return hand;
	}
	public void setHand(List<CardEntity> hand) {
		this.hand = hand;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public String getEndRoundMsg() {
		return endRoundMsg;
	}
	public void setEndRoundMsg(String endRoundMsg) {
		this.endRoundMsg = endRoundMsg;
	}
	public int getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(int myIndex) {
		this.myIndex = myIndex;
	}
	public int getGameEndKeys() {
		return gameEndKeys;
	}
	public void setGameEndKeys(int gameEndKeys) {
		this.gameEndKeys = gameEndKeys;
	}
	public List<Integer> getRankIndex() {
		return rankIndex;
	}
	public void setRankIndex(List<Integer> rankIndex) {
		this.rankIndex = rankIndex;
	}
	public List<String> getEndGameRewards() {
		return endGameRewards;
	}
	public void setEndGameRewards(List<String> endGameRewards) {
		this.endGameRewards = endGameRewards;
	}
	public String getTargetedMsg() {
		return targetedMsg;
	}
	public void setTargetedMsg(String targetedMsg) {
		this.targetedMsg = targetedMsg;
	}
	public List<CardEntity> getResolveCards() {
		return resolveCards;
	}
	public void setResolveCards(List<CardEntity> resolveCards) {
		this.resolveCards = resolveCards;
	}
	public List<String> getResolveMsgs() {
		return resolveMsgs;
	}
	public void setResolveMsgs(List<String> resolveMsgs) {
		this.resolveMsgs = resolveMsgs;
	}
}
