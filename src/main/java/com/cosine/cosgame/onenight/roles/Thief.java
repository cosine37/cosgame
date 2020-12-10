package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Thief extends Role{
	public Thief() {
		super();
		roleNum = 3;
		side = Consts.HUMAN;
		img = "r03";
		sequence = 600;
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.EXCHANGE);
		}
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.EXCHANGE) {
				Player p = board.getPlayers().get(i);
				Role r1 = p.getCurrentRole();
				Role r2 = player.getCurrentRole();
				p.addRole(r2);
				player.addRole(r1);
			}
		}
	}
	
	
}
