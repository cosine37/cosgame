package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardLittleEssay extends Card {
	public CardLittleEssay() {
		super();
		id = 22;
		name = "小作文";
		desc = "指定一名玩家获得+2starP。消耗。";
		rarity = 2;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			Random rand = new Random();
			int x = rand.nextInt(100);
			
			board.setBroadcastImg("card/"+id);
			tp.addStar(2);
			
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
				
			board.getLogger().log(player.getName() + " 对 " + targetName + " 进行了社死攻击， " + tp.getName() + "的通缉值增加了2");
			board.setBroadcastMsg(player.getName() + "使用小作文对" + targetName + "进行了社死攻击，" + tp.getName() + "的通缉值增加了2。");
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
