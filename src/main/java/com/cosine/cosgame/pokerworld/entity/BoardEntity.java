package com.cosine.cosgame.pokerworld.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String myCards;
	String dominantRank;
	String dominantSuit;
	String currentSuit;
	String dominantSuitLastRound;
	String dominantCard;
	int myIndex;
	int numDominant;
	int curClaimedPlayer;
	int curPlayer;
	int firstPlayer;
	int numPlay;
	int winPlayer;
	int attackerPointsGained;
	int status;
	int round;
	int totalRounds;
	int phase;
	int biggestRank;
	int extraCards;
	int gameMode;
	int numWzRevealed;
	int numJeRevealed;
	int numBmRevealed;
	int numDrRevealed;
	int numFrRevealed;
	int numMeRevealed;
	int myCircusIndex;

	boolean confirmed;
	boolean confirmedNextTurn;
	boolean fiveTenBonus;
	
	List<Integer> sequence;
	List<PlayerEntity> players;
	List<Integer> playable;
	List<Integer> myChosenSkins;
	List<String> myEndGameRewards;
	List<Integer> myPlayedIndex;

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
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public String getMyCards() {
		return myCards;
	}
	public void setMyCards(String myCards) {
		this.myCards = myCards;
	}
	public List<Integer> getSequence() {
		return sequence;
	}
	public void setSequence(List<Integer> sequence) {
		this.sequence = sequence;
	}
	public String getDominantRank() {
		return dominantRank;
	}
	public void setDominantRank(String dominantRank) {
		this.dominantRank = dominantRank;
	}
	public String getDominantSuit() {
		return dominantSuit;
	}
	public void setDominantSuit(String dominantSuit) {
		this.dominantSuit = dominantSuit;
	}
	public int getNumDominant() {
		return numDominant;
	}
	public void setNumDominant(int numDominant) {
		this.numDominant = numDominant;
	}
	public int getCurClaimedPlayer() {
		return curClaimedPlayer;
	}
	public void setCurClaimedPlayer(int curClaimedPlayer) {
		this.curClaimedPlayer = curClaimedPlayer;
	}
	public int getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(int myIndex) {
		this.myIndex = myIndex;
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
	public int getNumPlay() {
		return numPlay;
	}
	public void setNumPlay(int numPlay) {
		this.numPlay = numPlay;
	}
	public int getWinPlayer() {
		return winPlayer;
	}
	public void setWinPlayer(int winPlayer) {
		this.winPlayer = winPlayer;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public boolean isConfirmedNextTurn() {
		return confirmedNextTurn;
	}
	public void setConfirmedNextTurn(boolean confirmedNextTurn) {
		this.confirmedNextTurn = confirmedNextTurn;
	}
	public int getAttackerPointsGained() {
		return attackerPointsGained;
	}
	public void setAttackerPointsGained(int attackerPointsGained) {
		this.attackerPointsGained = attackerPointsGained;
	}
	public int getBiggestRank() {
		return biggestRank;
	}
	public void setBiggestRank(int biggestRank) {
		this.biggestRank = biggestRank;
	}
	public int getGameMode() {
		return gameMode;
	}
	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}
	public String getDominantCard() {
		return dominantCard;
	}
	public void setDominantCard(String dominantCard) {
		this.dominantCard = dominantCard;
	}
	public List<Integer> getPlayable() {
		return playable;
	}
	public void setPlayable(List<Integer> playable) {
		this.playable = playable;
	}
	public int getNumWzRevealed() {
		return numWzRevealed;
	}
	public void setNumWzRevealed(int numWzRevealed) {
		this.numWzRevealed = numWzRevealed;
	}
	public int getNumJeRevealed() {
		return numJeRevealed;
	}
	public void setNumJeRevealed(int numJeRevealed) {
		this.numJeRevealed = numJeRevealed;
	}
	public int getTotalRounds() {
		return totalRounds;
	}
	public void setTotalRounds(int totalRounds) {
		this.totalRounds = totalRounds;
	}
	public String getDominantSuitLastRound() {
		return dominantSuitLastRound;
	}
	public void setDominantSuitLastRound(String dominantSuitLastRound) {
		this.dominantSuitLastRound = dominantSuitLastRound;
	}
	public String getCurrentSuit() {
		return currentSuit;
	}
	public void setCurrentSuit(String currentSuit) {
		this.currentSuit = currentSuit;
	}
	public int getExtraCards() {
		return extraCards;
	}
	public void setExtraCards(int extraCards) {
		this.extraCards = extraCards;
	}
	public int getNumBmRevealed() {
		return numBmRevealed;
	}
	public void setNumBmRevealed(int numBmRevealed) {
		this.numBmRevealed = numBmRevealed;
	}
	public int getNumDrRevealed() {
		return numDrRevealed;
	}
	public void setNumDrRevealed(int numDrRevealed) {
		this.numDrRevealed = numDrRevealed;
	}
	public int getNumFrRevealed() {
		return numFrRevealed;
	}
	public void setNumFrRevealed(int numFrRevealed) {
		this.numFrRevealed = numFrRevealed;
	}
	public List<Integer> getMyChosenSkins() {
		return myChosenSkins;
	}
	public void setMyChosenSkins(List<Integer> myChosenSkins) {
		this.myChosenSkins = myChosenSkins;
	}
	public List<String> getMyEndGameRewards() {
		return myEndGameRewards;
	}
	public void setMyEndGameRewards(List<String> myEndGameRewards) {
		this.myEndGameRewards = myEndGameRewards;
	}
	public boolean isFiveTenBonus() {
		return fiveTenBonus;
	}
	public void setFiveTenBonus(boolean fiveTenBonus) {
		this.fiveTenBonus = fiveTenBonus;
	}
	public int getNumMeRevealed() {
		return numMeRevealed;
	}
	public void setNumMeRevealed(int numMeRevealed) {
		this.numMeRevealed = numMeRevealed;
	}
	public int getMyCircusIndex() {
		return myCircusIndex;
	}
	public void setMyCircusIndex(int myCircusIndex) {
		this.myCircusIndex = myCircusIndex;
	}
	public List<Integer> getMyPlayedIndex() {
		return myPlayedIndex;
	}
	public void setMyPlayedIndex(List<Integer> myPlayedIndex) {
		this.myPlayedIndex = myPlayedIndex;
	}
}
