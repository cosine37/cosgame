package com.cosine.cosgame.onenight.questions;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Question;
import com.cosine.cosgame.onenight.Role;

public class LeftPlayerRole extends Question {
	public LeftPlayerRole() {
		super();
		id = 6;
		questionText = "此时左边玩家的身份。";
	}
	
	public boolean shouldContain() {
		return true;
	}
	
	public void genAnswer() {
		Player p = player.nextPlayer();
		Manipulations.viewPlayerRole(player, p);
		answerText = "提问时左边玩家的身份已显示。";
	}
}
