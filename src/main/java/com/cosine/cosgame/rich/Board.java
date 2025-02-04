package com.cosine.cosgame.rich;

import java.util.List;

public class Board {
	String id;
	String lotd;
	
	int status;
	int curCardId;
	int curPlayer;
	
	int height;
	int width;
	
	List<Place> places;
	List<Player> players;
	
	List<List<Event>> eventsList;

	public int getSalary() {
		return 0;
	}
	
}
