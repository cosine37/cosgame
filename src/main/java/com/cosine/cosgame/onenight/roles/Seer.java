package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Seer extends Role{
	public Seer() {
		super();
		roleNum = 2;
		side = Consts.HUMAN;
		img = "r02";
		sequence = 500;
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 >= 100 && t1 <= 102) {
			int x = t1-100;
			player.getCenterMarks().set(x, Consts.TARGET);
		}
		if (t2 >= 100 && t2 <= 102) {
			int x = t2-100;
			player.getCenterMarks().set(x, Consts.TARGET);
		}
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				int x = board.getPlayers().get(i).getCurrentRole().getRoleNum();
				player.getPlayerMarks().set(i, x);
			}
		}
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) == Consts.TARGET) {
				int x = board.getCurCenterRole(i).getRoleNum();
				player.getCenterMarks().set(i, x);
			}
		}
	}
}
