package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardDowngrade extends Card {
	public CardDowngrade() {
		super();
		id = 37;
		name = "拆屋卡";
		desc = "将当前地产降一级，若成功发动，+1starP。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		boolean f = changeEstateLevel(player.getPlace(),-1);
		if (f) {
			board.getLogger().log(player.getName() + " 将地产 " + player.getPlace().getName() + " 降了一级");
			board.getLogger().log(player.getName() + " 增加 1 点通缉值");
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name + "将地产" + player.getPlace().getName() + "降了一级。");
			player.addStar(1);
		} else {
			if (player.getPlace() != null) {
				board.getLogger().log(player.getName() + " 无法降级 " + player.getPlace().getName());
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，但是无法降级" + player.getPlace().getName() + "，这就尴尬了。");
			}
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
