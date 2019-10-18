package com.cosine.cosgame.minigame.xutangbo;

import org.bson.Document;

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
		curMove = new Move();
	}
	
	public Player(String name) {
		this();
		this.name = name;
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
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("status", status);
		doc.append("energy", energy);
		doc.append("bi", bi);
		doc.append("curMove", curMove.getMoveid());
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		status = doc.getInteger("status");
		energy = doc.getInteger("energy");
		bi = doc.getInteger("bi");
		curMove = new Move(doc.getInteger("curMove",-1));
	}
}
