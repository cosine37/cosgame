package com.cosine.cosgame.citadels.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Player;
import com.cosine.cosgame.citadels.Role;

public class Warlord extends Role {
	public Warlord() {
		super();
		num = 8;
		name = "城管";
		numSkills = 2;
		img = "008";
		color = CitadelsConsts.RED;
		buttonNames.add("收益");
		buttonNames.add("拆除");
	}
	
	public Ask chooseSkill(int x) {
		Ask ask = super.chooseSkill(x);
		if (x == 0) {
			ask = useSkill(0,0,0,0,0);
		} else if (x == 1) {
			ask.setAskId(1081);
			ask.setAskType(CitadelsConsts.CHOOSEBUILT);
			ask.setMsg("请选择一个你要拆除的建筑。");
			List<List<String>> builtInfo = new ArrayList<>();
			int i,j;
			for (i=0;i<board.getPlayers().size();i++) {
				Player p = board.getPlayers().get(i);
				List<String> singleBuiltInfo = new ArrayList<>();
				for (j=0;j<p.getBuilt().size();j++) {
					if (p.getBuilt().size() >= board.getFinishCount()) {
						singleBuiltInfo.add("-1");
					} else if (p.getRole().isDestroyable() || p.getRole().getNum() == board.getKilledRole()) {
						int temp = p.getBuilt().get(j).getCost() + p.getBuilt().get(j).getBeautifyLevel()-1;
						if (player.getGreatWallIndex() >= 0 && player.getGreatWallIndex() != j) {
							temp = temp+1;
						}
						if (temp < 0) temp = 0;
						if (player.getCoin() < temp) {
							singleBuiltInfo.add("-1");
						} else if (p.getBuilt().get(j).destroyable()){
							singleBuiltInfo.add(Integer.toString(temp));
						} else {
							singleBuiltInfo.add("-1");
						}
					} else {
						singleBuiltInfo.add("-1");
					}
				}
				builtInfo.add(singleBuiltInfo);
			}
			ask.setBuiltInfo(builtInfo);
			
			boolean flag = true;
			for (i=0;i<builtInfo.size();i++) {
				for (j=0;j<builtInfo.get(i).size();j++) {
					if (builtInfo.get(i).get(j) != "-1") {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				ask = useSkill(99,0,0,0,0);
			}
		}
		return ask;
	}
	
	public Ask useSkill(int x, int p1, int p2, int p3, int p4) {
		Ask ask = super.useSkill(x, p1, p2, p3, p4);
		if (x == 0) {
			benefit();
			ask = new Ask();
		} if (x == 1) {
			int playerIndex = p2;
			int builtIndex = p3;
			Player p = board.getPlayers().get(playerIndex);
			if (builtIndex < p.getBuilt().size()) {
				Card c = p.getBuilt().remove(builtIndex);
				p.getCanUseCardSkill().remove(builtIndex);
				String victimName = p.getName();
				String builtName = c.getName();
				int spent = c.getCost() + c.getBeautifyLevel() - 1;
				if (p.getGreatWallIndex() >= 0 && p.getGreatWallIndex() != builtIndex) {
					spent = spent+1;
				}
				int temp = player.getCoin() - spent;
				player.setCoin(temp);
				board.addCoin(spent);
				board.log(player.getName() + "花费了" + Integer.toString(spent) + "￥拆除了" + victimName + "的" + builtName+"。");
				board.addCoin(c.getBeautifyLevel());
				c.setBeautifyLevel(0);
				board.bottomDeck(c);
			}
		} if (x == 99) {
			player.useRoleSkill(1);
			ask = new Ask();
			board.log(player.getName() + "使出浑身解数想要拆除一个建筑，但是没有可以拆除的建筑。");
		}
		return ask;
	}
	
}
