package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Factory;
import com.cosine.cosgame.rich.Vehicle;

public class CardVehicleCoupon extends Card {
	public CardVehicleCoupon() {
		super();
		id = 9;
		name = "载具券";
		desc = "获得全新的载具。消耗。";
		rarity = 2;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			Vehicle v = Factory.genRandomVehicle(board.getMap().getVehicleIds());
			player.receiveVehicle(v);
			
			board.getLogger().log(player.getName() + " 兑换了载具 "+ v.getName());
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了载具券，兑换了" + v.getName() + "。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() || player.isInWard()) return false;
		if (player.getPhase() != Consts.PHASE_OFFTURN) return true;
		return false;
	}
}
