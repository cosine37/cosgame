package com.cosine.cosgame.marshbros.official;

import com.cosine.cosgame.marshbros.Ask;
import com.cosine.cosgame.marshbros.Card;
import com.cosine.cosgame.marshbros.Consts;
import com.cosine.cosgame.marshbros.Player;
import com.cosine.cosgame.marshbros.Role;

public class HuaRong extends Card {
	public HuaRong() {
		super();
		nickname = "小李广";
		name = "花荣";
		img = "HuaRong";
		atk = 3;
		hp = 1;
		hasAction = true;
		actionText = "击倒一名你选择的在场角色。";
		actionType = Consts.CHOOSEROLE;
		actionName = "神箭";
	}
	
	public void activate() {
		
	}
	
	public void resolveAction(int target) {
		int playerIndex = target / 100;
		int roleIndex = target % 100;
		
		Player p = board.getPlayerByIndex(playerIndex);
		Role r = p.getArea().get(roleIndex);
		r.setHp(0);
		board.getLogs().logAction(player, role, actionName);
		board.getLogs().logKnockdown(p, r);
		board.addMoveToTombAsk(p, roleIndex);
		r.getCard().lastWish();
		board.resolveAutoAsks();
		
	}
}
