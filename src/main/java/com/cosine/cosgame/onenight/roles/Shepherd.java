package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Shepherd extends Role{
	public Shepherd() {
		super();
		roleNum = 35;
		side = Consts.HUMAN;
		img = "r35";
		sequence = 900;
		name = "牧羊人";
		nightMsg.add("你的初始身份是 牧羊人。");
		nightMsg.add("点击确认结束你的夜晚阶段，天亮时你可以看到一名身份发生变化的其他玩家。");
		confirmedMsg.add("你的初始身份是 牧羊人。");
		confirmedMsg.add("天亮时你可以看到一名身份发生变化的其他玩家。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 牧羊人，你现在的身份可能已有变化。");
		dayMsg.add("拥有交换标记的玩家是身份已发生变化的玩家，若无标记，则无其他玩家身份发生改变。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 牧羊人，你现在的身份可能已有变化。");
		votedMsg.add("拥有交换标记的玩家是身份已发生变化的玩家，若无标记，则无其他玩家身份发生改变。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		List<Integer> is = new ArrayList<>();
		for (int i=0;i<board.getPlayers().size();i++) {
			if (i != player.getIndex()) {
				is.add(i);
			}
		}
		while (is.size() > 0) {
			Random rand = new Random();
			int size = is.size();
			int x = rand.nextInt(size);
			int y = is.remove(x);
			Player p = board.getPlayers().get(y);
			if (p.getRoles().size() > 1) {
				player.getPlayerMarks().set(y, Consts.EXCHANGE);
				return;
			}
		}
	}
	
	
}
