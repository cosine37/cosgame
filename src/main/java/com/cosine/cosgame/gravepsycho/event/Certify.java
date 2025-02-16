package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;
import com.cosine.cosgame.gravepsycho.Player;

public class Certify extends Event{
	public Certify() {
		super();
		this.num = 20;
		this.img = "certify";
		this.name = "勇者之证";
		this.desc = "返回营地时（在翻出下一张牌前），若有4张灾难被翻出，额外获得7枚钱币；若有5张或更多灾难被翻出，额外获得13枚钱币。";
	}
	
	public boolean backHandle(Player p) {
		List<Card> revealed = board.getRevealed();
		int x = 0;
		for (int i=0;i<revealed.size();i++) {
			if (revealed.get(i).getType() == Consts.DISASTER) {
				x++;
			}
		}
		if (x == 4) {
			p.addMoney(7);
			board.getLogger().log("二等勇者" + p.getName() + "收获了7枚金币作为奖金");
		} else if (x>=5) {
			p.addMoney(13);
			board.getLogger().log("一等勇者" + p.getName() + "收获了13枚金币作为奖金");
		}
		return false;
	}
}
