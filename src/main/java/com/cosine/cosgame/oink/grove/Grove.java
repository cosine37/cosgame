package com.cosine.cosgame.oink.grove;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;

public class Grove {
	int curPlayer;
	int phase;
	int lastAccused;
	int predefinedRoles;
	List<GrovePlayer> players;
	List<Role> suspects;
	List<Role> victims;
	
	Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("curPlayer",curPlayer);
		doc.append("phase",phase);
		doc.append("lastAccused",lastAccused);
		doc.append("predefinedRoles",predefinedRoles);
		List<Document> playersDocList = new ArrayList<>();
		for (i=0;i<players.size();i++){
			playersDocList.add(players.get(i).toDocument());
		}
		List<Document> suspectsDocList = new ArrayList<>();
		for (i=0;i<suspects.size();i++){
			suspectsDocList.add(suspects.get(i).toDocument());
		}
		List<Document> victimsDocList = new ArrayList<>();
		for (i=0;i<victims.size();i++){
			victimsDocList.add(victims.get(i).toDocument());
		}
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		curPlayer = doc.getInteger("curPlayer",0);
		phase = doc.getInteger("phase",0);
		lastAccused = doc.getInteger("lastAccused",0);
		predefinedRoles = doc.getInteger("predefinedRoles",0);
		List<Document> playersDocList = (List<Document>)doc.get("players");
		players = new ArrayList<>();
		for (i=0;i<playersDocList.size();i++){
			GrovePlayer e = new GrovePlayer();
			e.setFromDoc(playersDocList.get(i));
			players.add(e);
		}
		List<Document> suspectsDocList = (List<Document>)doc.get("suspects");
		suspects = new ArrayList<>();
		for (i=0;i<suspectsDocList.size();i++){
			Role e = new Role();
			e.setFromDoc(suspectsDocList.get(i));
			suspects.add(e);
		}
		List<Document> victimsDocList = (List<Document>)doc.get("victims");
		victims = new ArrayList<>();
		for (i=0;i<victimsDocList.size();i++){
			Role e = new Role();
			e.setFromDoc(victimsDocList.get(i));
			victims.add(e);
		}
	}
	
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
		GrovePlayer p = getPlayerByName(name);
		if (p != null) {
			p.setViewed(viewed);
		}
	}
	
	
	public void playerAccuse(String name, int index) {
		GrovePlayer p = getPlayerByName(name);
		if (p != null && index != lastAccused && index>=0 && index<=Consts.NUMSUSPECTS) {
			suspects.get(index).addPredicted(p.getIndex());
			p.setAccused(index);
			lastAccused = index;
		}
	}
	
	public GrovePlayer getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	public int getStatus() {
		return board.getStatus();
	}
	public void setStatus(int status) {
		board.setStatus(status);
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
	public List<GrovePlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<GrovePlayer> players) {
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
