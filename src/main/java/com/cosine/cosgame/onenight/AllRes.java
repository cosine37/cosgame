package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.onenight.roles.*;

public class AllRes {
	List<Role> allRoles;
	
	public AllRes() {
		genAllRoles();
	}
	
	public void genAllRoles() {
		allRoles = new ArrayList<>();
		Role r;
		int i;
		for (i=0;i<3;i++) {
			r = new Villager();
			allRoles.add(r);
		}
		for (i=0;i<2;i++) {
			r = new Werewolf();
			allRoles.add(r);
		}
		r = new Minion();
		allRoles.add(r);
		r = new Seer();
		allRoles.add(r);
		for (i=0;i<2;i++) {
			r = new Mason();
			allRoles.add(r);
		}
		r = new Thief();
		allRoles.add(r);
		r = new Urchin();
		allRoles.add(r);
		r = new Drunk();
		allRoles.add(r);
		r = new Insomniac();
		allRoles.add(r);
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}
	
}
