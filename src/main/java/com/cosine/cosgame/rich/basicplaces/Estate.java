package com.cosine.cosgame.rich.basicplaces;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class Estate extends Place{
	int area; // can be view as color
	int cost;
	int upgradeCost;
	int level;
	
	String img;
	
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
		super.stepOn(p);
		if (isUnoccupied()) {
			
		} else {
			
		}
	}
	
	public int getRent() {
		if (level<rents.size()) {
			return rents.get(level);
		} else {
			return 0;
		}
	}

}
