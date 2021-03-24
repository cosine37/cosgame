package com.cosine.cosgame.zodiac;

public class Zodiac {
	int num;
	String name;
	boolean isReal;
	boolean reveal;
	boolean keep;
	boolean stolen;
	int votes;
	
	public Zodiac() {
		votes = 0;
		isReal = true;
		reveal = false;
		keep = false;
		stolen = false;
	}
	
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isReal() {
		return isReal;
	}
	public void setReal(boolean isReal) {
		this.isReal = isReal;
	}
	public boolean isReveal() {
		return reveal;
	}
	public void setReveal(boolean reveal) {
		this.reveal = reveal;
	}
	public boolean isKeep() {
		return keep;
	}
	public void setKeep(boolean keep) {
		this.keep = keep;
	}
	public boolean isStolen() {
		return stolen;
	}
	public void setStolen(boolean stolen) {
		this.stolen = stolen;
	}
}
