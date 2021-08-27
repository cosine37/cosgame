package com.cosine.cosgame.marshbros.official;

import com.cosine.cosgame.marshbros.Ask;
import com.cosine.cosgame.marshbros.Card;
import com.cosine.cosgame.marshbros.Consts;
import com.cosine.cosgame.marshbros.Player;
import com.cosine.cosgame.marshbros.Role;

public class WangYing extends Card {
	public WangYing() {
		super();
		nickname = "矮脚虎";
		name = "王英";
		img = "WangYing";
		atk = 2;
		hp = 2;
		hasAction = true;
		actionText = "临时控制一名其它玩家的角色。";
		actionType = Consts.CHOOSEROLE;
		actionName = "好色";
	}
	
	public void activate() {
		
	}
	
	public void resolveAction(int target) {
		int playerIndex = target / 100;
		int roleIndex = target % 100;
		
		Player p = board.getPlayerByIndex(playerIndex);
		Role r = p.getArea().remove(roleIndex);
		r.setChoice(Consts.NOTCHOOSED);
		player.getArea().add(r);
		board.getLogs().logAction(player, role, actionName);
		board.getLogs().logStealRole(p, r);
		board.resolveAutoAsks();
		
	}
}
