package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.CardFactory;
import com.cosine.cosgame.oink.pope.Consts;
import com.cosine.cosgame.oink.pope.PopePlayer;

public class Werewolf extends Card{
	public Werewolf() {
		super();
		this.num = 1;
		type = Consts.CTYPE_TARGET;
		this.name = "狼人";
		this.img = "Werewolf";
		this.desc = "猜一名其他在场玩家的手牌（不能猜狼人），若猜中则该玩家出局。";
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
				s = player.getName() + "对你打出了" + this.name + "，将会猜测你的身份。";
				tp.setTargetedMsg(s);
				tp.setPhase(Consts.TARGETED);
				player.setPhase(Consts.WAITING);
			}
		}
	}
	
	public void onResolve(int val) {
		if (player.getPhase() == Consts.CHOOSEOPTION) {
			int x = val;
			
			PopePlayer tp = game.getPlayers().get(player.getTarget());
			
			log(player.getName() + "觉得" + tp.getName() + "的手牌是" + CardFactory.getCardName(x));
			int y = tp.getHand().get(0).getNum();
			if (x == y) {
				log("竟然刀中了！");
				tp.inactivate();
			} else {
				log("但是意料之中地没有刀中");
			}
			player.setPhase(Consts.OFFTURN);
		}
	}
	
	public void onTargetConfirm() {
		super.onTargetConfirm();
		int target = player.getTarget();
		if (target != -1) {
			PopePlayer tp = game.getPlayers().get(target);
			player.setPhase(Consts.CHOOSEOPTION);
			tp.setPhase(Consts.OFFTURN);
		}
	}
	
}
