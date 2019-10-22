package com.cosine.cosgame.minigame.xutangbo;

public class PlayerEntity {
	String name;
	int status;
	String statusStr;
	int energy;
	int bi;
	String curMove;
	public PlayerEntity() {
		
	}
	public PlayerEntity(Player p) {
		name = p.getName();
		status = p.getStatus();
		energy = p.getEnergy();
		bi = p.getBi();
		curMove = p.getCurMove().getMoveName();
		statusStr = "";
		if (status == Player.DEAD) {
			statusStr = "(fainted)";
		}
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
	public String getCurMove() {
		return curMove;
	}
	public void setCurMove(String curMove) {
		this.curMove = curMove;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
}
