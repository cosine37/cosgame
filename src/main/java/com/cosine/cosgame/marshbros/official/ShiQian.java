package com.cosine.cosgame.marshbros.official;

import com.cosine.cosgame.marshbros.Ask;
import com.cosine.cosgame.marshbros.Card;
import com.cosine.cosgame.marshbros.Consts;
import com.cosine.cosgame.marshbros.Player;
import com.cosine.cosgame.marshbros.Role;

public class ShiQian extends Card {
	public ShiQian() {
		super();
		nickname = "鼓上蚤";
		name = "时迁";
		img = "ShiQian";
		atk = 2;
		hp = 2;
		hasAction = true;
		actionText = "从除你以外的一名资源最对的玩家处窃取一点资源。";
		actionType = Consts.CHOOSERICHEST;
		actionName = "神偷";
		isStealAction = true;
	}
	
	public void activate() {
		
	}
	
	public void resolveAction(int target) {
		int playerIndex = target / 100;
		
		Player p = board.getPlayerByIndex(playerIndex);
		board.getLogs().logAction(player, role, actionName);
		board.getLogs().logSteal(player, role, p);
		if (p.getResource() > 0) {
			p.addResource(-1);
			player.addResource(1);
		}
		board.resolveAutoAsks();
		
	}
}
