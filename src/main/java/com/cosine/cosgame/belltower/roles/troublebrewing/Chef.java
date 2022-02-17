package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Player;
import com.cosine.cosgame.belltower.Role;

public class Chef extends Role {
	public Chef() {
		id = 8;
		sequence = 203;
		name = "厨师";
		img = "chef";
		faction = Consts.HUMAN;
		group = Consts.TOWNSFOLK;
		hasFirstNight = true;
		hasRestNights = false;
		desc = "第一夜你会被告知有几对相邻的邪恶势力玩家。";
	}
	
	public List<String> getInstructions() {
		List<String> instruction = super.getInstructions();
		int numDay = board.getNumDay();
		int phase = board.getPhase();
		if (phase == Consts.NIGHT) {
			if (numDay == 1) {
				instruction.add("今天天亮时你会被告知有几对相邻的邪恶势力玩家。");
			} else {
				
			}
			instruction.add("点击确认结束你的阶段。");
		}
		return instruction;
	}
	
	public void execSkillNormal() {
		int numDay = board.getNumDay();
		if (numDay == 1) {
			int ans = 0;
			for (int i=0;i<board.getPlayers().size();i++) {
				int j = i+1;
				if (j==board.getPlayers().size()) {
					j=0;
				}
				Player p1 = board.getPlayers().get(i);
				Player p2 = board.getPlayers().get(j);
				if (p1.getRole().getFaction() == Consts.DEVIL && p2.getRole().getFaction() == Consts.DEVIL) {
					ans++;
				}
			}
			String lastNightMsg = "游戏开始时有"+ans+"对相邻邪恶势力玩家。";
			player.setLastNightMsg(lastNightMsg);
		}
	}
	
	public void execSkillPoisoned() {
		int numDay = board.getNumDay();
		if (numDay == 1) {
			int ans = 0;
			int n = 0;
			for (int i=0;i<board.getPlayers().size();i++) {
				Player p1 = board.getPlayers().get(i);
				if (p1.getRole().getFaction() == Consts.DEVIL) {
					n++;
				}
			}
			Random rand = new Random();
			ans = rand.nextInt(n);
			String lastNightMsg = "游戏开始时有"+ans+"对相邻邪恶势力玩家。";
			player.setLastNightMsg(lastNightMsg);
		}
	}
}
