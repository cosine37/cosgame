package com.cosine.cosgame.oink.startups.entity;

import java.util.List;

import com.cosine.cosgame.oink.account.entity.AccountEntity;

public class EndGameEntity {
	List<String> playerNames;
	List<List<Integer>> scores;
	List<Integer> finalScore;
	List<String> endGameRewards;
	AccountEntity winner;
	
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<List<Integer>> getScores() {
		return scores;
	}
	public void setScores(List<List<Integer>> scores) {
		this.scores = scores;
	}
	public List<Integer> getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(List<Integer> finalScore) {
		this.finalScore = finalScore;
	}
	public List<String> getEndGameRewards() {
		return endGameRewards;
	}
	public void setEndGameRewards(List<String> endGameRewards) {
		this.endGameRewards = endGameRewards;
	}
	public AccountEntity getWinner() {
		return winner;
	}
	public void setWinner(AccountEntity winner) {
		this.winner = winner;
	}
}
