package com.cosine.cosgame.marshbros.official;

import com.cosine.cosgame.marshbros.Ask;
import com.cosine.cosgame.marshbros.Card;
import com.cosine.cosgame.marshbros.Consts;
import com.cosine.cosgame.marshbros.Player;
import com.cosine.cosgame.marshbros.Role;

public class HuangFuDuan extends Card {
	public HuangFuDuan() {
		super();
		nickname = "紫髯伯";
		name = "皇甫端";
		img = "HuangFuDuan";
		atk = 2;
		hp = 1;
		hasAction = true;
		actionText = "增加一名角色一点体力，若该角色为兽，则额外增加一点体力。";
		actionType = Consts.CHOOSEROLE;
		actionName = "医术";
	}
	
	public void activate() {
		
	}
	
	public void resolveAction(int target) {
		int playerIndex = target / 100;
		int roleIndex = target % 100;
		
		board.getLogs().logAction(player, role, actionName);
		Player p = board.getPlayerByIndex(playerIndex);
		Role r = p.getArea().get(roleIndex);
		if (r.getCard().isBeast()) {
			r.addHp(2);
		} else {
			r.addHp(1);
		}
		board.resolveAutoAsks();
		
	}
}
