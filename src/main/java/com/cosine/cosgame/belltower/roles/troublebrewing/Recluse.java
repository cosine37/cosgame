package com.cosine.cosgame.belltower.roles.troublebrewing;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.Role;
import com.cosine.cosgame.belltower.RoleFactory;

public class Recluse extends Role {
	public Recluse() {
		id = 12;
		sequence = 0;
		name = "隐士";
		img = "recluse";
		faction = Consts.HUMAN;
		group = Consts.OUTSIDER;
		hasFirstNight = false;
		hasRestNights = false;
		desc = "你会被当成某邪恶势力的身份，即使你已死去。";
	}
	
	public List<String> getInstructions() {
		List<String> instruction = super.getInstructions();
		instruction.add("点击确认结束你的阶段。");
		return instruction;
	}
	
	public int getFaction() {
		if (board.getStatus() == Consts.INGAME) {
			Random rand = new Random();
			int tempFaction = rand.nextInt(2) + 1;
			return tempFaction;
		} else {
			return super.getFaction();
		}
	}
	public int getGroup() {
		if (board.getStatus() == Consts.INGAME) {
			int recluseIndex = board.getExtraBits().get(Consts.RECLUSEINDEX);
			int tempGroup = super.getGroup();
			Random rand = new Random();
			int x = rand.nextInt(2);
			if (recluseIndex>=0 && x==1) {
				Role r = RoleFactory.makeRole(recluseIndex);
				tempGroup = r.getGroup();
			}
			return tempGroup;
		} else {
			return super.getGroup();
		}
	}
	public String getName() {
		if (board.getStatus() == Consts.INGAME) {
			int recluseIndex = board.getExtraBits().get(Consts.RECLUSEINDEX);
			String tempName = super.getName();
			Random rand = new Random();
			int x = rand.nextInt(2);
			if (recluseIndex>=0 && x==1) {
				Role r = RoleFactory.makeRole(recluseIndex);
				tempName = r.getName();
			}
			return tempName;
		} else {
			return super.getName();
		}
	}
	public String getFakeName() {
		int recluseIndex = board.getExtraBits().get(Consts.RECLUSEINDEX);
		Role r = RoleFactory.makeRole(recluseIndex);
		String tempName = r.getName();
		return tempName;
	}
	public String getRealName() {
		return super.getName();
	}
}
