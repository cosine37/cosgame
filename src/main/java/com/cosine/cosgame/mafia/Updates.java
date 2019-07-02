package com.cosine.cosgame.mafia;

import java.util.*;

public class Updates {
	String roomId;
	String phase;
	List<Action> actions;
	
	public Updates(String roomId) {
		actions = new ArrayList<Action>();
	}
	
	public void addAction(Action a) {
		actions.add(a);
	}
	
	public void sortAction() {
		
	}
	
}
