package com.cosine.cosgame.onenight.questions;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Question;
import com.cosine.cosgame.onenight.Role;

public class AnotherGypsy extends Question {
	public AnotherGypsy() {
		super();
		id = 1;
		questionText = "此时另一张吉普赛娘的位置。";
	}
	
	public boolean shouldContain() {
		int i;
		int numGypsy = 0;
		for (i=0;i<board.getRolesThisGame().size();i++) {
			Role r = board.getRolesThisGame().get(i);
			if (r.getRoleNum() == Consts.GYPSY) {
				numGypsy++;
			}
		}
		if (numGypsy == 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public void genAnswer() {
		int i;
		answerText = "显示的身份为提问时另一张吉普赛娘身份的位置。";
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getIndex() == player.getIndex()) {
				continue;
			}
			if (p.getCurrentRole().getRoleNum() == Consts.GYPSY) {
				player.getPlayerMarks().set(p.getIndex(), p.getCurrentRole().getRoleNumToShow());
			}
		}
		for (i=0;i<board.getCenterRoles().size();i++) {
			Role r = board.getCurCenterRole(i);
			if (r.getRoleNum() == Consts.GYPSY) {
				player.getCenterMarks().set(i, r.getRoleNumToShow());
			}
		}
	}
}
