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
		this.color = "DarkCyan";
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		logPlay(target);
		if (target != -1) {
			PopePlayer tp = game.getPlayers().get(target);
			if (tp.isProtect()) {
				logProtect(tp);
				player.setPhase(Consts.OFFTURN);
			} else {
				String s = "正等待" + tp.getName() + "的确认。";
				player.setTargetedMsg(s);
				Card h = tp.getHand().get(0);
				player.addResolve("预言家查看" + tp.getName() + "的手牌", h);
				s = player.getName() + "对你打出了" + this.name + "，并查看了你的手牌。";
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
			log(tp.getName() + "的手牌被" + player.getName() + "查看了");
			player.setPhase(Consts.OFFTURN);
			tp.setPhase(Consts.OFFTURN);
		}
	}
	
	
	
}
