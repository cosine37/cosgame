package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Factory;
import com.cosine.cosgame.rich.Vehicle;

public class CardGTA extends Card {
	public CardGTA() {
		super();
		id = 27;
		name = "G T A";
		desc = "+2starP，获得载具。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			Vehicle v = Factory.genRandomVehicle(board.getMap().getVehicleIds());
			
			board.getLogger().log(player.getName() + " 从路人处偷得载具 "+ v.getName());
			board.getLogger().logGainstar(player, 2);
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "从路人处偷得载具" + v.getName() + "，增加2点通缉值。");
			
			player.addStar(2);
			player.receiveVehicle(v);
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
