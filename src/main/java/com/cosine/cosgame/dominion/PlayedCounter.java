package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

public class PlayedCounter {
	List<String> played;
	
	public PlayedCounter() {
		played = new ArrayList<String>();
	}
	
	public void add(String s) {
		played.add(s);
	}
	
	public int numPlayed(String s) {
		int i;
		int ans = 0;
		for (i=0;i<played.size();i++) {
			if (played.get(i).equals(s)) {
				ans = ans+1;
			}
		}
		return ans;
	}
	
	public List<String> getPlayedList(){
		return played;
	}
}
