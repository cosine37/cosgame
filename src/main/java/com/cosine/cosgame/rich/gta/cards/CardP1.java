package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardP1 extends Card {
	public CardP1() {
		super();
		id = 11;
		name = "加一卡";
		desc = "本回合的步数+1，可以在掷骰之后移动之前使用。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int x = 1;
			player.getBuff().setRollAdd(x);
			
			board.getLogger().logAddMoreStep(player, x);
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name +"，移动时额外移动" + x + "步。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return alsoPlayableAfterRoll();
	}
}
