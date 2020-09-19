package com.cosine.cosgame.citadels.rdarkcity;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Player;
import com.cosine.cosgame.citadels.Role;

public class Diplomat extends Role {
	public Diplomat() {
		super();
		num = 8;
		name = "法官";
		numSkills = 2;
		img = "108";
		color = CitadelsConsts.RED;
		buttonNames.add("收益");
		buttonNames.add("交换");
	}
	
	public Ask chooseSkill(int x) {
		Ask ask = super.chooseSkill(x);
		if (x == 0) {
			ask = useSkill(0,0,0,0,0);
		} else {
			ask.setAskId(1181);
			ask.setAskType(CitadelsConsts.CHOOSEBUILT);
			ask.setMsg("请选择一个你需要用来交换的建筑。");
			List<List<String>> builtInfo = new ArrayList<>();
			int i,j,k,l;
			for (i=0;i<board.getPlayers().size();i++) {
				Player p = board.getPlayers().get(i);
				List<String> singleBuiltInfo = new ArrayList<>();
				for (j=0;j<p.getBuilt().size();j++) {
					if (p.getName().contentEquals(player.getName())) {
						Card c = p.getBuilt().get(j);
						boolean flag = false;
						for (k=0;k<board.getPlayers().size();k++) {
							if (board.getPlayers().get(k).getName().contentEquals(player.getName())) {
								continue;
							}
							if (board.getPlayers().get(k).canReceive(player, c)) {
								flag = true;
								break;
							}
						}
						if (flag) {
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
				ask = useSkill(99,0,0,0,0);
			}
		}
		return ask;
	}
	
	public Ask useSkill(int x, int p1, int p2, int p3, int p4) {
		Ask ask;
		if (x == 0) {
			ask = super.useSkill(x, p1, p2, p3, p4);
			benefit();
			
		} else if (x == 1) {
			int playerIndex = p2;
			int builtIndex = p3;
			int dpBuiltIndex = p1;
			Player tp = board.getPlayers().get(playerIndex);
			Card c = tp.getBuilt().get(builtIndex);
			System.out.println("playerIndex:" + playerIndex);
			System.out.println("builtIndex:" + builtIndex);
			
			if (tp.getName().contentEquals(player.getName())) { // choose own first
				ask = new Ask();
				ask.setAskId(1182);
				ask.setAskType(CitadelsConsts.CHOOSEBUILT);
				ask.setAskBuiltIndex(builtIndex);
				ask.setMsg("请选择一个你想要得到的建筑。");
				List<List<String>> builtInfo = new ArrayList<>();
				int i;
				for (i=0;i<board.getPlayers().size();i++) {
					Player p = board.getPlayers().get(i);
					builtInfo.add(p.canExchangeList(tp, c));
				}
				ask.setBuiltInfo(builtInfo);
			} else { // choose other's built to exchange
				ask = super.useSkill(x, p1, p2, p3, p4);
				Card dc = player.getBuilt().remove(dpBuiltIndex);
				player.getCanUseCardSkill().remove(dpBuiltIndex);
				c = tp.getBuilt().remove(builtIndex);
				int y = c.getCost() - dc.getCost();
				if (y < 0) y=0;
				int z = player.getCoin()-y;
				player.setCoin(z);
				z = tp.getCoin() + y;
				tp.setCoin(z);
				tp.getBuilt().add(dc);
				player.getBuilt().add(c);
				player.getCanUseCardSkill().add(c.canUseSkillSameRound());
				board.log(player.getName() + "将自己的"+dc.getName()+"和"+tp.getName()+"的"+c.getName()+"交换了。");
				if (y>0) {
					board.log(player.getName()+"支付给了"+tp.getName()+" "+Integer.toString(y)+"￥。");
				}
			}
		} else if (x == 99) {
			player.useRoleSkill(1);
			ask = new Ask();
			board.log(player.getName() + "使出浑身解数想要交换，但是没有可以用来交换的建筑。");
		} 
		else {
			ask = new Ask();
		}
		
		return ask;
	}
}
