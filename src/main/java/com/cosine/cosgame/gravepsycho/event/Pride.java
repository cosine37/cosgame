package com.cosine.cosgame.gravepsycho.event;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;
import com.cosine.cosgame.gravepsycho.Player;

public class Pride extends Event{
	public Pride() {
		super();
		this.num = 3;
		this.img = "pride";
		this.name = "死要面子";
		this.desc = "若你是唯一一个选择返回营地的玩家且有其他玩家选择继续冒险，你的决策改为继续冒险。";
	}
	
	public boolean singleBackHandle(Player p) {
		int totalIn = 0;
		for (int i=0;i<board.getPlayers().size();i++) {
			if (board.getPlayers().get(i).isStillIn()) {
				totalIn++;
			}
		}
		if (totalIn > 1) {
			Random rand = new Random();
			int x = rand.nextInt(2);
			String s = "但是" + p.getName() + "被其他玩家嘲笑是怂包，所以硬着头皮继续了冒险";
			if (x == 1) {
				s = "其他玩家表示谁回去谁是狗，死要面子的" + p.getName() + "只得继续冒险";
			}
			board.getLogger().log(s);
			return true;
		} else {
			return false;
		}
		
	}
}
