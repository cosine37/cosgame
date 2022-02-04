package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.List;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Player;
import com.cosine.cosgame.belltower.Role;

public class Imp extends Role{
	public Imp() {
		id = 3;
		sequence = 300;
		name = "小恶魔";
		img = "imp";
		faction = Consts.DEVIL;
		group = Consts.DEMON;
		hasFirstNight = false;
		hasRestNights = true;
		desc = "从第二夜起，夜晚阶段击杀一名玩家。";
	}
	
	public List<String> getInstruction() {
		List<String> instruction = super.getInstruction();
		int numDay = board.getNumDay();
		int phase = board.getPhase();
		if (phase == Consts.NIGHT) {
			if (numDay == 1) {
				
			} else {
				instruction.add("选择一名玩家，将其击杀。");
			}
			instruction.add("点击确认结束你的阶段。");
		}
		return instruction;
	}
	
	public void execSkill() {
		if (player.getTargets().size() > 0) {
			int x = player.getTargets().get(0);
			Player p = board.getPlayers().get(x);
			if (p.isUnaffectedByDemon() == false) {
				p.setAlive(false);
				board.addKilledIndex(x);
			}
		}
	}
}
