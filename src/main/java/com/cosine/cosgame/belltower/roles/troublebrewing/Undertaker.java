package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Player;
import com.cosine.cosgame.belltower.Role;

public class Undertaker extends Role {
	public Undertaker() {
		id = 10;
		sequence = 600;
		name = "掘墓人";
		img = "undertaker";
		faction = Consts.HUMAN;
		group = Consts.TOWNSFOLK;
		hasFirstNight = false;
		hasRestNights = true;
		numPlayerChoose = 1;
		desc = "夜晚阶段你得知前一个白天被处决的玩家的身份。";
	}
	
	public List<String> getInstructions() {
		List<String> instruction = super.getInstructions();
		int numDay = board.getNumDay();
		int phase = board.getPhase();
		if (phase == Consts.NIGHT) {
			if (numDay == 1) {
				
			} else {
				instruction.add("今天天亮时你会被告知前一个白天被处决的玩家的身份。");
			}
			instruction.add("点击确认结束你的阶段。");
		}
		return instruction;
	}
	
	public void execSkillNormal() {
		String lastNightMsg;
		int executedIndex = board.getExecutedIndex();
		if (executedIndex>=0 && executedIndex<board.getPlayers().size()) {
			Player p = board.getPlayers().get(executedIndex);
			lastNightMsg = "前一个白天被处决的玩家 " + p.getDisplayName() + " 的身份是 " + p.getRole().getName() + " 。";
		} else {
			lastNightMsg = "前一个白天没有玩家被处决。";
		}
		player.setLastNightMsg(lastNightMsg);
	}
	
	public void execSkillPoisoned() {
		String lastNightMsg;
		int executedIndex = board.getExecutedIndex();
		if (executedIndex>=0 && executedIndex<board.getPlayers().size()) {
			List<Integer> playerIndexes = new ArrayList<>();
			for (int i=0;i<board.getPlayers().size();i++) {
				playerIndexes.add(i);
			}
			int fakeExecutedIndex = executedIndex;
			while (playerIndexes.size()>0) {
				Random rand = new Random();
				int x = rand.nextInt(playerIndexes.size());
				int y = playerIndexes.remove(x);
				if (y != player.getIndex()) {
					fakeExecutedIndex = y;
					break;
				}
			}
			Player p = board.getPlayers().get(executedIndex);
			Player faker = board.getPlayers().get(fakeExecutedIndex);
			lastNightMsg = "前一个白天被处决的玩家 " + p.getDisplayName() + " 的身份是 " + faker.getRole().getName() + " 。";
		} else {
			lastNightMsg = "前一个白天没有玩家被处决。";
		}
		player.setLastNightMsg(lastNightMsg);
	}
	
}
