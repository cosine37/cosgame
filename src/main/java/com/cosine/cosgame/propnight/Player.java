package com.cosine.cosgame.propnight;

public class Player extends com.cosine.boardgame.Player{
	static final String HP = "hp";
	
	public Player() {
		
	}
	
	public void setHP(int x) {
		put(HP, x);
	}
	public int getHP() {
		return getInt(HP);
	}
	public void resetHP() {
		setHP(Consts.MAXHP);
	}
	public void addHP(int x) {
		int hp = getInt(HP);
		put (HP,hp+x);
	}
}
