package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.onenight.statuses.NoStatus;

public class Player {
	String name;
	String displayName;
	List<Role> roles; // the change of roles over time
	List<Status> statuses;  // the change of statuses over time
	int numVotes; // number of votes this player receives
	int voteIndex; // who this player votes	
	int score;
	int index;
	int beggarIndex;
	int questionChoosed;
	boolean voted;
	boolean votedOut;
	boolean bot;
	Role updatedRole; // the updated role the user see
	Status updatedStatus;
	Status finalStatus;
	boolean showFinalStatus;
	boolean showUpdatedRole;
	boolean confirmed;
	boolean stoned;
	boolean poisoned;
	String msg; // the message used for certain roles
	
	List<Integer> questionChoices;
	List<Integer> playerMarks;
	List<Integer> centerMarks;
	List<Integer> statusMarks;
	
	Board board;
	
	public Player() {
		roles = new ArrayList<>();
		statuses = new ArrayList<>();
		questionChoices = new ArrayList<>();
		playerMarks = new ArrayList<>();
		centerMarks = new ArrayList<>();
		statusMarks = new ArrayList<>();
		voted = false;
		votedOut = false;
		bot = false;
		updatedRole = new Role();
		updatedStatus = new Status();
		finalStatus = new Status();
		showUpdatedRole = false;
		showFinalStatus = false;
		confirmed = false;
		beggarIndex = -1;
		questionChoosed = -1;
		stoned = false;
		poisoned = false;
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
	
	public Status getCurrentStatus() {
		if (statuses.size() > 0) {
			int x = statuses.size() - 1;
			return statuses.get(x);
		} else {
			return null;
		}
	}
	
	public int getSide() {
		int ans = getCurrentRole().getSide();
		ans = getCurrentStatus().getSide(ans);
		return ans;
	}
	
	public void initializeMarks(int n) {
		playerMarks = new ArrayList<>();
		centerMarks = new ArrayList<>();
		statusMarks = new ArrayList<>();
		questionChoices = new ArrayList<>();
		int i;
		for (i=0;i<n;i++) {
			playerMarks.add(-1);
			statusMarks.add(-1);
		}
		for (i=0;i<3;i++) {
			centerMarks.add(-1);
		}
		beggarIndex = -1;
		questionChoosed = -1;
		stoned = false;
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
		for (i=0;i<statusMarks.size();i++) {
			int x = board.getPlayers().get(i).getCurrentStatus().getNum();
			statusMarks.set(i, x);
		}
		updatedRole = getCurrentRole();
		finalStatus = getCurrentStatus();
		showUpdatedRole = true;
		showFinalStatus = true;
	}
	
	public boolean win() {
		boolean ans = false;
		if (getSide() == Consts.HUMAN) {
			if (board.getWinSide() == Consts.HUMAN) {
				ans = true;
			}
		} else if (getSide() == Consts.WOLF) {
			if (board.getWinSide() == Consts.WOLF && board.isTannerWin() == false) {
				ans = true;
			}
		}
		ans = getCurrentRole().win(ans);
		ans = getCurrentStatus().win(ans);
		if (voteIndex >= 0 && voteIndex < board.getPlayers().size()) {
			Player p = board.getPlayers().get(voteIndex);
			ans = p.getCurrentRole().votedThis(ans);
			ans = p.getCurrentStatus().votedThis(ans);
		}
		return ans;
	}
	
	public void addRole(Role role) {
		roles.add(role);
	}
	
	public void clearRole() {
		roles = new ArrayList<>();
	}
	
	public void addStatus(Status status) {
		statuses.add(status);
	}
	
	public void clearStatus() {
		statuses = new ArrayList<>();
		Manipulations.convertStatus(this, new NoStatus());
	}
	
	public void receiveVote(int x) {
		numVotes = numVotes+x;
	}
	
	public void receiveVote() {
		numVotes++;
	}
	
	public void poison() {
		if (getCurrentRole().getSide() == Consts.HUMAN){
			if (getCurrentRole().isCanBePoisoned()) {
				poisoned = true;
			}
		}	
	}
	
	public void detoxify() {
		poisoned = false;
	}
	
	public boolean hasSentinel() {
		return (board.getSentinelIndex() == index);
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
		if (updatedRole.getRoleNum() == Consts.WEREMELEON && board.getWeremeleonIndex() != -1) {
			this.updatedRole = board.getWeremeleonRole();
		} else if (updatedRole.getRoleNum() == Consts.WOLFHUNTER && board.getWolfHunterIndex() != -1) {
			this.updatedRole = board.getWolfHunterRole();
		} else {
			this.updatedRole = updatedRole;
		}
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
	public Status getUpdatedStatus() {
		return updatedStatus;
	}
	public void setUpdatedStatus(Status updatedStatus) {
		this.updatedStatus = updatedStatus;
	}
	public List<Integer> getStatusMarks() {
		return statusMarks;
	}
	public void setStatusMarks(List<Integer> statusMarks) {
		this.statusMarks = statusMarks;
	}
	public List<Status> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}
	public Status getFinalStatus() {
		return finalStatus;
	}
	public void setFinalStatus(Status finalStatus) {
		this.finalStatus = finalStatus;
	}
	public boolean isShowFinalStatus() {
		return showFinalStatus;
	}
	public void setShowFinalStatus(boolean showFinalStatus) {
		this.showFinalStatus = showFinalStatus;
	}
	public boolean isStoned() {
		return stoned;
	}
	public void setStoned(boolean stoned) {
		this.stoned = stoned;
	}
	public boolean isPoisoned() {
		return poisoned;
	}
	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getQuestionChoosed() {
		return questionChoosed;
	}
	public void setQuestionChoosed(int questionChoosed) {
		this.questionChoosed = questionChoosed;
	}
	public List<Integer> getQuestionChoices() {
		return questionChoices;
	}
	public void setQuestionChoices(List<Integer> questionChoices) {
		this.questionChoices = questionChoices;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("displayName", displayName);
		doc.append("numVotes", numVotes);
		doc.append("voteIndex", voteIndex);
		doc.append("score", score);
		doc.append("questionChoices", questionChoices);
		doc.append("questionChoosed", questionChoosed);
		doc.append("playerMarks", playerMarks);
		doc.append("centerMarks", centerMarks);
		doc.append("statusMarks", statusMarks);
		doc.append("bot", bot);
		doc.append("showUpdatedRole", showUpdatedRole);
		doc.append("showFinalStatus", showFinalStatus);
		doc.append("updatedRole", updatedRole.getDBStorageImg());
		doc.append("updatedStatus", updatedStatus.getNum());
		doc.append("finalStatus", finalStatus.getNum());
		doc.append("confirmed", confirmed);
		doc.append("voted", voted);
		doc.append("votedOut", votedOut);
		doc.append("beggarIndex", beggarIndex);
		doc.append("stoned", stoned);
		doc.append("poisoned", poisoned);
		doc.append("msg", msg);
		int i;
		List<String> lor = new ArrayList<>();
		for (i=0;i<roles.size();i++) {
			lor.add(roles.get(i).getDBStorageImg());
		}
		doc.append("roles", lor);
		List<Integer> los = new ArrayList<>();
		for (i=0;i<statuses.size();i++) {
			los.add(statuses.get(i).getNum());
		}
		doc.append("statuses", los);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		displayName = doc.getString("displayName");
		numVotes = doc.getInteger("numVotes", 0);
		voteIndex = doc.getInteger("voteIndex", 0);
		score = doc.getInteger("score", 0);
		questionChoices = (List<Integer>) doc.get("questionChoices");
		questionChoosed = doc.getInteger("questionChoosed", -1);
		playerMarks = (List<Integer>) doc.get("playerMarks");
		centerMarks = (List<Integer>) doc.get("centerMarks");
		statusMarks = (List<Integer>) doc.get("statusMarks");
		bot = doc.getBoolean("bot", false);
		showUpdatedRole = doc.getBoolean("showUpdatedRole", false);
		showFinalStatus = doc.getBoolean("showFinalStatus", false);
		updatedRole = RoleFactory.createRole(doc.getString("updatedRole"));
		updatedRole.setBoard(board);
		updatedRole.setPlayer(this);
		updatedStatus = StatusFactory.createStatus(doc.getInteger("updatedStatus", Consts.UNKNOWN));
		updatedStatus.setBoard(board);
		updatedStatus.setPlayer(this);
		finalStatus = StatusFactory.createStatus(doc.getInteger("finalStatus", Consts.UNKNOWN));
		finalStatus.setBoard(board);
		finalStatus.setPlayer(this);
		confirmed = doc.getBoolean("confirmed", false);
		voted = doc.getBoolean("voted", false);
		votedOut = doc.getBoolean("votedOut", false);
		beggarIndex = doc.getInteger("beggarIndex", -1);
		stoned = doc.getBoolean("stoned", false);
		poisoned = doc.getBoolean("poisoned", false);
		msg = doc.getString("msg");
		List<String> lor = (List<String>) doc.get("roles");
		int i;
		roles = new ArrayList<>();
		for (i=0;i<lor.size();i++) {
			Role r = RoleFactory.createRole(lor.get(i));
			r.setPlayer(this);
			r.setBoard(board);
			roles.add(r);
		}
		List<Integer> los = (List<Integer>) doc.get("statuses");
		statuses = new ArrayList<>();
		for (i=0;i<los.size();i++) {
			Status s = StatusFactory.createStatus(los.get(i));
			s.setPlayer(this);
			s.setBoard(board);
			statuses.add(s);
		}
	}

}
