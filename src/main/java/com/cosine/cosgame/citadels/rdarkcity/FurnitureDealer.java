package com.cosine.cosgame.citadels.rdarkcity;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Player;
import com.cosine.cosgame.citadels.Role;

public class FurnitureDealer extends Role {
	public FurnitureDealer() {
		super();
		num = 9;
		name = "家具商";
		numSkills = 2;
		img = "119";
		buttonNames.add("装饰");
		buttonNames.add("收回");
	}
	
	public Ask chooseSkill(int x) {
		Ask ask = super.chooseSkill(x);
		if (x == 0) {
			ask.setAskId(1191);
			ask.setAskType(CitadelsConsts.CHOOSETWOBUILTS);
			ask.setMsg("请选择至多两个你要装饰的建筑。");
			ask.setUpperLimit(2);
			if (player.getCoin() == 1) {
				ask.setMsg("请选择一个你要装饰的建筑。");
				ask.setUpperLimit(1);
			}
			List<List<String>> builtInfo = new ArrayList<>();
			int i,j;
			for (i=0;i<board.getPlayers().size();i++) {
				Player p = board.getPlayers().get(i);
				List<String> singleBuiltInfo = new ArrayList<>();
				boolean isOwnBuilt = false;
				if (i == board.getPlayerIndex(player)) {
					isOwnBuilt = true;
				}
				for (j=0;j<p.getBuilt().size();j++) {
					if (isOwnBuilt) {
						if (p.getBuilt().get(j).getBeautifyLevel() == 0) {
							singleBuiltInfo.add("0");
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
			if (player.getCoin() == 0) {
				ask = useSkill(98,0,0,0,0);
			}
			if (flag) {
				ask = useSkill(99,0,0,0,0);
			}
			
		} else if (x == 1) {
			ask.setAskId(1192);
			ask.setAskType(CitadelsConsts.CHOOSEBUILT);
			ask.setMsg("请选择一个你要收回装饰的建筑。");
			List<List<String>> builtInfo = new ArrayList<>();
			int i,j;
			for (i=0;i<board.getPlayers().size();i++) {
				Player p = board.getPlayers().get(i);
				List<String> singleBuiltInfo = new ArrayList<>();
				boolean isOwnBuilt = false;
				if (i == board.getPlayerIndex(player)) {
					isOwnBuilt = true;
				}
				for (j=0;j<p.getBuilt().size();j++) {
					if (isOwnBuilt) {
						if (p.getBuilt().get(j).getBeautifyLevel() > 0) {
							singleBuiltInfo.add("0");
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
				ask = useSkill(97,0,0,0,0);
			}
		}
		
		return ask;
	}
	
	public Ask useSkill(int x, int p1, int p2, int p3, int p4) {
		Ask ask = super.useSkill(x, p1, p2, p3, p4);
		if (x == 0) {
			player.useRoleSkill(0);
			int builtIndex = p3;
			int b1 = -1;
			int b2 = -1;
			if (builtIndex > 100) {
				b1 = builtIndex/100-1;
				b2 = builtIndex%100-1;
				board.log(player.getName() + "装饰了两个建筑");
			} else {
				b1 = builtIndex-1;
				board.log(player.getName() + "装饰了一个建筑");
			}
			player.beautify(b1);
			x = player.getCoin() - 1;
			player.setCoin(x);
			board.addCoin(1);
			if (b2 != -1) {
				player.beautify(b2);
				x = player.getCoin() - 1;
				player.setCoin(x);
				board.addCoin(1);
			}
		} else if (x == 1) {
			player.useRoleSkill(1);
			int builtIndex = p3;
			int y = player.getBuilt().get(builtIndex).getBeautifyLevel();
			if (y>0) {
				y = y-1;
				int c = player.getCoin()+1;
				player.setCoin(c);
				player.getBuilt().get(builtIndex).setBeautifyLevel(y);
				board.log(player.getName() + "从已装饰的 " + player.getBuilt().get(builtIndex).getName() + " 上拿回了1￥。");
			}
		} else if (x == 97) {
			player.useRoleSkill(1);
			ask = new Ask();
			board.log("家具商大仙说：“" + player.getName() + "土鳖，都不装饰装饰你的建筑。”");
		} else if (x == 98) {
			player.useRoleSkill(0);
			ask = new Ask();
			board.log("家具商大仙说：“" + player.getName() + "，你想白嫖？这是不可能的。”");
		} else if (x == 99) {
			player.useRoleSkill(0);
			ask = new Ask();
			board.log(player.getName() + "使出浑身解数想要装饰，但是没有可以装饰的建筑。");
		}
		if (player.getCanUseRoleSkill().get(0).contentEquals("n") 
				&& player.getCanUseRoleSkill().get(1).contentEquals("n")) {
			board.log("家具商大仙说：“" + player.getName() + "，又是装饰又是收回的，你玩我呐？”");
		}
		return ask;
	}
}
