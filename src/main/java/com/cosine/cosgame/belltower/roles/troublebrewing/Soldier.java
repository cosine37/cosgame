package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.List;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Role;

public class Soldier extends Role {
	public Soldier() {
		id = 2;
		sequence = 201;
		name = "士兵";
		img = "soldier";
		faction = Consts.HUMAN;
		group = Consts.TOWNSFOLK;
		hasFirstNight = true;
		hasRestNights = true;
		desc = "你不受恶魔技能的影响。";
	}
	
	public List<String> getInstruction() {
		List<String> instruction = super.getInstruction();
		instruction.add("你不受恶魔技能的影响。");
		instruction.add("点击确认结束你的阶段。");
		return instruction;
	}
	
	public void execSkill() {
		if (player.isPoisoned() == false) {
			player.setUnaffectedByDemon(true);
		}
	}
	
}
