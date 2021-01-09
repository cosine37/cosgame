package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Player {
	String name;
	String displayName;
	List<Role> roles; // the change of roles over time
	int numVotes; // number of votes this player receives
	int voteIndex; // who this player votes	
	int score;
	int index;
	int beggarIndex;
	boolean voted;
	boolean votedOut;
	boolean bot;
	Role updatedRole; // the updated role the user see
	boolean showUpdatedRole;
	boolean confirmed;
	
	List<Integer> playerMarks;
	List<Integer> centerMarks;
	
	Board board;
	
	public Player() {
		roles = new ArrayList<>();
		playerMarks = new ArrayList<>();
		centerMarks = new ArrayList<>();
		voted = false;
		votedOut = false;
		bot = false;
		updatedRole = new Role();
		showUpdatedRole = false;
		confirmed = false;
		beggarIndex = -1;
	}
	
	public Player prevPlayer() {
		int x = index-1;
		if (x<0) {
			x = x+board.getPlayers().size();
		}
		Player p = board.getPlayers().get(x);
		return p;
	}
	
	public Player nextPlayer() {
		int x = index+1;
		if (x == board.getPlayers().size()) {
			x = 0;
		}
		Player p = board.getPlayers().get(x);
		return p;
	}
	
	public int getCurrentRoleIndex() {
		int x = roles.size() - 1;
		return x;
	}
	
	public Role getCurrentRole() {
		if (roles.size() > 0) {
			int x = roles.size() - 1;
			return roles.get(x);
		} else {
			return null;
		}
	}
	
	public Role getInitialRole() {
		if (roles.size() > 0) {
			return roles.get(0);
		} else {
			return null;
		}
	}
	
	public void initializeMarks(int n) {
		playerMarks = new ArrayList<>();
		centerMarks = new ArrayList<>();
		int i;
		for (i=0;i<n;i++) {
			playerMarks.add(-1);
		}
		for (i=0;i<3;i++) {
			centerMarks.add(-1);
		}
		beggarIndex = -1;
	}
	
	public void showAllRoles() {
		int i;
		for (i=0;i<playerMarks.size();i++) {
			int x = board.getPlayers().get(i).getCurrentRole().getRoleNum();
			playerMarks.set(i, x);
		}
		for (i=0;i<centerMarks.size();i++) {
			int x = board.getCurCenterRole(i).getRoleNum();
			centerMarks.set(i, x);
		}
		updatedRole = getCurrentRole();
		showUpdatedRole = true;
	}
	
	public void addRole(Role role) {
		roles.add(role);
	}
	
	public void clearRole() {
		roles = new ArrayList<>();
	}
	
	public void receiveVote() {
		numVotes++;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public int getNumVotes() {
		return numVotes;
	}
	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}
	public int getVoteIndex() {
		return voteIndex;
	}
	public void setVoteIndex(int voteIndex) {
		this.voteIndex = voteIndex;
	}
	public List<Integer> getPlayerMarks() {
		return playerMarks;
	}
	public void setPlayerMarks(List<Integer> playerMarks) {
		this.playerMarks = playerMarks;
	}
	public List<Integer> getCenterMarks() {
		return centerMarks;
	}
	public void setCenterMarks(List<Integer> centerMarks) {
		this.centerMarks = centerMarks;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public boolean isVoted() {
		return voted;
	}
	public void setVoted(boolean voted) {
		this.voted = voted;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	public Role getUpdatedRole() {
		return updatedRole;
	}
	public void setUpdatedRole(Role updatedRole) {
		this.updatedRole = updatedRole;
	}
	public boolean isShowUpdatedRole() {
		return showUpdatedRole;
	}
	public void setShowUpdatedRole(boolean showUpdatedRole) {
		this.showUpdatedRole = showUpdatedRole;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public boolean isVotedOut() {
		return votedOut;
	}
	public void setVotedOut(boolean votedOut) {
		this.votedOut = votedOut;
	}
	public int getBeggarIndex() {
		return beggarIndex;
	}
	public void setBeggarIndex(int beggarIndex) {
		this.beggarIndex = beggarIndex;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("displayName", displayName);
		doc.append("numVotes", numVotes);
		doc.append("voteIndex", voteIndex);
		doc.append("score", score);
		doc.append("playerMarks", playerMarks);
		doc.append("centerMarks", centerMarks);
		doc.append("bot", bot);
		doc.append("showUpdatedRole", showUpdatedRole);
		doc.append("updatedRole", updatedRole.getDBStorageImg());
		doc.append("confirmed", confirmed);
		doc.append("voted", voted);
		doc.append("votedOut", votedOut);
		doc.append("beggarIndex", beggarIndex);
		int i;
		List<String> lor = new ArrayList<>();
		for (i=0;i<roles.size();i++) {
			lor.add(roles.get(i).getDBStorageImg());
		}
		doc.append("roles", lor);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		displayName = doc.getString("displayName");
		numVotes = doc.getInteger("numVotes", 0);
		voteIndex = doc.getInteger("voteIndex", 0);
		score = doc.getInteger("score", 0);
		playerMarks = (List<Integer>) doc.get("playerMarks");
		centerMarks = (List<Integer>) doc.get("centerMarks");
		bot = doc.getBoolean("bot", false);
		showUpdatedRole = doc.getBoolean("showUpdatedRole", false);
		updatedRole = RoleFactory.createRole(doc.getString("updatedRole"));
		updatedRole.setBoard(board);
		updatedRole.setPlayer(this);
		confirmed = doc.getBoolean("confirmed", false);
		voted = doc.getBoolean("voted", false);
		votedOut = doc.getBoolean("votedOut", false);
		beggarIndex = doc.getInteger("beggarIndex", -1);
		List<String> lor = (List<String>) doc.get("roles");
		int i;
		roles = new ArrayList<>();
		for (i=0;i<lor.size();i++) {
			Role r = RoleFactory.createRole(lor.get(i));
			r.setPlayer(this);
			r.setBoard(board);
			roles.add(r);
		}
	}

}
