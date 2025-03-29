package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

public class Buff {
	final int NEXTROLL = 0;
	
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
	
	public List<Integer> getBuffs() {
		return buffs;
	}
	public void setBuffs(List<Integer> buffs) {
		this.buffs = buffs;
	}
}
