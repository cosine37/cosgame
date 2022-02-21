package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.List;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Player;
import com.cosine.cosgame.belltower.Role;

public class Investigator extends Role {
	public Investigator() {
		id = 7;
		sequence = 202;
		name = "调查员";
		img = "investigator";
		faction = Consts.HUMAN;
		group = Consts.TOWNSFOLK;
		hasFirstNight = true;
		hasRestNights = false;
		desc = "第一夜你会被告知一个爪牙身份和两名玩家，其中一名玩家是该身份。";
	}
	
	public List<String> getInstructions() {
		List<String> instruction = super.getInstructions();
		int numDay = board.getNumDay();
		int phase = board.getPhase();
		if (phase == Consts.NIGHT) {
			if (numDay == 1) {
				instruction.add("今天天亮时你会被告知一个爪牙身份和两名玩家，其中一名玩家为该身份。");
			} else {
				
			}
			instruction.add("点击确认结束你的阶段。");
		}
		return instruction;
	}
	
	public void execSkillNormal() {
		int numDay = board.getNumDay();
		if (numDay == 1) {
			List<Player> twoPlayers = twoPlayersFromGroup(Consts.MINION);
			String lastNightMsg;
			if (twoPlayers.size() < 2) {
				lastNightMsg = "啊这，本局游戏没有爪牙，所以你似乎没有获得任何信息。";
			} else {
				lastNightMsg = getMessageForTwoPlayersFromGroup(twoPlayers, true);
			}
			player.setLastNightMsg(lastNightMsg);
			//player.addLog(lastNightMsg);
		}
	}
	
	public void execSkillPoisoned() {
		int numDay = board.getNumDay();
		if (numDay == 1) {
			List<Player> twoPlayers = twoPlayersFromGroupFake(Consts.MINION);
			String lastNightMsg;
			if (twoPlayers.size() < 3) {
				lastNightMsg = "啊这，本局游戏没有爪牙，所以你似乎没有获得任何信息。";
			} else {
				String roleName = twoPlayers.get(2).getRole().getName();
				lastNightMsg = getMessageForTwoPlayersFromGroup(twoPlayers, roleName);
			}
			player.setLastNightMsg(lastNightMsg);
			//player.addLog(lastNightMsg);
		}
	}
}
