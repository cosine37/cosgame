package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.List;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Player;
import com.cosine.cosgame.belltower.Role;

public class Poisoner extends Role{
	public Poisoner() {
		id = 5;
		sequence = 100;
		name = "投毒者";
		img = "poisoner";
		faction = Consts.DEVIL;
		group = Consts.MINION;
		hasFirstNight = true;
		hasRestNights = true;
		numPlayerChoose = 1;
		desc = "夜晚阶段指定一名玩家，该玩家在当晚和接下来的白天中毒。";
	}
	
	public List<String> getInstructions() {
		List<String> instruction = super.getInstructions();
		int phase = board.getPhase();
		if (phase == Consts.NIGHT) {
			instruction.add("选择一名玩家，该玩家在今夜和接下来的白天中毒。");
			instruction.add("点击确认结束你的阶段。");
		}
		return instruction;
	}
	
	public void execSkillNormal() {
		if (player.getTargets().size() > 0) {
			int x = player.getTargets().get(1);
			Player p = board.getPlayers().get(x);
			p.poison();
		}
	}
}
