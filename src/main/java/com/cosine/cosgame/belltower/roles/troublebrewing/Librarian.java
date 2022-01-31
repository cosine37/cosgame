package com.cosine.cosgame.belltower.roles.troublebrewing;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Role;

public class Librarian extends Role {
	public Librarian() {
		id = 1;
		name = "图书馆长";
		img = "librarian";
		faction = Consts.HUMAN;
		group = Consts.TOWNSFOLK;
	}
}
