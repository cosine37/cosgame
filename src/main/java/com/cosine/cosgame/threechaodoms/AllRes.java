package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.base.*;

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
		int i;
		for (i=0;i<100;i++) {
			Card c = new LiuBei();
			base.add(c);
		}
	}
}
