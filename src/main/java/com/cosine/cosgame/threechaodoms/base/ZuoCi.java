package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class ZuoCi extends Card {
	public ZuoCi() {
		name = "左慈";
		courtesy = "";
		img = "ZuoCi";
		title = "烏角先生";
		faction = Consts.QUN;
		emulator = true;
		emulateType = Consts.EMULATETOMB;
		
		desc = "执行墓地顶武将的效果。";
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (board.getTomb().size() > 0) {
			Card c = board.topTomb();
			c.setPlayer(player);
			board.log("左慈模仿了墓地顶" + c.getName() + "的效果。");
			c.play(targets);
		}
	}
}
