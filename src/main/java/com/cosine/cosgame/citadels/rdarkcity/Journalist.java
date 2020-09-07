package com.cosine.cosgame.citadels.rdarkcity;

import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Role;

public class Journalist extends Role {
	public Journalist() {
		super();
		num = 9;
		name = "记者";
		numSkills = 0;
		img = "109";
	}
	
	public void whenReveal() {
		super.whenReveal();
		int playerIndex = board.getPlayerIndex(player);
		int prevPlayer = playerIndex-1;
		if (prevPlayer<0) {
			prevPlayer = prevPlayer+board.getPlayers().size();
		}
		int nextPlayer = playerIndex+1;
		if (nextPlayer == board.getPlayers().size()) {
			nextPlayer = 0;
		}
		boolean getBenefit = false;
		if (board.getPlayers().get(prevPlayer).getRole().getNum() == 4) {
			getBenefit = true;
		}
		if (board.getPlayers().get(nextPlayer).getRole().getNum() == 4) {
			getBenefit = true;
		}
		if (getBenefit) {
			player.addCoin(3);
			board.log("因为在4号角色旁边，" + player.getName() + "获得3￥。");
		}
	}
}
