package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

public class AllRes {
	List<Card> base;
	
	public AllRes() {
		genBase();
	}
	
	public List<Card> getDeck(){
		return base;
	}
	
	public void genBase() {
		base = new ArrayList<>();
	}
}
