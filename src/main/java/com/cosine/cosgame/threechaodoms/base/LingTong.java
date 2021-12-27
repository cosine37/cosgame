package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class LingTong extends Card {
	public LingTong() {
		name = "凌統";
		courtesy = "公績";
		img = "LingTong";
		title = "親賢禮士";
		faction = Consts.WU;
		
		desc = "王道+1或霸道+1。将你和另一名玩家阵面的各一名武将交换。";
		
		playType = Consts.CHOOSEPLAYMEOTHEROPTION;
		options.add("王道+1");
		options.add("霸道+1");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >3) {
			int o = targets.get(0);
			if (o == 0) {
				board.moveHan(1);
			} else if (o == 1) {
				board.moveWei(1);
			}
			int x = targets.get(1);
			int y = targets.get(2);
			int z = targets.get(3);
			Player p = board.getPlayerByIndex(x);
			if (player != null && p.getPlay().size() > y && player.getPlay().size() > z) {
				Card c1 = p.getPlay().remove(y);
				Card c2 = player.getPlay().remove(z);
				p.getPlay().add(c2);
				player.getPlay().add(c1);
				board.log(p.getName() + "阵面的" + c1.getName() + "与" + player.getName() + "阵面的" + c2.getName() + "交换了。");
			}
		}
		
	}
}
