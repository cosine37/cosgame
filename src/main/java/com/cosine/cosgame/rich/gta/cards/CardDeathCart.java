package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardDeathCart extends Card {
	public CardDeathCart() {
		super();
		id = 26;
		name = "归天之车";
		desc = "+2starP，对前方（不包括你在的这格）9格内的所有玩家造成2点伤害并摧毁其载具。消耗。";
		rarity = 1;
		attack = 2;
	}
	
	public void play(int rawOptions) {
		
		board.getLogger().log(player.getName() + " 获得了 2 点通缉值");
		board.setBroadcastImg("card/"+id);
		
		int y = getFinalAttack();
		
		board.setBroadcastMsg(player.getName() + "使用了归天之车，获得2点通缉值，对前方9格所有玩家造成"+y+"点伤害并摧毁其载具。");
		
		player.addStar(2);
		int placeId = player.getPlace().getId();
		int i,j;
		int distance = 9;
		for (i=1;i<=distance;i++) {
			int x = placeId+i;
			int n = board.getMap().getPlaces().size();
			if (x>=n) {
				x = x-n;
			}
			
			for (j=0;j<board.getMap().getPlaces().get(x).getPlayersOn().size();j++) {
				Player p = board.getMap().getPlaces().get(x).getPlayersOn().get(j);
				
				
				board.getLogger().logLoseHp(p, y);
				player.hurt(p, y);
				if (p.hasVehicle()) {
					p.loseVehicle();
				}
			}
			
			
		}
		
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
