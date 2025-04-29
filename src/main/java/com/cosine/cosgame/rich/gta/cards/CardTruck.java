package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Factory;
import com.cosine.cosgame.rich.Vehicle;

public class CardTruck extends Card {
	public CardTruck() {
		super();
		id = 65;
		name = "卡车卡";
		desc = "获得载具，随机获得2张牌。消耗。";
		rarity = 2;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			boolean f;
			int x = 0;
			f = player.addRandomCard();
			if (f) x++;
			f = player.addRandomCard();
			if (f) x++;
			
			Vehicle v = Factory.genRandomVehicle(board.getMap().getVehicleIds());
			board.setBroadcastImg("card/"+id);
			board.getLogger().log(player.getName() + " 获得载具 "+ v.getName());
			board.getLogger().logGainCard(player, x);
			if (x == 0) {
				board.setBroadcastMsg(player.getName() + "使用了" + name +"，获得了载具" + v.getName() + "，但是手牌已达上限，没有获得任何牌。");
			} else {
				board.setBroadcastMsg(player.getName() + "使用了" + name +"，获得了载具" + v.getName() + "并随机获得了" + x + "张牌。");
			}
			
			player.receiveVehicle(v);
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
