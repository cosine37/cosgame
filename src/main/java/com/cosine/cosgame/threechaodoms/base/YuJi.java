package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class YuJi extends Card {
	public YuJi() {
		name = "於吉";
		courtesy = "";
		img = "YuJi";
		title = "符水治病";
		faction = Consts.QUN;
		
		han = -1;
		wei = -1;
		
		desc = "王道-1，霸道-1，将墓地顶的两名武将的其中一名加入你的监狱，将另一名驱逐.";
		
		playType = Consts.CHOOSEONE;
	}
	
	public List<String> getOptionsDisplay() {
		options = new ArrayList<>();
		if (board.getTomb().size() == 0) {
			options.add("墓地没有武将，无事发生");
		} else if (board.getTomb().size() == 1) {
			String n1 = board.getTomb().get(0).getName();
			options.add("将" + n1 + "加入你的监狱");
			options.add("将" + n1 + "驱逐");
		} else {
			String n1 = board.getTomb().get(0).getName();
			String n2 = board.getTomb().get(1).getName();
			options.add("将" + n1 + "加入你的监狱，驱逐" + n2);
			options.add("将" + n2 + "加入你的监狱，驱逐" + n1);
		}
		return options;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int option = targets.get(0);
			if (board.getTomb().size() == 0) {
				
			} else if (board.getTomb().size() == 1) {
				if (option == 0) {
					Card c = board.getTomb().remove(0);
					player.getJail().add(c);
					board.log(player.getName() + "将" + c.getName() + "从墓地中加入了监狱。");
				} else if (option == 1) {
					Card c = board.getTomb().remove(0);
					board.getExile().add(c);
					board.log(player.getName() + "将" + c.getName() + "从墓地中驱逐。");
				}
			} else {
				if (option == 0) {
					Card c1 = board.getTomb().remove(0);
					Card c2 = board.getTomb().remove(0);
					player.getJail().add(c1);
					board.getExile().add(c2);
					board.log(player.getName() + "将" + c1.getName() + "从墓地中加入了监狱。", c1, "于吉招募");
					board.log(player.getName() + "将" + c2.getName() + "从墓地中驱逐。", c2, "于吉驱逐");
				} else if (option == 1) {
					Card c1 = board.getTomb().remove(0);
					Card c2 = board.getTomb().remove(0);
					player.getJail().add(c2);
					board.getExile().add(c1);
					board.log(player.getName() + "将" + c2.getName() + "从墓地中加入了监狱。", c2, "于吉招募");
					board.log(player.getName() + "将" + c1.getName() + "从墓地中驱逐。", c1, "于吉驱逐");
				}
			}
			
		}
	}
}
