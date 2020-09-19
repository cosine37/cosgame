package com.cosine.cosgame.citadels.sckx.omnicolor;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Office extends Card{
	public Office() {
		super();
		name = "写字楼";
		cost = 5;
		img = "o51p";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public String canUseSkill() {
		return "y";
	}
	
	public String canUseSkillSameRound() {
		return "y";
	}
	
	public Ask chooseSkill(int index, int x) {
		Ask ask = super.chooseSkill(index, x);
		if (player.getHand().size() > 1) {
			List<String> ls = new ArrayList<>();
			for (int i=0;i<player.getHand().size();i++) {
				if (player.getHand().get(i).getColor() <= 9) {
					ls.add("y");
				} else {
					ls.add("n");
				}
			}
			ask.setAskId(99951);
			ask.setAskType(5);
			ask.setAskBuiltIndex(index);
			ask.setMsg("请展示一张手牌");
			ask.setLs(ls);
		} else {
			ask = useSkill(index, x);
		}
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 写字楼 的特效。");
		if (player.getHand().size() == 0) {
			board.log("然而" + name + "没有手牌，所以无事发生。");
		} else  {
			if (player.getHand().size() == 1) {
				x = 0;
			}
			Card c = player.getHand().get(x);
			board.log(name + "展示了" + c.getName() +"。");
			
			if (c.getColor() == CitadelsConsts.GREEN) {
				color = c.getColor();
				img = "o51g";
				board.log(name + "的写字楼变更为 商业类型。");
			} else if (c.getColor() == CitadelsConsts.BLUE) {
				color = c.getColor();
				img = "o51b";
				board.log(name + "的写字楼变更为 教育类型。");
			} else if (c.getColor() == CitadelsConsts.RED) {
				color = c.getColor();
				img = "o51r";
				board.log(name + "的写字楼变更为 治安类型。");
			} else if (c.getColor() == CitadelsConsts.YELLOW) {
				color = c.getColor();
				img = "o51y";
				board.log(name + "的写字楼变更为 市政类型。");
			} else if (c.getColor() == CitadelsConsts.PURPLE) {
				color = c.getColor();
				img = "o51p";
				board.log(name + "的写字楼变更为 特殊类型。");
			}
			
		}
		return ask;
	}
}
