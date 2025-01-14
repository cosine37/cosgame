package com.cosine.cosgame.gravepsycho.event;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;
import com.cosine.cosgame.gravepsycho.Player;

public class Search extends Event{
	public Search() {
		super();
		this.num = 2;
		this.img = "search";
		this.name = "东搜西罗";
		this.desc = "若你是唯一一个选择返回营地的玩家，获得$5。";
	}
	
	public boolean singleBackHandle(Player p) {
		p.addMoney(5);
		board.getLogger().log(p.getName() + "从归途的犄角旮旯中找到了5枚钱币");
		return false;
	}
}
