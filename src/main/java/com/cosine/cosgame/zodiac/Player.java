package com.cosine.cosgame.zodiac;

import java.util.List;

import org.bson.Document;

public class Player {
	String name;
	String displayName;
	Role role;
	Board board;
	int index;
	int votes;
	int disabledTurn;
	int option;
	List<Integer> voteHistory;
	List<Integer> checkHistory;
	List<Integer> sideHistory;
	List<Integer> zodiacTargets;
	List<Integer> playerTargets;
	boolean disabled;
	
	public Player() {
		votes = 0;
		disabledTurn = -1;
	}
	
	public void initialize() {
		votes = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public List<Integer> getVoteHistory() {
		return voteHistory;
	}
	public void setVoteHistory(List<Integer> voteHistory) {
		this.voteHistory = voteHistory;
	}
	public List<Integer> getCheckHistory() {
		return checkHistory;
	}
	public void setCheckHistory(List<Integer> checkHistory) {
		this.checkHistory = checkHistory;
	}
	public int getDisabledTurn() {
		return disabledTurn;
	}
	public void setDisabledTurn(int disabledTurn) {
		this.disabledTurn = disabledTurn;
	}
	public List<Integer> getZodiacTargets() {
		return zodiacTargets;
	}
	public void setZodiacTargets(List<Integer> zodiacTargets) {
		this.zodiacTargets = zodiacTargets;
	}
	public List<Integer> getPlayerTargets() {
		return playerTargets;
	}
	public void setPlayerTargets(List<Integer> playerTargets) {
		this.playerTargets = playerTargets;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Integer> getSideHistory() {
		return sideHistory;
	}
	public void setSideHistory(List<Integer> sideHistory) {
		this.sideHistory = sideHistory;
	}
	public int getOption() {
		return option;
	}
	public void setOption(int option) {
		this.option = option;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("displayName", displayName);
		doc.append("role", role.getNum());
		doc.append("votes", votes);
		doc.append("disabledTurn", disabledTurn);
		doc.append("option", option);
		doc.append("voteHistory", voteHistory);
		doc.append("checkHistory", checkHistory);
		doc.append("sideHistory", sideHistory);
		doc.append("zodiacTargets", zodiacTargets);
		doc.append("playerTargets", playerTargets);
		doc.append("disabled", disabled);
		return doc;
	}
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		displayName = doc.getString("displayName");
		role = RoleFactory.createRole(doc.getInteger("role", -1));
		role.setPlayer(this);
		role.setBoard(board);
		votes = doc.getInteger("votes", 0);
		disabledTurn = doc.getInteger("disabledTurn", -1);
		option = doc.getInteger("option", Consts.NOOPTION);
		voteHistory = (List<Integer>) doc.get("voteHistory");
		checkHistory = (List<Integer>) doc.get("checkHistory");
		sideHistory = (List<Integer>) doc.get("sideHistory");
		zodiacTargets = (List<Integer>) doc.get("zodiacTargets");
		playerTargets = (List<Integer>) doc.get("playerTargets");
		disabled = doc.getBoolean("disabled", false);
	}
}
