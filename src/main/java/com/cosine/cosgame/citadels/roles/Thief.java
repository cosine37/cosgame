package com.cosine.cosgame.citadels.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Role;

public class Thief extends Role {
	public Thief() {
		super();
		num = 2;
		name = "盗贼";
		numSkills = 1;
		img = "002";
		buttonNames.add("盗窃");
	}
	
	public Ask chooseSkill(int x) {
		int i;
		Ask ask = super.chooseSkill(x);
		if (x == 0) {
			ask.setAskId(1021);
			ask.setAskType(CitadelsConsts.CHOOSEROLE);
			ask.setMsg("请选择一个你要盗窃的角色。");
			List<String> available = new ArrayList<>();
			for (i=0;i<board.getRoles().size();i++) {
				Role r = board.getRoles().get(i);
				if (r.getNum() == num) {
					available.add("n");
				} else if (r.getOwner() == CitadelsConsts.NOTUSEDREVEALED) {
					available.add("r");
				} else if (r.getNum() == 1){
					available.add("n");
				} else if (r.getNum() == board.getKilledRole()){
					available.add("n");
				} else {
					available.add("y");
				}
			}
			ask.setLs(available);
		}
		return ask;
	}
	
	public Ask useSkill(int x, int p1, int p2, int p3, int p4) {
		Ask ask = super.useSkill(x, p1, p2, p3, p4);
		if (x == 0) {
			int roleNum = board.getRoles().get(p1).getNum();
			String roleName = board.getRoles().get(p1).getName();
			board.setStealedRole(board.getRoles().get(p1).getNum());
			board.log(player.getName() + "选择了偷窃" + Integer.toString(roleNum) + "号 " + roleName + "。");
			ask = new Ask();
			
		}
		return ask;
	}
}
