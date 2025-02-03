package com.cosine.cosgame.gravepsycho.event;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class Echo extends Event{
	public Echo() {
		super();
		this.num = 18;
		this.img = "echo";
		this.name = "远古回响";
		this.desc = "翻出钱币时，钱币数量改为展示的所有钱币牌数值的最大值。";
	}
	
	public boolean distributeCoins(int x) {
		int i;
		int oldx = x;
		x = 0;
		List<Card> revealed = board.getRevealed();
		for (i=0;i<revealed.size();i++) {
			if (revealed.get(i).getType() == Consts.COIN && revealed.get(i).getNum() > x) {
				x = revealed.get(i).getNum();
			}
		}
		if (x != oldx) {
			board.getLogger().log("远古回响！钱币的数量被神秘力量变更为" + Integer.toString(x) + "枚");
		}
		
		int n=0;
		for (i=0;i<board.getPlayers().size();i++) {
			if (board.getPlayers().get(i).isStillIn()) {
				n++;
			}
		}
		if (n>0) {
			List<String> pnames = new ArrayList<>();
			int t = x/n;
			int y = x%n;
			board.setLeftover(board.getLeftover()+y);
			for (i=0;i<board.getPlayers().size();i++) {
				if (board.getPlayers().get(i).isStillIn()) {
					board.getPlayers().get(i).addMoney(t);
					pnames.add(board.getPlayers().get(i).getName());
				}
			}
			board.getLogger().logDistributeCoins(pnames, t);
		}
		return true;
	}
}
