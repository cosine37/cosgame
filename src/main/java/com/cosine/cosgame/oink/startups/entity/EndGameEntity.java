package com.cosine.cosgame.oink.startups.entity;

import java.util.List;

public class EndGameEntity {
	List<String> playerNames;
	List<List<Integer>> scores;
	List<Integer> finalScore;
	
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
}
