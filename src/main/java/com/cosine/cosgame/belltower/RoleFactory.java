package com.cosine.cosgame.belltower;

import com.cosine.cosgame.belltower.roles.troublebrewing.*;

public class RoleFactory {
	public static Role makeRole(int roleId) {
		Role role = new Role();
		
		if (roleId == 1) {
			role = new Librarian();
		} else if (roleId == 2) {
			role = new Soldier();
		} else if (roleId == 3) {
			role = new Imp();
		} else if (roleId == 4) {
			role = new Monk();
		} else if (roleId == 5) {
			role = new Poisoner();
		} else if (roleId == 6) {
			role = new Washerwoman();
		} else if (roleId == 7) {
			role = new Investigator();
		} else if (roleId == 8) {
			role = new Chef();
		}
		
		return role;
	}
}
