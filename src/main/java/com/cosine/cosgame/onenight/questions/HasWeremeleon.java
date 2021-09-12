package com.cosine.cosgame.onenight.questions;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Question;
import com.cosine.cosgame.onenight.Role;

public class HasWeremeleon extends Question {
	public HasWeremeleon() {
		super();
		id = 4;
		questionText = "此时是否有玩家的身份是变色狼。";
	}
	
	public boolean shouldContain() {
		int i;
		int numWeremeleon = 0;
		for (i=0;i<board.getRolesThisGame().size();i++) {
			Role r = board.getRolesThisGame().get(i);
			if (r.getRoleNum() == Consts.WEREMELEON) {
				numWeremeleon++;
			}
		}
		if (numWeremeleon > 0) {
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
			if (p.getCurrentRole().getRoleNum() == Consts.WEREMELEON) {
				flag = true;
			}
		}
		if (flag) {
			answerText = "提问时，有某名玩家的身份为变色狼。";
		} else {
			answerText = "提问时，没有玩家的身份为变色狼。";
		}
	}
}
