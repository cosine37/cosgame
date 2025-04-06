package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class CardShuriken extends Card {
	public CardShuriken() {
		super();
		id = 33;
		name = "手里剑";
		desc = "成功率：85%；+1starP，对后方最近的玩家（随机之一）造成1点伤害。消耗。";
		rarity = 0;
		attack = 1;
		aim = 85;
	}
	
	public void play(int rawOptions) {
		boolean f = aimed();
		
		int i;
		Player targetPlayer = null;
		int n = player.getBoard().getMap().getPlaces().size();
		for (i=1;i<=n;i++) {
			int x = player.getPlaceIndex()-i;
			if (x<0) x=x+n;
			Place p = player.getBoard().getMap().getPlace(x);
			if (p.getPlayersOn().size()>0) {
				Random rand = new Random();
				int y = rand.nextInt(p.getPlayersOn().size());
				targetPlayer = p.getPlayersOn().get(y);
				break;
			}
		}
		
		board.setBroadcastImg("card/"+id);
		String targetName = targetPlayer.getName();
		if (targetName.contentEquals(player.getName())) targetName = "自己";
		
		if (f && targetPlayer!=null) {
			int h = getFinalAttack();
			
			board.getLogger().log(player.getName() + " 对 " + targetName + " 造成了 " + h + " 点伤害");
			board.getLogger().log(player.getName() + " 获得了 1 点通缉值");
			board.setBroadcastMsg(player.getName() + "使用了手里剑，对" + targetName + "造成了" + h + "点伤害，" + player.getName() + "获得了1点通缉值。");
			
			player.hurt(targetPlayer, h);
			player.addStar(1);
		} else {
			board.getLogger().log(player.getName() + " 没有命中任何目标");
			board.setBroadcastMsg(player.getName() + "使用了手里剑，但是并没有命中任何目标。");
		}
		
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
