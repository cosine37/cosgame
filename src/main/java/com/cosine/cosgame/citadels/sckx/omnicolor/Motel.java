package com.cosine.cosgame.citadels.sckx.omnicolor;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Motel extends Card{
	public Motel() {
		super();
		name = "商务宾馆";
		cost = 4;
		img = "o42p";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onAnotherBuild(Card c) {
		String name = player.getName();
		if (c.getColor() == CitadelsConsts.GREEN) {
			color = c.getColor();
			img = "o42g";
			board.log(name + "的 商务宾馆 变成了 商业类型。");
		} else if (c.getColor() == CitadelsConsts.BLUE){
			color = c.getColor();
			img = "o42b";
			board.log(name + "的 商务宾馆 变成了 教育类型。");
		} else if (c.getColor() == CitadelsConsts.RED) {
			color = c.getColor();
			img = "o42r";
			board.log(name + "的 商务宾馆 变成了 治安类型。");
		} else if (c.getColor() == CitadelsConsts.YELLOW) {
			color = c.getColor();
			img = "o42y";
			board.log(name + "的 商务宾馆 变成了 市政类型。");
		} else if (c.getColor() == CitadelsConsts.PURPLE) {
			color = c.getColor();
			img = "o42p";
			board.log(name + "的 商务宾馆 变成了 特殊类型。");
		}
	}
}
