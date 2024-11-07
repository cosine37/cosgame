package com.cosine.cosgame.oink.grove;

import java.util.List;

public class Grove {
	int curPlayer;
	int phase;
	int lastAccused;
	int predefinedRoles;
	List<Player> players;
	List<Role> suspects;
	List<Role> victims;
	
	public Grove() {
		
	}
	
	
	public int murdererId() {
		int i;
		boolean reverse = false;
		for (i=0;i<suspects.size();i++) {
			if (suspects.get(i).getNum() == Consts.REVERSENUM) {
				reverse = true;
			}
		}
		
		int ans = suspects.get(0).getNum();
		for (i=1;i<suspects.size();i++) {
			if (reverse) {
				if (suspects.get(i).getNum()<ans) {
					ans = suspects.get(i).getNum();
				}
			} else {
				if (suspects.get(i).getNum()>ans) {
					ans = suspects.get(i).getNum();
				}
			}
		}
		return ans;
	}
	
	public void distributeLiar() {
		int ans = murdererId();
		for (int i=0;i<suspects.size();i++) {
			if (suspects.get(i).getNum() != ans && suspects.get(i).lastPredicted() != -1) {
				players.get(suspects.get(i).lastPredicted()).addLiar(suspects.get(i).getPredicted().size());
			}
		}
	}
	
	public void playerView(String name, List<Integer> viewed) {
		Player p = getPlayerByName(name);
		if (p != null) {
			p.setViewed(viewed);
		}
	}
	
	
	public void playerAccuse(String name, int index) {
		Player p = getPlayerByName(name);
		if (p != null && index != lastAccused && index>=0 && index<=Consts.NUMSUSPECTS) {
			suspects.get(index).addPredicted(p.getIndex());
			p.setAccused(index);
			lastAccused = index;
		}
	}
	
	public Player getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getLastAccused() {
		return lastAccused;
	}
	public void setLastAccused(int lastAccused) {
		this.lastAccused = lastAccused;
	}
	public int getPredefinedRoles() {
		return predefinedRoles;
	}
	public void setPredefinedRoles(int predefinedRoles) {
		this.predefinedRoles = predefinedRoles;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Role> getSuspects() {
		return suspects;
	}
	public void setSuspects(List<Role> suspects) {
		this.suspects = suspects;
	}
	public List<Role> getVictims() {
		return victims;
	}
	public void setVictims(List<Role> victims) {
		this.victims = victims;
	}
	
}
