package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.List;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Player;
import com.cosine.cosgame.belltower.Role;

public class Monk extends Role {
	public Monk() {
		id = 4;
		sequence = 202;
		name = "僧侣";
		img = "monk";
		faction = Consts.HUMAN;
		group = Consts.TOWNSFOLK;
		hasFirstNight = false;
		hasRestNights = true;
		numPlayerChoose = 1;
		desc = "从第二夜起，夜晚阶段选择一名其他玩家，该玩家当天不受恶魔的技能影响。";
	}
	
	public List<String> getInstructions() {
		List<String> instruction = super.getInstructions();
		int numDay = board.getNumDay();
		int phase = board.getPhase();
		if (phase == Consts.NIGHT) {
			if (numDay == 1) {
				
			} else {
				instruction.add("选择一名其他玩家，该玩家当天不受恶魔的技能影响。");
			}
			instruction.add("点击确认结束你的阶段。");
		}
		return instruction;
	}
	
	public void execSkillNormal() {
		if (player.getTargets().size() > 0) {
			int x = player.getTargets().get(1);
			Player p = board.getPlayers().get(x);
			p.setUnaffectedByDemon(true);
		}
	}
	
}
