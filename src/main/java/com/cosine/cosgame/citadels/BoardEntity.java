package com.cosine.cosgame.citadels;

import java.util.List;

public class BoardEntity {
	List<String> playerNames;
	List<List<String>> built;
	List<List<String>> specialHands;
	List<List<String>> askBuiltInfo;
	List<String> hand;
	List<String> buildable;
	List<String> coins;
	List<String> handSizes;
	List<String> revealedCards;
	List<String> roleNums;
	List<String> roleOwners;
	List<String> roleImgs;
	List<String> roleRevealed;
	List<String> logs;
	List<String> skillButtons;
	List<String> askLs;
	List<String> canUseRoleSkill;
	List<String> canUseCardSkill;
	List<String> scores;
	List<String> netScores;
	List<String> extraScores;
	List<String> tempRevealedTop;
	
	String phase;
	String bank;
	String deckSize;
	String status;
	String roundCount;
	String curPlayer;
	String curRole;
	String crown;
	String lord;
	String isLord;
	String id;
	String lastRound;
	String askType;
	String askMsg;
	String askId;
	String askBuiltIndex;
	String killedRole;
	String stealedRole;
	String yourRole;
	String chooseOrDiscard;
	String finishCount;
	String regicide;

	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<List<String>> getBuilt() {
		return built;
	}
	public void setBuilt(List<List<String>> built) {
		this.built = built;
	}
	public List<String> getHand() {
		return hand;
	}
	public void setHand(List<String> hand) {
		this.hand = hand;
	}
	public List<String> getBuildable() {
		return buildable;
	}
	public void setBuildable(List<String> buildable) {
		this.buildable = buildable;
	}
	public List<String> getCoins() {
		return coins;
	}
	public void setCoins(List<String> coins) {
		this.coins = coins;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public List<String> getHandSizes() {
		return handSizes;
	}
	public void setHandSizes(List<String> handSizes) {
		this.handSizes = handSizes;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public List<String> getRevealedCards() {
		return revealedCards;
	}
	public void setRevealedCards(List<String> revealedCards) {
		this.revealedCards = revealedCards;
	}
	public String getDeckSize() {
		return deckSize;
	}
	public void setDeckSize(String deckSize) {
		this.deckSize = deckSize;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRoundCount() {
		return roundCount;
	}
	public void setRoundCount(String roundCount) {
		this.roundCount = roundCount;
	}
	public String getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(String curPlayer) {
		this.curPlayer = curPlayer;
	}
	public String getCurRole() {
		return curRole;
	}
	public void setCurRole(String curRole) {
		this.curRole = curRole;
	}
	public List<String> getRoleNums() {
		return roleNums;
	}
	public void setRoleNums(List<String> roleNums) {
		this.roleNums = roleNums;
	}
	public List<String> getRoleOwners() {
		return roleOwners;
	}
	public void setRoleOwners(List<String> roleOwners) {
		this.roleOwners = roleOwners;
	}
	public List<String> getRoleImgs() {
		return roleImgs;
	}
	public void setRoleImgs(List<String> roleImgs) {
		this.roleImgs = roleImgs;
	}
	public String getCrown() {
		return crown;
	}
	public void setCrown(String crown) {
		this.crown = crown;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public String getIsLord() {
		return isLord;
	}
	public void setIsLord(String isLord) {
		this.isLord = isLord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastRound() {
		return lastRound;
	}
	public void setLastRound(String lastRound) {
		this.lastRound = lastRound;
	}
	public List<String> getRoleRevealed() {
		return roleRevealed;
	}
	public void setRoleRevealed(List<String> roleRevealed) {
		this.roleRevealed = roleRevealed;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public List<String> getSkillButtons() {
		return skillButtons;
	}
	public void setSkillButtons(List<String> skillButtons) {
		this.skillButtons = skillButtons;
	}
	public String getAskId() {
		return askId;
	}
	public void setAskId(String askId) {
		this.askId = askId;
	}
	public String getAskType() {
		return askType;
	}
	public void setAskType(String askType) {
		this.askType = askType;
	}
	public String getAskBuiltIndex() {
		return askBuiltIndex;
	}
	public void setAskBuiltIndex(String askBuiltIndex) {
		this.askBuiltIndex = askBuiltIndex;
	}
	public List<String> getAskLs() {
		return askLs;
	}
	public void setAskLs(List<String> askLs) {
		this.askLs = askLs;
	}
	public List<String> getCanUseRoleSkill() {
		return canUseRoleSkill;
	}
	public void setCanUseRoleSkill(List<String> canUseRoleSkill) {
		this.canUseRoleSkill = canUseRoleSkill;
	}
	public List<String> getCanUseCardSkill() {
		return canUseCardSkill;
	}
	public void setCanUseCardSkill(List<String> canUseCardSkill) {
		this.canUseCardSkill = canUseCardSkill;
	}
	public String getKilledRole() {
		return killedRole;
	}
	public void setKilledRole(String killedRole) {
		this.killedRole = killedRole;
	}
	public String getStealedRole() {
		return stealedRole;
	}
	public void setStealedRole(String stealedRole) {
		this.stealedRole = stealedRole;
	}
	public List<List<String>> getAskBuiltInfo() {
		return askBuiltInfo;
	}
	public void setAskBuiltInfo(List<List<String>> askBuiltInfo) {
		this.askBuiltInfo = askBuiltInfo;
	}
	public String getAskMsg() {
		return askMsg;
	}
	public void setAskMsg(String askMsg) {
		this.askMsg = askMsg;
	}
	public List<String> getScores() {
		return scores;
	}
	public void setScores(List<String> scores) {
		this.scores = scores;
	}
	public List<String> getNetScores() {
		return netScores;
	}
	public void setNetScores(List<String> netScores) {
		this.netScores = netScores;
	}
	public List<String> getExtraScores() {
		return extraScores;
	}
	public void setExtraScores(List<String> extraScores) {
		this.extraScores = extraScores;
	}
	public String getYourRole() {
		return yourRole;
	}
	public void setYourRole(String yourRole) {
		this.yourRole = yourRole;
	}
	public String getChooseOrDiscard() {
		return chooseOrDiscard;
	}
	public void setChooseOrDiscard(String chooseOrDiscard) {
		this.chooseOrDiscard = chooseOrDiscard;
	}
	public String getFinishCount() {
		return finishCount;
	}
	public void setFinishCount(String finishCount) {
		this.finishCount = finishCount;
	}
	public String getRegicide() {
		return regicide;
	}
	public void setRegicide(String regicide) {
		this.regicide = regicide;
	}
	public List<String> getTempRevealedTop() {
		return tempRevealedTop;
	}
	public void setTempRevealedTop(List<String> tempRevealedTop) {
		this.tempRevealedTop = tempRevealedTop;
	}
	public List<List<String>> getSpecialHands() {
		return specialHands;
	}
	public void setSpecialHands(List<List<String>> specialHands) {
		this.specialHands = specialHands;
	}
}
