package com.cosine.cosgame.minigame.xutangbo;

public class Player {
	public static final int INACTIVE = 0;
	public static final int ALIVE = 1;
	public static final int DEAD = 2;
	
	int status;
	int energy;
	int bi;
	
	String name;
	
	Move curMove;
	
	public Player() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getBi() {
		return bi;
	}

	public void setBi(int bi) {
		this.bi = bi;
	}

	public Move getCurMove() {
		return curMove;
	}

	public void setCurMove(Move curMove) {
		this.curMove = curMove;
	}
	
	public void die() {
		status = DEAD;
	}
	
}
