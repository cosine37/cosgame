package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

public class PlaceBuff {
	final int MAXBUFFS = 20;
	final int DISABLE = 0;
	
	
	public List<Integer> buffs;
	
	public PlaceBuff() {
		buffs = new ArrayList<>();
		int i;
		for (i=0;i<MAXBUFFS;i++) {
			buffs.add(-1);
		}
	}
	
	public boolean noBuff() {
		for (int i=0;i<MAXBUFFS;i++) {
			if (buffs.get(i) > 0) return false;
		}
		return true;
	}
	
	
	public void minusBuff() {
		for (int i=0;i<MAXBUFFS;i++) {
			int x = buffs.get(i);
			if (x > 0) x--;
			if (x == 0) x = -1;
			buffs.set(i, x);
		}
	}
	public int getDisable() {
		return buffs.get(DISABLE);
	}
	
	public void setDisable(int x) {
		buffs.set(DISABLE, x);
	}
	
	public PlaceBuff(List<Integer> buffs) {
		this.buffs = buffs;
	}
	public List<Integer> getBuffs() {
		return buffs;
	}
	public void setBuffs(List<Integer> buffs) {
		this.buffs = buffs;
	}
	
}
