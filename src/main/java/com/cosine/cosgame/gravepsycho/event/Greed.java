package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;
import com.cosine.cosgame.gravepsycho.Player;

public class Greed extends Event{
	public Greed() {
		super();
		this.num = 23;
		this.img = "greed";
		this.name = "贪婪之手";
		this.desc = "翻出钱币牌时，若你是本回合唯一一名选择偷窃的玩家，无论成功与否，你独吞所有钱币且其他玩家不会获得钱币。";
	}
	
	public boolean distributeCoins(int x) {
		int i;
		int numSteal = 0;
		Player p = null;
		for (i=0;i<board.getPlayers().size();i++) {
			if (board.getPlayers().get(i).getDecisionLastTurn() >= Consts.THIEFDECISIONS) {
				numSteal++;
				p = board.getPlayers().get(i);
			}
		}
		if (numSteal == 1) {
			p.addMoney(x);
			board.getLogger().log("眼疾手快的" + p.getName() + "在众人发现钱币之前独吞了所有的" + x + "枚钱币。");
			return true;
		} else {
			return false;
		}
		
	}
}
