package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;
import com.cosine.cosgame.gravepsycho.Player;

public class Raider extends Event{
	public Raider() {
		super();
		this.num = 21;
		this.img = "raider";
		this.name = "盗圣之证";
		this.desc = "若你成功偷窃一名玩家，额外获得7枚钱币。";
	}
	
	public void stealSuccessful(Player p) {
		p.addMoney(7);
		board.getLogger().log("因偷窃成功，见习盗贼" + p.getName() + "从盗圣处收获了7枚金币作为奖金");
	}
}
