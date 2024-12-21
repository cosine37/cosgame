package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.Consts;
import com.cosine.cosgame.oink.pope.PopePlayer;

public class Seer extends Card{
	public Seer() {
		super();
		this.num = 2;
		type = Consts.CTYPE_TARGET;
		this.name = "预言家";
		this.img = "Seer";
		this.desc = "查看一名其他在场玩家的手牌。";
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		if (target != -1) {
			PopePlayer tp = game.getPlayers().get(target);
			if (tp.isProtect()) {
				player.setPhase(Consts.OFFTURN);
			} else {
				String s = "正等待" + player.getName() + "的确认。";
				player.setTargetedMsg(s);
				s = player.getName() + "对你打出了" + this.name + "，你的手牌将会被查看。";
				tp.setTargetedMsg(s);
				tp.setPhase(Consts.TARGETED);
				player.setPhase(Consts.WAITING);
			}
		}
	}
	
	public void onTargetConfirm() {
		super.onTargetConfirm();
		int target = player.getTarget();
		if (target != -1) {
			PopePlayer tp = game.getPlayers().get(target);
			Card h = tp.getHand().get(0);
			player.addResolve("预言家查看" + tp.getName() + "的手牌", h);
			player.setPhase(Consts.OFFTURN);
		}
	}
	
	
	
}
