package com.cosine.cosgame.rich.eco;

import java.util.ArrayList;
import java.util.List;

public class NewsBuff {
	final int BUFF_NOSTATIONMOVE = 0;
	final int BUFF_DOMINANTFATE = 1;
	
	
	List<Integer> buffs;
	public NewsBuff() {
		clearBuffs();
	}
	
	public NewsBuff(List<Integer> buffs) {
		this.buffs = buffs;
	}
	
	public void clearBuffs() {
		buffs = new ArrayList<>();
		for (int i=0;i<20;i++) {
			buffs.add(-1);
		}
	}
	
	public void setNoStationMove(int x) {
		buffs.set(BUFF_NOSTATIONMOVE, x);
	}
	public int getNoStationMove() {
		return buffs.get(BUFF_NOSTATIONMOVE);
	}
	public void setDominantFate(int x) {
		buffs.set(BUFF_DOMINANTFATE, x);
	}
	public int getDominantFate() {
		return buffs.get(BUFF_DOMINANTFATE);
	}

	public List<Integer> getBuffs() {
		return buffs;
	}
	public void setBuffs(List<Integer> buffs) {
		this.buffs = buffs;
	}
}
