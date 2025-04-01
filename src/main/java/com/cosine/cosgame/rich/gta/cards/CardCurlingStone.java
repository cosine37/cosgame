package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardCurlingStone extends Card {
	public CardCurlingStone() {
		super();
		id = 19;
		name = "冰壶";
		desc = "+1starP，对前方（不包括你在的这格）7格内的所有玩家造成1点伤害。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		
		board.getLogger().log(player.getName() + " 获得了 1 点通缉值");
		board.setBroadcastImg("card/"+id);
		board.setBroadcastMsg(player.getName() + "使用了冰壶，获得1点通缉值，并对前方7格所有玩家造成1点伤害。");
		
		player.addStar(1);
		int placeId = player.getPlace().getId();
		int i,j;
		for (i=1;i<=7;i++) {
			int x = placeId+i;
			int n = board.getMap().getPlaces().size();
			if (x>=n) {
				x = x-n;
			}
			
			for (j=0;j<board.getMap().getPlaces().get(x).getPlayersOn().size();j++) {
				Player p = board.getMap().getPlaces().get(x).getPlayersOn().get(j);
				board.getLogger().logLoseHp(p, 1);
				p.loseHp(1);
			}
			
			
		}
		
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() || player.isInWard()) return false;
		if (player.getPhase() != Consts.PHASE_OFFTURN) return true;
		return false;
	}
}
