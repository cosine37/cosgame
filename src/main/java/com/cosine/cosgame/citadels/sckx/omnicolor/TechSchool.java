package com.cosine.cosgame.citadels.sckx.omnicolor;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class TechSchool extends Card{
	public TechSchool() {
		super();
		name = "技校";
		cost = 4;
		img = "o41b";
		color = CitadelsConsts.BLUE;
		expansion = 3;
	}
	
	public void endTurnEffect() {
		String name = player.getName();
		if (img.contentEquals("o41b")) {
			img = "o41g";
			color = CitadelsConsts.GREEN;
			board.log(name + "的 技校 变成了 商业类型。");
		} else if (img.contentEquals("o41g")) {
			img = "o41r";
			color = CitadelsConsts.RED;
			board.log(name + "的 技校 变成了 治安类型。");
		} else if (img.contentEquals("o41r")) {
			img = "o41y";
			color = CitadelsConsts.YELLOW;
			board.log(name + "的 技校 变成了 市政类型。");
		} else if (img.contentEquals("o41y")) {
			img = "o41b";
			color = CitadelsConsts.BLUE;
			board.log(name + "的 技校 变成了 教育类型。");
		}
	}
}
