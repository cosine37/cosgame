package com.cosine.cosgame.propnightold;

import java.util.ArrayList;
import java.util.List;

public class Place {
	int id;
	List<Integer> presentedHuman;
	boolean hasGhost;
	
	public void cleanGhostToken() {
		hasGhost = false;
	}
	
	public void cleanHuman() {
		presentedHuman = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getPresentedHuman() {
		return presentedHuman;
	}
	public void setPresentedHuman(List<Integer> presentedHuman) {
		this.presentedHuman = presentedHuman;
	}
	public boolean isHasGhost() {
		return hasGhost;
	}
	public void setHasGhost(boolean hasGhost) {
		this.hasGhost = hasGhost;
	}
	
	
}
