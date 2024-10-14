package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

public class Estate extends Place{
	int area;
	int cost;
	int upgradeCost;
	int level;
	
	Player owner;
	
	List<Integer> rents;
	
	public Estate(int id, String name) {
		super(id, name, Consts.PLACE_ESTATE);
		
		rents = new ArrayList<>();
	}
	
	public boolean isUnoccupied() {
		return owner == null;
	}
	
	public boolean isOwner(Player p) {
		if (owner == null) {
			return false;
		} else {
			return owner.getName().contentEquals(p.getName());
		}
		
	}
	
	public void stepOn(Player p) {
		if (isUnoccupied()) {
			
		} else {
			
		}
	}

}
