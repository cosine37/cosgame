package com.cosine.cosgame.rich;

import java.util.List;

public class Map {
	List<Place> places;
	
	public Map() {
		
	}
	
	public void genMap(int x) {
		if (x == 0) {
			places = AllRes.genTestMap();
		}
	}
	
	public Place getPlace(int x) {
		int i;
		for (i=0;i<places.size();i++) {
			if (places.get(i).getId() == x) {
				return places.get(i);
			}
		}
		return null;
	}
	
	public void printMap() {
		int i,j;
		for (i=0;i<places.size();i++) {
			String line = places.get(i).getName();
			for (j=0;j<places.get(i).getPlayersOn().size();j++) {
				line = line + " " + places.get(i).getPlayersOn().get(j).getName();
			}
			System.out.println(line);
		}
	}
}
