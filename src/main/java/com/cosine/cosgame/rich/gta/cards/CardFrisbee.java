package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardFrisbee extends Card {
	public CardFrisbee() {
		super();
		id = 34;
		name = "飞盘卡";
		desc = "+1starP，对前方（不包括所在格）4格内的所有玩家造成1点伤害。消耗。";
		rarity = 0;
		attack = 1;
	}
	
	public void play(int rawOptions) {
		
		board.getLogger().log(player.getName() + " 获得了 1 点通缉值");
		board.setBroadcastImg("card/"+id);
		
		int y = getFinalAttack();
		
		board.setBroadcastMsg(player.getName() + "使用了飞盘卡，获得1点通缉值，并对前方4格所有玩家造成"+y+"点伤害。");
		
		player.addStar(1);
		int placeId = player.getPlace().getId();
		int i,j;
		int distance = 4;
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
			}
			
			
		}
		
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
