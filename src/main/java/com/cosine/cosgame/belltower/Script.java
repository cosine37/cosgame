package com.cosine.cosgame.belltower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Script {
	int id;
	List<Role> townsfolks;
	List<Role> outsiders;
	List<Role> demons;
	List<Role> minions;
	
	public List<Role> getRoles(List<Integer> factionCounts){
		List<Role> roles = new ArrayList<>();
		if (factionCounts.size()>3) {
			roles = getRoles(factionCounts.get(0), factionCounts.get(1), factionCounts.get(2), factionCounts.get(3));
		}
		return roles;
	}
	
	public List<Role> getRoles(int t, int o, int d, int m){
		List<Role> roles = new ArrayList<>();
		roles.addAll(getNumRoles(Consts.TOWNSFOLK, t));
		roles.addAll(getNumRoles(Consts.OUTSIDER, o));
		roles.addAll(getNumRoles(Consts.DEMON, d));
		roles.addAll(getNumRoles(Consts.MINION, m));
		return roles;
	}
	
	public List<Role> getNumRoles(int category, int num){
		List<Role> roles = new ArrayList<>();
		List<Role> tempRoles = new ArrayList<>();
		int i;
		if (category == Consts.TOWNSFOLK) {
			for (i=0;i<townsfolks.size();i++) {
				tempRoles.add(townsfolks.get(i));
			}
		} else if (category == Consts.OUTSIDER) {
			for (i=0;i<outsiders.size();i++) {
				tempRoles.add(outsiders.get(i));
			}
		} else if (category == Consts.DEMON) {
			for (i=0;i<demons.size();i++) {
				tempRoles.add(demons.get(i));
			}
		} else if (category == Consts.MINION) {
			for (i=0;i<minions.size();i++) {
				tempRoles.add(minions.get(i));
			}
		}
		Random rand = new Random();
		for (i=0;i<num;i++) {
			if (tempRoles.size() == 0) break;
			int x = rand.nextInt(tempRoles.size());
			Role r = tempRoles.remove(x);
			roles.add(r);
		}
		return roles;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Role> getTownsfolks() {
		return townsfolks;
	}
	public void setTownsfolks(List<Role> townsfolks) {
		this.townsfolks = townsfolks;
	}
	public List<Role> getOutsiders() {
		return outsiders;
	}
	public void setOutsiders(List<Role> outsiders) {
		this.outsiders = outsiders;
	}
	public List<Role> getDemons() {
		return demons;
	}
	public void setDemons(List<Role> demons) {
		this.demons = demons;
	}
	public List<Role> getMinions() {
		return minions;
	}
	public void setMinions(List<Role> minions) {
		this.minions = minions;
	}
	
}
