package com.cosine.cosgame.onenight;

import com.cosine.cosgame.onenight.roles.*;

public class RoleFactory {
	public static Role createRole(String img) {
		Role role;
		if (img.contentEquals("r00")) {
			role = new Villager();
		} else if (img.contentEquals("r01")) {
			role = new Werewolf();
		} else if (img.contentEquals("r02")) {
			role = new Seer();
		} else if (img.contentEquals("r03")) {
			role = new Thief();
		} else if (img.contentEquals("r04")) {
			role = new Urchin();
		} else if (img.contentEquals("r05")) {
			role = new Minion();
		} else if (img.contentEquals("r06")) {
			role = new Insomniac();
		} else if (img.contentEquals("r07")) {
			role = new Drunk();
		}
		
		else {
			role = new Role();
			role.setImg(img);
		}
		
		return role;
	}
	
	public static Role createRole(int num) {
		Role role;
		if (num == 0) {
			role = new Villager();
		} else if (num == 1) {
			role = new Werewolf();
		} else if (num == 2) {
			role = new Seer();
		} else if (num == 3) {
			role = new Thief();
		} else if (num == 4) {
			role = new Urchin();
		} else if (num == 5) {
			role = new Minion();
		} else if (num == 6) {
			role = new Insomniac();
		} else if (num == 7) {
			role = new Drunk();
		}
		
		else {
			role = new Role();
			role.setRoleNum(num);
		}
		
		return role;
	}
}
