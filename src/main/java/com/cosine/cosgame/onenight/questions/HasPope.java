package com.cosine.cosgame.onenight.questions;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Question;
import com.cosine.cosgame.onenight.Role;

public class HasPope extends Question {
	public HasPope() {
		super();
		id = 3;
		questionText = "此时是否有玩家的身份是教宗。";
	}
	
	public boolean shouldContain() {
		int i;
		int numPope = 0;
		for (i=0;i<board.getRolesThisGame().size();i++) {
			Role r = board.getRolesThisGame().get(i);
			if (r.getRoleNum() == Consts.POPE) {
				numPope++;
			}
		}
		if (numPope > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void genAnswer() {
		int i;
		boolean flag = false;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getCurrentRole().getRoleNum() == Consts.POPE) {
				flag = true;
			}
		}
		if (flag) {
			answerText = "提问时，有某名玩家的身份为教宗。";
		} else {
			answerText = "提问时，没有玩家的身份为教宗。";
		}
	}
}
