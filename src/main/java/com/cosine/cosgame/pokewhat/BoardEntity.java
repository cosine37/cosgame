package com.cosine.cosgame.pokewhat;

import java.util.List;

public class BoardEntity {
	List<List<String>> allCards;
	List<List<String>> playedCards;
	List<List<String>> frameTargets;
	List<String> frameType;
	List<String> frameTime;
	List<String> frameImg;
	List<String> playerNames;
	List<String> pm;
	List<String> pmNames;
	List<String> pmSizes;
	List<String> hp;
	List<String> scores;
	List<String> ancient;
	List<String> playerAncients;
	List<String> scoreLastRound;
	List<String> avatars;
	List<String> playerAvatars;
	List<String> pmToChoose;
	List<String> pmToChooseNames;
	List<String> pmFromPool;
	List<String> pmFromPoolNames;
	List<String> logs;
	
	
	String gameEndScore;
	String curPlayer;
	String confirmed;
	String status;
	String phase;
	String round;
	String turn;
	String id;
	String lord;
	String hasBot;
	String myIndex;
	String lastMove;
	String deckSize;
	String ScoringMsg;
	String ancientSize;
	String roundEndMsg;
	String animationId;
	String animationType;
	
	
	
	public List<List<String>> getAllCards() {
		return allCards;
	}
	public void setAllCards(List<List<String>> allCards) {
		this.allCards = allCards;
	}
	public List<List<String>> getPlayedCards() {
		return playedCards;
	}
	public void setPlayedCards(List<List<String>> playedCards) {
		this.playedCards = playedCards;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<String> getPm() {
		return pm;
	}
	public void setPm(List<String> pm) {
		this.pm = pm;
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
	public String getTurn() {
		return turn;
	}
	public void setTurn(String turn) {
		this.turn = turn;
	}
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
	public List<String> getHp() {
		return hp;
	}
	public void setHp(List<String> hp) {
		this.hp = hp;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public List<String> getScores() {
		return scores;
	}
	public void setScores(List<String> scores) {
		this.scores = scores;
	}
	public String getLastMove() {
		return lastMove;
	}
	public void setLastMove(String lastMove) {
		this.lastMove = lastMove;
	}
	public List<String> getAncient() {
		return ancient;
	}
	public void setAncient(List<String> ancient) {
		this.ancient = ancient;
	}
	public String getDeckSize() {
		return deckSize;
	}
	public void setDeckSize(String deckSize) {
		this.deckSize = deckSize;
	}
	public String getAncientSize() {
		return ancientSize;
	}
	public void setAncientSize(String ancientSize) {
		this.ancientSize = ancientSize;
	}
	public List<String> getScoreLastRound() {
		return scoreLastRound;
	}
	public void setScoreLastRound(List<String> scoreLastRound) {
		this.scoreLastRound = scoreLastRound;
	}
	public String getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(String curPlayer) {
		this.curPlayer = curPlayer;
	}
	public List<String> getAvatars() {
		return avatars;
	}
	public void setAvatars(List<String> avatars) {
		this.avatars = avatars;
	}
	public List<String> getPlayerAvatars() {
		return playerAvatars;
	}
	public void setPlayerAvatars(List<String> playerAvatars) {
		this.playerAvatars = playerAvatars;
	}
	public List<String> getPmToChoose() {
		return pmToChoose;
	}
	public void setPmToChoose(List<String> pmToChoose) {
		this.pmToChoose = pmToChoose;
	}
	public List<String> getPmToChooseNames() {
		return pmToChooseNames;
	}
	public void setPmToChooseNames(List<String> pmToChooseNames) {
		this.pmToChooseNames = pmToChooseNames;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public List<String> getPmNames() {
		return pmNames;
	}
	public void setPmNames(List<String> pmNames) {
		this.pmNames = pmNames;
	}
	public List<String> getPlayerAncients() {
		return playerAncients;
	}
	public void setPlayerAncients(List<String> playerAncients) {
		this.playerAncients = playerAncients;
	}
	public String getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(String myIndex) {
		this.myIndex = myIndex;
	}
	public String getHasBot() {
		return hasBot;
	}
	public void setHasBot(String hasBot) {
		this.hasBot = hasBot;
	}
	public List<String> getPmSizes() {
		return pmSizes;
	}
	public void setPmSizes(List<String> pmSizes) {
		this.pmSizes = pmSizes;
	}
	public String getGameEndScore() {
		return gameEndScore;
	}
	public void setGameEndScore(String gameEndScore) {
		this.gameEndScore = gameEndScore;
	}
	public List<String> getPmFromPool() {
		return pmFromPool;
	}
	public void setPmFromPool(List<String> pmFromPool) {
		this.pmFromPool = pmFromPool;
	}
	public List<String> getPmFromPoolNames() {
		return pmFromPoolNames;
	}
	public void setPmFromPoolNames(List<String> pmFromPoolNames) {
		this.pmFromPoolNames = pmFromPoolNames;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getRoundEndMsg() {
		return roundEndMsg;
	}
	public void setRoundEndMsg(String roundEndMsg) {
		this.roundEndMsg = roundEndMsg;
	}
	public String getScoringMsg() {
		return ScoringMsg;
	}
	public void setScoringMsg(String scoringMsg) {
		ScoringMsg = scoringMsg;
	}
	public String getAnimationId() {
		return animationId;
	}
	public void setAnimationId(String animationId) {
		this.animationId = animationId;
	}
	public String getAnimationType() {
		return animationType;
	}
	public void setAnimationType(String animationType) {
		this.animationType = animationType;
	}
	public List<List<String>> getFrameTargets() {
		return frameTargets;
	}
	public void setFrameTargets(List<List<String>> frameTargets) {
		this.frameTargets = frameTargets;
	}
	public List<String> getFrameType() {
		return frameType;
	}
	public void setFrameType(List<String> frameType) {
		this.frameType = frameType;
	}
	public List<String> getFrameTime() {
		return frameTime;
	}
	public void setFrameTime(List<String> frameTime) {
		this.frameTime = frameTime;
	}
	public List<String> getFrameImg() {
		return frameImg;
	}
	public void setFrameImg(List<String> frameImg) {
		this.frameImg = frameImg;
	}
}
