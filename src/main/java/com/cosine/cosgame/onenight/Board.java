package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Board {
	List<Player> players;
	List<Role> rolesThisGame;
	List<List<Role>> centerRoles;
	
	String id;
	int status;
	int round;
	int totalRounds;
	int winSide;
	
	List<String> confirmed;
	
	
	public Board() {
		players = new ArrayList<>();
		rolesThisGame = new ArrayList<>();
	}
	
	public void startGame() {
		int n = players.size();
		int i;
		for (i=0;i<n;i++) {
			players.get(i).initializeMarks(n);
		}
		status = Consts.PREGAME;
		winSide = -1;
		
	}
	
	public Role getCurCenterRole(int x) {
		if (x>2 || x<0) {
			return null;
		} else {
			int n = centerRoles.get(x).size()-1;
			Role role = centerRoles.get(x).get(n);
			return role;
		}
	}
	
	public void executeAllSkills() {
		List<Player> tps = new ArrayList<>();
		int i,j;
		for (i=0;i<players.size();i++) {
			tps.add(players.get(i));
		}
		for (i=0;i<tps.size();i++) {
			for (j=i+1;j<tps.size();j++) {
				int xi = tps.get(i).getInitialRole().getSequence();
				int xj = tps.get(j).getInitialRole().getSequence();
				if (xi > xj) {
					Player tp = tps.get(i);
					tps.set(i, tps.get(j));
					tps.set(j, tp);
				}
			}
		}
		for (i=0;i<tps.size();i++) {
			int x = tps.get(i).getInitialRole().getSequence();
			if (x > 0) {
				tps.get(i).getInitialRole().executeSkill();
			}
		}
	}
	
	public boolean allConfirmed() {
		int i;
		for (i=0;i<confirmed.size();i++) {
			if (confirmed.get(i).contentEquals("n")) {
				return false;
			}
		}
		return true;
	}
	
	public void nextStatus() {
		
	}
	
	public void confirm(int x) {
		confirmed.set(x, "y");
		if (allConfirmed()) {
			nextStatus();
		}
	}
	
	public void vote(int x, int y) {
		players.get(x).setVoteIndex(y);
		players.get(y).receiveVote();
	}
	
	public void decideWinSide() {
		int mostVote = 2;
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getNumVotes() > mostVote) {
				mostVote = players.get(i).getNumVotes();
			}
		}
		boolean votedWerewolf = false;
		boolean votedMinion = false;
		boolean missedWerewolf = false;
		for (i=0;i<players.size();i++) {
			Role r = players.get(i).getCurrentRole();
			if (players.get(i).getNumVotes() == mostVote) {
				players.get(i).setVoted(true);
				if (r.getSide() == Consts.WOLF) {
					if (r.getRoleNum() == Consts.MINION) {
						votedMinion = true;
					} else {
						votedWerewolf = true;
					}
				}
			} else {
				if (r.getSide() == Consts.WOLF) {
					missedWerewolf = true;
				}
			}
		}
		if (votedWerewolf) {
			winSide = Consts.HUMAN;
		} else if (votedMinion){
			if (missedWerewolf) {
				winSide = Consts.WOLF;
			} else {
				winSide = Consts.HUMAN;
			}
		} else if (missedWerewolf == false) {
			winSide = Consts.HUMAN;
		} else {
			winSide = Consts.WOLF;
		}
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Role> getRolesThisGame() {
		return rolesThisGame;
	}
	public void setRolesThisGame(List<Role> rolesThisGame) {
		this.rolesThisGame = rolesThisGame;
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
	public List<List<Role>> getCenterRoles() {
		return centerRoles;
	}
	public void setCenterRoles(List<List<Role>> centerRoles) {
		this.centerRoles = centerRoles;
	}
	public List<String> getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(List<String> confirmed) {
		this.confirmed = confirmed;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("status", status);
		doc.append("round", round);
		doc.append("totalRounds", totalRounds);
		doc.append("confirmed", confirmed);
		int i,j;
		List<String> lor = new ArrayList<>();
		for (i=0;i<rolesThisGame.size();i++) {
			lor.add(rolesThisGame.get(i).getImg());
		}
		doc.append("rolesThisGame", lor);
		List<List<String>> loc = new ArrayList<>();
		for (i=0;i<centerRoles.size();i++) {
			List<String> sloc = new ArrayList<>();
			for (j=0;j<centerRoles.get(i).size();j++) {
				sloc.add(centerRoles.get(i).get(j).getImg());
			}
			loc.add(sloc);
		}
		doc.append("centerRoles", centerRoles);
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String playerName = players.get(i).getName();
			playerNames.add(playerName);
			String s = "player-" + playerName;
			doc.append(s, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		status = doc.getInteger("status", 0);
		round = doc.getInteger("round", 0);
		totalRounds = doc.getInteger("totalRounds", totalRounds);
		confirmed = (List<String>) doc.get("confirmed");
		int i,j;
		List<String> lor = (List<String>) doc.get("rolesThisGame");
		rolesThisGame = new ArrayList<>();
		for (i=0;i<lor.size();i++) {
			Role r = RoleFactory.createRole(lor.get(i));
			r.setBoard(this);
			rolesThisGame.add(r);
		}
		List<List<String>> loc = (List<List<String>>) doc.get("centerRoles");
		centerRoles = new ArrayList<>();
		for (i=0;i<loc.size();i++) {
			List<Role> singleCenterRoles = new ArrayList<>();
			for (j=0;j<loc.get(i).size();j++) {
				Role r = RoleFactory.createRole(loc.get(i).get(j));
				singleCenterRoles.add(r);
			}
			centerRoles.add(singleCenterRoles);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String playerName = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(playerName);
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
	}
	
}
