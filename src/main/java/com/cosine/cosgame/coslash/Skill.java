package com.cosine.cosgame.coslash;

public class Skill extends Card {
	
	boolean oncePerTurn; // 单发技
	boolean locked; // 锁定技
	boolean once; // 限定技
	boolean awaken; // 觉醒技
	
	public boolean isOncePerTurn() {
		return oncePerTurn;
	}
	public void setOncePerTurn(boolean oncePerTurn) {
		this.oncePerTurn = oncePerTurn;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public boolean isOnce() {
		return once;
	}
	public void setOnce(boolean once) {
		this.once = once;
	}
	public boolean isAwaken() {
		return awaken;
	}
	public void setAwaken(boolean awaken) {
		this.awaken = awaken;
	}
	
	
}
