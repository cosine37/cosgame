package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.Consts;

public class Thief extends Card{
	public Thief() {
		super();
		this.num = 0;
		this.name = "盗贼";
		this.img = "Thief";
		this.desc = "本轮游戏结束时，若你是唯一在场打出或弃置盗贼的玩家，你获得一个灵杖。";
		this.color = "DarkSlateGrey";
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		logPlay();
		player.setPlayedThief(true);
		player.setPhase(Consts.OFFTURN);
		log("本轮游戏结束时，" + player.getName() + "派出的盗贼有可能会为" + player.getName() + "偷得一个灵杖。");
	}
	
	public void onDiscard() {
		super.onDiscard();
		player.setPlayedThief(true);
		log("本轮游戏结束时，" + player.getName() + "派出的盗贼有可能会为" + player.getName() + "偷得一个灵杖。");
	}
}
