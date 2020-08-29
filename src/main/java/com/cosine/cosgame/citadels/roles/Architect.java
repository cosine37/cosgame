package com.cosine.cosgame.citadels.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Role;

public class Architect extends Role {
	public Architect() {
		super();
		num = 7;
		name = "建筑师";
		numSkills = 0;
		img = "007";
	}
	
	public Ask afterTakeAction() {
		Ask ask = super.afterTakeAction();
		player.draw(2);
		board.log(player.getName() + "额外摸了两张牌。");
		return ask;
	}
	
	public void alterPlayerAbility() {
		super.alterPlayerAbility();
		int x = player.getBuildLimit();
		x = x+2;
		player.setBuildLimit(x);
	}
	
}
