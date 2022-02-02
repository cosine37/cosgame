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
		desc = "第一夜你会被告知一个外来者身份和两名玩家，其中一名玩家是该身份（若场上无外来者，你会被告知无外来者）。";
	}
}
