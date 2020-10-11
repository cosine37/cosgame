package com.cosine.cosgame.citadels.sckx.delicacy;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Delicacy;
import com.cosine.cosgame.citadels.Player;

public class DriedRaddish extends Delicacy{
	public DriedRaddish() {
		name = "萝卜干";
		img = "d103";
		cost = 1;
	}
	
	public boolean canBuy(Player p) {
		if (p.getCanBuyDelicacy().get(index).contentEquals("n")) {
			return false;
		}
		boolean flag = true;
		for (int i=0;i<p.getBuilt().size();i++) {
			if (p.getBuilt().get(i).getBeautifyLevel() == 0) {
				flag = false;
				break;
			}
		}
		if (flag) {
			return false;
		}
		if (p.getCoin() >= cost) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Ask onBuy(Player p) {
		Ask ask = super.onBuy(p);
		
		ask.setAskId(79103);
		ask.setAskType(CitadelsConsts.CHOOSEBUILT);
		ask.setMsg("请选择一个你需要用来装饰的建筑。");
		List<List<String>> builtInfo = new ArrayList<>();
		int i,j;
		for (i=0;i<board.getPlayers().size();i++) {
			Player pl = board.getPlayers().get(i);
			List<String> singleBuiltInfo = new ArrayList<>();
			boolean isOwnBuilt = false;
			if (i == board.getPlayerIndex(p)) {
				isOwnBuilt = true;
			}
			for (j=0;j<pl.getBuilt().size();j++) {
				if (isOwnBuilt) {
					if (pl.getBuilt().get(j).getBeautifyLevel() == 0) {
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
		
		return ask;
	}
	
	public Ask afterEffect(Player p, int x) {
		Ask ask = super.afterEffect(p, x);
		
		if (p.getCanBuyDelicacy().get(index).contentEquals("y")) {
			if (p.getCoin() >= cost) {
				p.getCanBuyDelicacy().set(index, "n");
				int y = p.getCoin() - cost;
				board.log(p.getName() + "花费了1￥购买了 萝卜干。");
				p.setCoin(y);
				board.addCoin(cost);
				p.getBuilt().get(x).beautify();
				board.log(p.getName() + "将购买的 萝卜干 放置于  " + p.getBuilt().get(x).getName() + " 内。");
				board.log(p.getName() + "的 " + p.getBuilt().get(x).getName() + " 升值了。");
			}
		}
		return ask;
	}
}
