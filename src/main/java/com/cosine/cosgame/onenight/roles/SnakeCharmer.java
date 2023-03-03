package com.cosine.cosgame.onenight.roles;

import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class SnakeCharmer extends Role{
	public SnakeCharmer() {
		super();
		roleNum = 90;
		side = Consts.HUMAN;
		img = "r90";
		sequence = -750;
		name = "耍蛇人";
		choosePlayerNum = 1;
		hasDusk = true;
		mandatory = true;
		duskMsg.add("你的身份是 耍蛇人。");
		duskMsg.add("指定一名其他玩家，你与该玩家其中之一会中毒（若该玩家不会中毒，则你必定中毒），夜晚阶段开始前查看该玩家的身份。");
		duskMsg.add("选择后点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 耍蛇人。");
		nightMsg.add("场上显示的身份牌是你发动你技能时指定的身份牌。你与该玩家其中之一中毒（若该玩家不会中毒，则你必定中毒）。");
		nightMsg.add("因为查看身份的时机为夜晚阶段开始前，所以如果你已中毒，该玩家的身份会随机显示。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 耍蛇人。");
		confirmedMsg.add("场上显示的身份牌是你发动你技能时指定的身份牌。你与该玩家其中之一中毒（若该玩家不会中毒，则你必定中毒）。");
		confirmedMsg.add("因为查看身份的时机为夜晚阶段开始前，所以如果你已中毒，该玩家的身份会随机显示。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 耍蛇人，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的身份牌是你发动你技能时指定的身份牌。你与该玩家其中之一中毒（若该玩家不会中毒，则你必定中毒）。");
		dayMsg.add("因为查看身份的时机为夜晚阶段开始前，所以如果你已中毒，该玩家的身份会随机显示。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 耍蛇人，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				Random rand = new Random();
				int x = rand.nextInt(10000);
				if (x%2 == 0) {
					p.poison();
				}
				if (!p.isPoisoned()) {
					player.poison();
				}
			}
		}
	}
	
	public void onNightSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				Manipulations.viewPlayerRole(player, p);
			}
		}
	}
	
	public void onNightSkillPoisoned() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				Manipulations.viewPlayerRolePoisoned(player, p);
			}
		}
	}
	
}
