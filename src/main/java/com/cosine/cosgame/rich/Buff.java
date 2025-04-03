package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

public class Buff {
	final int NEXTROLL = 0;
	final int ROLLADD = 1;
	final int ATTACKBOOST = 2;
	final int AIMBOOST = 3;
	
	List<Integer> buffs;
	
	public Buff() {
		buffs = new ArrayList<>();
		for (int i=0;i<20;i++) {
			buffs.add(-1);
		}
	}
	public Buff(List<Integer> buffs) {
		this.buffs = buffs;
	}
	
	public int getNextRoll() {
		return buffs.get(NEXTROLL);
	}
	public void setNextRoll(int x) {
		buffs.set(NEXTROLL,x);
	}
	public void clearNextRoll() {
		buffs.set(NEXTROLL,-1);
	}
	public int getRollAdd() {
		return buffs.get(ROLLADD);
	}
	public void setRoleAdd(int x) {
		buffs.set(ROLLADD,x);
	}
	public void clearRollAdd() {
		buffs.set(ROLLADD,-1);
	}
	public int getAttackBoost() {
		return buffs.get(ATTACKBOOST);
	}
	public void setAttackBoost(int x) {
		buffs.set(ATTACKBOOST,x);
	}
	public void clearAttackBoost() {
		buffs.set(ATTACKBOOST,-1);
	}
	public int getAimBoost() {
		return buffs.get(AIMBOOST);
	}
	public void setAimBoost(int x) {
		buffs.set(AIMBOOST,x);
	}
	public void cleargetAimBoostBoost() {
		buffs.set(AIMBOOST,-1);
	}
	public void minusBoost(int c) {
		int x = buffs.get(c);
		x--;
		if (x < 1) {
			buffs.set(c, -1);
		} else {
			buffs.set(c, x);
		}
	}
	public void minusAttackBoost() {
		minusBoost(ATTACKBOOST);
	}
	public void minusAimBoost() {
		minusBoost(AIMBOOST);
	}
	
	public void turnEndMinus() {
		minusAttackBoost();
		minusAimBoost();
	}
	
	public List<Integer> getBuffs() {
		return buffs;
	}
	public void setBuffs(List<Integer> buffs) {
		this.buffs = buffs;
	}
}
