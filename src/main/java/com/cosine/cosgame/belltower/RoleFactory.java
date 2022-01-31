package com.cosine.cosgame.belltower;

import com.cosine.cosgame.belltower.roles.troublebrewing.*;

public class RoleFactory {
	public static Role makeRole(int roleId) {
		Role role = new Role();
		
		if (roleId == 1) {
			role = new Librarian();
		}
		
		return role;
	}
}
