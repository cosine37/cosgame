package com.cosine.cosgame.onenight.questions;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Question;
import com.cosine.cosgame.onenight.Role;

public class RightPlayerRole extends Question {
	public RightPlayerRole() {
		super();
		id = 7;
		questionText = "此时右边玩家的身份。";
	}
	
	public boolean shouldContain() {
		return true;
	}
	
	public void genAnswer() {
		Player p = player.prevPlayer();
		Manipulations.viewPlayerRole(player, p);
		answerText = "提问时右边玩家的身份已显示。";
	}
}
