package com.cosine.cosgame.oink.startups.entity;

import java.util.List;
import java.util.Map;

public class StartupsEntity {
	List<PlayerEntity> players;
	List<CardEntity> myHand;
	List<CardEntity> discard;
	List<EndRoundEntity> endRoundInfo;
	List<String> logs;
	
	Map<Integer, Integer> antiMonopoly;
	Map<Integer, Integer> shareholder;
	
	int round;
	int curPlayer;
	int deckSize;
	int phase;
	boolean canDraw;
	int myDrawCost;
	int myRanking;
	boolean confirmed;
	
	EndGameEntity endGameInfo;
	
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
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
	public List<CardEntity> getMyHand() {
		return myHand;
	}
	public void setMyHand(List<CardEntity> myHand) {
		this.myHand = myHand;
	}
	public int getDeckSize() {
		return deckSize;
	}
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	public boolean isCanDraw() {
		return canDraw;
	}
	public void setCanDraw(boolean canDraw) {
		this.canDraw = canDraw;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<CardEntity> getDiscard() {
		return discard;
	}
	public void setDiscard(List<CardEntity> discard) {
		this.discard = discard;
	}
	public Map<Integer, Integer> getAntiMonopoly() {
		return antiMonopoly;
	}
	public void setAntiMonopoly(Map<Integer, Integer> antiMonopoly) {
		this.antiMonopoly = antiMonopoly;
	}
	public int getMyDrawCost() {
		return myDrawCost;
	}
	public void setMyDrawCost(int myDrawCost) {
		this.myDrawCost = myDrawCost;
	}
	public Map<Integer, Integer> getShareholder() {
		return shareholder;
	}
	public void setShareholder(Map<Integer, Integer> shareholder) {
		this.shareholder = shareholder;
	}
	public List<EndRoundEntity> getEndRoundInfo() {
		return endRoundInfo;
	}
	public void setEndRoundInfo(List<EndRoundEntity> endRoundInfo) {
		this.endRoundInfo = endRoundInfo;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public EndGameEntity getEndGameInfo() {
		return endGameInfo;
	}
	public void setEndGameInfo(EndGameEntity endGameInfo) {
		this.endGameInfo = endGameInfo;
	}
	public int getMyRanking() {
		return myRanking;
	}
	public void setMyRanking(int myRanking) {
		this.myRanking = myRanking;
	}
}
