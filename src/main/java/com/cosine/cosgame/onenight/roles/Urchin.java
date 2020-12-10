package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Urchin extends Role{
	public Urchin() {
		super();
		roleNum = 4;
		side = Consts.HUMAN;
		img = "r04";
		sequence = 700;
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.EXCHANGE);
		}
		if (t2 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t2, Consts.EXCHANGE);
		}
	}
	
	public void executeSkill() {
		int t1 = -1;
		int t2 = -1;
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.EXCHANGE) {
				if (t1 == -1) {
					t1 = i;
				} else {
					t2 = i;
				}
			}
		}
		if (t1 != -1 && t2 != -1) {
			Player p1 = board.getPlayers().get(t1);
			Player p2 = board.getPlayers().get(t2);
			Role r1 = p1.getCurrentRole();
			Role r2 = p2.getCurrentRole();
			p2.addRole(r1);
			p1.addRole(r2);
		}
	}
	
}
