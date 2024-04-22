package com.cosine.cosgame.pokerworld.camelup;

import java.util.ArrayList;
import java.util.List;

public class Spectator extends Grid {
	int dir;
	Gambler gambler;
	
	public Spectator(int dir) {
		super();
		this.dir = dir;
	}
	
	public Spectator(Gambler gambler, int dir) {
		super();
		this.dir = dir;
		this.gambler = gambler;
	}

	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public Gambler getGambler() {
		return gambler;
	}
	public void setGambler(Gambler gambler) {
		this.gambler = gambler;
	}

	public List<Integer> toList(){
		List<Integer> ans = new ArrayList<>();
		ans.add(dir);
		return ans;
	}
}
