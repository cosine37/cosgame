package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Player;
import com.cosine.cosgame.belltower.Role;

public class Empath extends Role {
	public Empath() {
		id = 9;
		sequence = 300;
		name = "共情者";
		img = "empath";
		faction = Consts.HUMAN;
		group = Consts.TOWNSFOLK;
		hasFirstNight = true;
		hasRestNights = true;
		desc = "夜晚阶段你得知当时你邻座的存活玩家有几个是邪恶势力。";
	}
	
	public List<String> getInstructions() {
		List<String> instruction = super.getInstructions();
		int phase = board.getPhase();
		if (phase == Consts.NIGHT) {
			instruction.add("今天天亮时你会被告知在你行动的时候你邻座的存活玩家有几个是邪恶势力。");
			instruction.add("点击确认结束你的阶段。");
		}
		return instruction;
	}
	
	public void execSkillNormal() {
		int numDevil = 0;
		int leftIndex = player.getIndex();
		int rightIndex = player.getIndex();
		while (true) {
			leftIndex = leftIndex-1;
			if (leftIndex<0) {
				leftIndex = board.getPlayers().size()+leftIndex;
			}
			if (leftIndex == player.getIndex()) break;
			Player p = board.getPlayers().get(leftIndex);
			if (p.isAlive()) {
				if (p.getRole().getFaction() == Consts.DEVIL) {
					numDevil++;
					break;
				}
			}
		}
		while (true) {
			rightIndex = rightIndex+1;
			if (rightIndex>=board.getPlayers().size()) {
				rightIndex = rightIndex-board.getPlayers().size();
			}
			if (rightIndex == player.getIndex()) break;
			Player p = board.getPlayers().get(rightIndex);
			if (p.isAlive()) {
				if (p.getRole().getFaction() == Consts.DEVIL) {
					if (rightIndex != leftIndex) {
						numDevil++;
					}
					break;
				}
			}
		}
		String lastNightMsg = "今晚在你行动时你邻座有"+numDevil+"名邪恶势力玩家。";
		player.setLastNightMsg(lastNightMsg);
	}
	
	public void execSkillPoisoned() {
		int numDevil = 0;
		int numNeighbours = 0;
		int leftIndex = player.getIndex();
		int rightIndex = player.getIndex();
		while (true) {
			leftIndex = leftIndex-1;
			if (leftIndex<0) {
				leftIndex = board.getPlayers().size()+leftIndex;
			}
			if (leftIndex == player.getIndex()) break;
			Player p = board.getPlayers().get(leftIndex);
			if (p.isAlive()) {
				numNeighbours++;
			}
		}
		while (true) {
			rightIndex = rightIndex+1;
			if (rightIndex>=board.getPlayers().size()) {
				rightIndex = rightIndex-board.getPlayers().size();
			}
			if (rightIndex == player.getIndex()) break;
			Player p = board.getPlayers().get(rightIndex);
			if (p.isAlive()) {
				if (p.getRole().getFaction() == Consts.DEVIL) {
					if (rightIndex != leftIndex) {
						numNeighbours++;
					}
					break;
				}
			}
		}
		Random rand = new Random();
		numDevil = rand.nextInt(numNeighbours+1);
		String lastNightMsg = "今晚在你行动时你邻座有"+numDevil+"名邪恶势力玩家。";
		player.setLastNightMsg(lastNightMsg);
	}
	
}
