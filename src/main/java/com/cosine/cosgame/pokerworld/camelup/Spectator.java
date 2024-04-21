package com.cosine.cosgame.pokerworld.camelup;

import java.util.ArrayList;
import java.util.List;

public class Spectator extends Grid {
	int dir;
	
	public Spectator(int dir) {
		super();
		this.dir = dir;
	}

	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public List<Integer> toList(){
		List<Integer> ans = new ArrayList<>();
		ans.add(dir);
		return ans;
	}
}
