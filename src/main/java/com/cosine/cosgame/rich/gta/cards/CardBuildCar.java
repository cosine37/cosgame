package com.cosine.cosgame.rich.gta.cards;

import java.util.ArrayList;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Factory;
import com.cosine.cosgame.rich.Vehicle;

public class CardBuildCar extends Card {
	public CardBuildCar() {
		super();
		id = 59;
		name = "闭门造车";
		desc = "丢弃所有手牌，若丢弃的牌不小于2张，获得载具。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			for (int i=0;i<player.getHand().size();i++) {
				player.getHand().get(i).onThrow();
			}
			
			if (player.getHand().size()>=2) {
				Vehicle v = Factory.genRandomVehicle(board.getMap().getVehicleIds());
			
				board.getLogger().log(player.getName() + " 弃置了所有手牌并徒手搓出了载具 "+ v.getName());
				
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "弃置了所有手牌并徒手搓出了载具" + v.getName() + "，真是个天才。");
				player.receiveVehicle(v);
			} else {
				board.getLogger().log(player.getName() + " 弃置了所有手牌，但是没造出载具，这就尴尬了");
				
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "弃置了所有手牌，但是没造出载具，这就尴尬了。");
			}
			
			player.setHand(new ArrayList<>());
			
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
