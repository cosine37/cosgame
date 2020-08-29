package com.cosine.cosgame.citadels.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Player;
import com.cosine.cosgame.citadels.Role;

public class Magician extends Role {
	public Magician() {
		super();
		num = 3;
		name = "魔术师";
		numSkills = 2;
		img = "003";
		buttonNames.add("与玩家交换");
		buttonNames.add("与牌堆交换");
	}
	
	public Ask chooseSkill(int x) {
		Ask ask = super.chooseSkill(x);
		if (x == 0) {
			ask.setAskId(1031);
			ask.setAskType(CitadelsConsts.CHOOSEPLAYER);
			ask.setMsg("选择一个玩家交换手牌。");
			List<String> ls = new ArrayList<>();
			int i;
			for (i=0;i<board.getPlayers().size();i++) {
				Player p = board.getPlayers().get(i);
				if (p.getName().contentEquals(player.getName())) {
					ls.add("n");
				} else {
					ls.add("y");
				}
			}
			ask.setLs(ls);
		} else if (x == 1) {
			ask.setAskId(1032);
			ask.setAskType(CitadelsConsts.CHOOSECARDS);
			ask.setMsg("选择你要和牌堆交换的手牌。");
		}
		return ask;
	}
	
	public Ask useSkill(int x, int p1, int p2, int p3, int p4) {
		Ask ask = super.useSkill(x, p1, p2, p3, p4);
		if (x == 0) {
			int playerIndex = p2;
			Player victim = board.getPlayers().get(playerIndex);
			List<Card> thand = victim.getHand();
			List<Card> mhand = player.getHand();
			player.setHand(thand);
			victim.setHand(mhand);
			player.getCanUseRoleSkill().set(1, "n");
			board.log(player.getName() + "和" + victim.getName() + "交换了手牌。");
		}
		return ask;
	}
	
	public Ask useSkillOnHand(int x, String handChoices) {
		Ask ask = super.useSkillOnHand(x, handChoices);
		if (x==1) {
			int count = 0;
			int i;
			List<Card> toReturn = new ArrayList<>();
			for (i=handChoices.length()-1;i>=0;i--) {
				if (handChoices.charAt(i) == 'y') {
					count++;
					Card c = player.getHand().remove(i);
					toReturn.add(c);
				}
			}
			board.bottomDeck(toReturn);
			player.draw(count);
			player.getCanUseRoleSkill().set(0, "n");
			board.log(player.getName() + "和牌库交换了" + Integer.toString(count) + "张牌。");
		}
		return ask;
	}
	
}
