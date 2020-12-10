package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<Player> players;
	List<Role> rolesThisGame;
	List<List<Role>> centerRoles;
	
	int status;
	int round;
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
	
	
}
