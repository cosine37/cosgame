package com.cosine.cosgame.citadels.sckx.omnicolor;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Roadblock extends Card{
	public Roadblock() {
		super();
		name = "路障";
		cost = 0;
		img = "o01r";
		color = CitadelsConsts.RED;
		expansion = 3;
		buildCount = 0;
	}
	
	public void onAnotherBuild(Card c) {
		Card r = player.getBuilt().remove(index);
		player.getCanUseCardSkill().remove(index);
		board.addCoin(r.getBeautifyLevel());
		r.setBeautifyLevel(0);
		board.bottomDeck(r);
		board.log(player.getName() + "将路障拆除了。");
	}
}
