package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		numPlayerChoose = 1;
		desc = "从第二夜起，夜晚阶段击杀一名玩家。";
	}
	
	public List<String> getInstructions() {
		List<String> instruction = super.getInstructions();
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
	
	public void execSkillNormal() {
		if (player.getTargets().size() > 0) {
			int x = player.getTargets().get(1);
			Player p = board.getPlayers().get(x);
			if (p.isUnaffectedByDemon() == false) {
				p.getRole().onKilled();
				p.setAlive(false);
				board.addKilledIndex(x);
			}
		}
	}
	
	public void onKilled() {
		//TODO: add Scarlet Woman Handles
		int i;
		List<Integer> a = new ArrayList<>();
		for (i=0;i<board.getPlayers().size();i++) {
			a.add(i);
		}
		while (a.size()>0) {
			int n = a.size();
			Random rand = new Random();
			int x = rand.nextInt(n);
			int y = a.remove(x);
			if (board.getPlayers().get(y).getRole().getGroup() == Consts.MINION) {
				Role imp = new Imp();
				board.getPlayers().get(y).setRole(imp);
			}
		}
	}
}
