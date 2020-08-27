package com.cosine.cosgame.citadels.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Role;

public class Assassin extends Role {
	public Assassin() {
		super();
		num = 1;
		name = "送葬者";
		numSkills = 1;
		img = "001";
		buttonNames.add("kill");
	}
	
	public Ask chooseSkill(int x) {
		int i;
		Ask ask = super.chooseSkill(x);
		if (x == 0) {
			ask.setAskId(1001);
			ask.setAskType(CitadelsConsts.CHOOSEROLE);
			ask.setMsg("Please choose a role to kill");
			List<String> available = new ArrayList<>();
			for (i=0;i<board.getRoles().size();i++) {
				Role r = board.getRoles().get(i);
				if (r.getNum() == num) {
					available.add("n");
				} else if (r.getOwner() == CitadelsConsts.NOTUSEDREVEALED) {
					available.add("r");
				} else {
					available.add("y");
				}
			}
		}
		return ask;
	}
	
	public void useSkill(int x, int p1, int p2, int p3, int p4) {
		super.useSkill(x, p1, p2, p3, p4);
		if (x == 0) {
			board.setKilledRole(p1);
		}
	}
}
