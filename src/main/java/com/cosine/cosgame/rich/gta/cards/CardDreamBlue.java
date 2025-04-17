package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardDreamBlue extends Card {
	public CardDreamBlue() {
		super();
		id = 10005;
		name = "梦之蓝";
		desc = "本回合你获得增伤效果。若你有载具，+2starP。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			board.setBroadcastImg("card/"+id);
			
			if (player.hasVehicle()) {
				board.setBroadcastMsg(player.getName() + "在载具上使用了" + name + "，获得2点通缉值，且本回合获得增伤效果。");
				board.getLogger().log(player.getName() + " 因在载具上使用 " + name + "，获得 2 点通缉值，且本回合获得增伤效果");
				
				player.addStar(2);
			} else {

				board.setBroadcastMsg(player.getName() + "使用了" + name + "，本回合获得增伤效果。");
				board.getLogger().log(player.getName() + " 本回合获得增伤效果");
			}
			
			player.getBuff().setAttackBoost(1);
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
