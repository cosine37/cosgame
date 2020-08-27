package com.cosine.cosgame.citadels.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Role;

public class Merchant extends Role {
	public Merchant() {
		super();
		num = 6;
		name = "实业家";
		numSkills = 1;
		img = "006";
		color = CitadelsConsts.GREEN;
		buttonNames.add("benefit");
	}
	
	public Ask chooseSkill(int x) {
		Ask ask = super.chooseSkill(x);
		if (x == 0) {
			ask = useSkill(0,0,0,0,0);
		}
		return ask;
	}
	
	public Ask useSkill(int x, int p1, int p2, int p3, int p4) {
		Ask ask = super.useSkill(x, p1, p2, p3, p4);
		if (x == 0) {
			benefit();
			ask = new Ask();
			
		}
		return ask;
	}
	
	public Ask afterTakeAction() {
		Ask ask = super.afterTakeAction();
		player.addCoin(1);
		board.log(player.getName() + " receives 1 extra coin.");
		return ask;
	}
}
