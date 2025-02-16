package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;
import com.cosine.cosgame.gravepsycho.Player;

public class Pilfer extends Event{
	public Pilfer() {
		super();
		this.num = 24;
		this.img = "pilfer";
		this.name = "顺手牵羊";
		this.desc = "若你是本回合唯一一名选择偷窃的玩家，无论成功与否，在返回营地结算之前，你获得所有已展示的宝物。";
	}
	
	public void preBackStealHandle() {
		int i;
		int numThief = 0;
		Player p = null;
		for (i=0;i<board.getPlayers().size();i++) {
			if (board.getPlayers().get(i).getDecision() >= Consts.THIEFDECISIONS) {
				numThief++;
				p = board.getPlayers().get(i);
			}
		}
		if (numThief == 1) {
			for (i=board.getRevealed().size()-1;i>=0;i--) {
				if (board.getRevealed().get(i).getType() == Consts.TREASURE) {
					Card c = board.getRevealed().remove(i);
					board.getRemoved().add(c);
					p.addMoney(c.getNum());
					board.getLogger().log(p.getName() + "顺手牵走了宝物：" + c.getTreasureName());
				}
			}
		}
	}
}
