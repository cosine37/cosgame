package com.cosine.cosgame.pokerworld.camelup;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Grid{
	public Empty() {
		super();
	}
	
	public int getType() {
		return Consts.EMPTY;
	}
	
	public List<Integer> toList(){
		List<Integer> ans = new ArrayList<>();
		ans.add(Consts.EMPTY);
		return ans;
	}
}
