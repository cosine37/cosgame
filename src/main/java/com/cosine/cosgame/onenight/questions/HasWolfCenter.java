package com.cosine.cosgame.onenight.questions;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Question;
import com.cosine.cosgame.onenight.Role;

public class HasWolfCenter extends Question {
	public HasWolfCenter() {
		super();
		id = 5;
		questionText = "此时中央身份是否有狼阵营。";
	}
	
	public boolean shouldContain() {
		return true;
	}
	
	public void genAnswer() {
		int i;
		boolean flag = false;
		for (i=0;i<board.getCenterRoles().size();i++) {
			Role r = board.getCurCenterRole(i);
			if (r.getSide() == Consts.WOLF) {
				flag = true;
			}
		}
		if (flag) {
			answerText = "提问时，有至少一张中央身份为狼阵营。";
		} else {
			answerText = "提问时，中央身份都不是狼阵营。";
		}
	}
}
