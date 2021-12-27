package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class XuShu extends Card {
	public XuShu() {
		name = "徐庶";
		courtesy = "元直";
		img = "XuShu";
		title = "走馬薦諸葛";
		faction = Consts.HAN;
		
		desc = "王道+1或霸道+1。将你阵面的一名武将和酒馆的一名武将交换。";
		
		playType = Consts.CHOOSEPLAYOPTIONTWO;
		playSubType = Consts.MYCARD;
		
		options.add("王道+1");
		options.add("霸道+1");
	}
	
	public List<String> getOptions2Display() {
		options2 = new ArrayList<>();
		for (int i=0;i<board.getTavern().size();i++) {
			Card c = board.getTavern().get(i);
			options2.add("与" + c.getName() + "交换");
		}
		return options2;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >4) {
			int option1 = targets.get(0);
			if (option1 == 0) {
				board.moveHan(1);
			} else if (option1 == 1) {
				board.moveWei(1);
			}
			int option2 = targets.get(4);
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (player != null && p.getPlay().size() > y && option2>=0 && option2<3) {
				Card c1 = p.getPlay().get(y);
				Card c2 = board.getTavern().get(option2);
				board.getTavern().set(option2, c1);
				p.getPlay().set(y, c2);
				board.log(p.getName() + "阵面的" + c1.getName() + "与酒馆中的" + c2.getName() + "交换了。");
			}
		}
		
	}
}
