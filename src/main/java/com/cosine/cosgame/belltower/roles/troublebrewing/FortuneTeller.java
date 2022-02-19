package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Player;
import com.cosine.cosgame.belltower.Role;

public class FortuneTeller extends Role {
	public FortuneTeller() {
		id = 11;
		sequence = 400;
		name = "预言家";
		img = "fortuneTeller";
		faction = Consts.HUMAN;
		group = Consts.TOWNSFOLK;
		hasFirstNight = true;
		hasRestNights = true;
		numPlayerChoose = 2;
		desc = "夜晚阶段指定两名玩家，你得知其中是否有邪恶势力。";
	}
	
	public List<String> getInstructions() {
		List<String> instruction = super.getInstructions();
		int phase = board.getPhase();
		if (phase == Consts.NIGHT) {
			instruction.add("指定两名玩家，今天天亮时你会被告知这两个玩家中是否有邪恶势力。");
			instruction.add("点击确认结束你的阶段。");
		}
		return instruction;
	}
	
	public void execSkillNormal() {
		if (player.getTargets().size() > 2) {
			int x = player.getTargets().get(1);
			int y = player.getTargets().get(2);
			Player p1 = board.getPlayers().get(x);
			Player p2 = board.getPlayers().get(y);
			boolean hasDevil = false;
			if (p1.getRole().getFaction() == Consts.DEVIL || p2.getRole().getFaction() == Consts.DEVIL) {
				hasDevil = true;
			}
			String lastNightMsg;
			if (hasDevil) {
				lastNightMsg = p1.getDisplayName() + "和" + p2.getDisplayName() + "中有邪恶势力玩家。";
			} else {
				lastNightMsg = p1.getDisplayName() + "和" + p2.getDisplayName() + "中无邪恶势力玩家。";
			}
			player.setLastNightMsg(lastNightMsg);
		}
	}
	
	public void execSkillPoisoned() {
		if (player.getTargets().size() > 2) {
			int x = player.getTargets().get(1);
			int y = player.getTargets().get(2);
			Player p1 = board.getPlayers().get(x);
			Player p2 = board.getPlayers().get(y);
			Random rand = new Random();
			boolean hasDevil = rand.nextBoolean();
			String lastNightMsg;
			if (hasDevil) {
				lastNightMsg = p1.getDisplayName() + "和" + p2.getDisplayName() + "中有邪恶势力玩家。";
			} else {
				lastNightMsg = p1.getDisplayName() + "和" + p2.getDisplayName() + "中无邪恶势力玩家。";
			}
			player.setLastNightMsg(lastNightMsg);
		}
	}
	
}
