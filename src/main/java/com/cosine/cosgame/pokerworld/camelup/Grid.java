package com.cosine.cosgame.pokerworld.camelup;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	int pos;
	public Grid() {
		
	}
	
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getType() {
		return Consts.GRID;
	}
	
	public List<Integer> toList(){
		List<Integer> ans = new ArrayList<>();
		ans.add(Consts.GRID);
		return ans;
	}
}
