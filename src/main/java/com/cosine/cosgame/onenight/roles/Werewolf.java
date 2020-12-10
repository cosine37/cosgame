package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Werewolf extends Role{
	public Werewolf() {
		super();
		roleNum = 1;
		side = Consts.WOLF;
		img = "r01";
		sequence = 200;
	}
	
	public void vision() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getRoleNum() == Consts.WEREWOLF) {
				player.getPlayerMarks().set(i, 1);
			}
		}
	}
}
